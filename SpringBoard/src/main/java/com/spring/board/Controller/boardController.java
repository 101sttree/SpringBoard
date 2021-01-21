package com.spring.board.Controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.Interface.boardMapper;
import com.spring.board.Interface.commentMapper;
import com.spring.board.Interface.fileMapper;
import com.spring.board.VO.BoardVO;
import com.spring.board.VO.FileVO;
import com.spring.board.VO.PagingVO;
import com.spring.board.VO.SearchVO;

@Controller

public class boardController

{
	@Autowired
	boardMapper mapper;
	@Autowired
	commentMapper cmapper;
	@Autowired
	fileMapper fmapper;
	
	
	  private static final String UPLOAD_PATH =
	  "C:\\Users\\cube\\Documents\\GitHub\\SpringBoard\\SpringBoard\\src\\main\\webapp\\resources\\file";
	 
	/*
	 * private static final String UPLOAD_PATH =
	 * "C:\\Users\\hyosin\\Documents\\GitHub\\SpringBoard\\SpringBoard\\src\\main\\webapp\\resources\\file";
	 */
	
	//메인화면, 글 불러오기, 페이징 처리
	@GetMapping(value = "/")
	public String boardlist
	(
		Model model,
		HttpServletRequest request,
		HttpServletResponse response,
		PagingVO pagingVO,
		SearchVO searchVO,
		String nowPage,
		String cntPerPage,
		String searchType,
		String searchText
	) 
	{
		
		searchVO.setSearchType(searchType);
		searchVO.setSearchText(searchText); 
		int total = mapper.boardcount(searchVO);
		
		if (nowPage == null && cntPerPage == null) 
		{
			nowPage = "1";
			cntPerPage = "5";
		}
		if (nowPage == null) 
		{
			nowPage = "1";
		}
		if (cntPerPage == null) 
		{ 
			cntPerPage = "5";
		}
		
		pagingVO = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		searchVO.setStart(pagingVO.getStart());
		searchVO.setEnd(5);
		
		
		List<BoardVO> list = mapper.boardlist(searchVO);
		
		model.addAttribute("paging", pagingVO);
		model.addAttribute("list", list);
		
		return "main";
	}
	
	//글 상세 보기
	@GetMapping(value = "/board/detail")
	public ModelAndView detail
	(
			Locale locale,
			Model model,
			int bno,
			@RequestParam HashMap<Object, Object> params,
			ModelAndView mv
	) {

		BoardVO vo = mapper.boarddetail(bno);
		FileVO fileVO = fmapper.fileselect(bno);
		//model.addAttribute("vo", vo);
		mv.setViewName("board/detail");
		mv.addObject("vo", vo);
		mv.addObject("fvo", fileVO);
		return mv;
	}
	
	

	@RequestMapping("/common/download")
	public ModelAndView download
	(
			@RequestParam HashMap<Object, Object> params,
			ModelAndView mv
	)
	{
		String fileName = (String) params.get("fileName");
		String fullPath = UPLOAD_PATH + "/" + fileName;
		File file = new File(fullPath);
		//해당 경로에 있는 파일의 정보를 담는다 = new File(경로)
		
		mv.setViewName("downloadView");
		//해당 뷰로 모델에 정보를 담아 보낸다.
		mv.addObject("downloadFile", file);
		//뷰로 보낼 정보를 객체에 담는다.
		return mv;
	}
	
	@RequestMapping("/board/board.do")
	public ModelAndView board
	(
			@RequestParam HashMap<Object, Object> params,
			ModelAndView mv
	) {
		mv.setViewName("board/board");
		return mv;
	}
	
	//글 작성
	@PostMapping(value = "/board/write")
	public String boardwrite
	(
			Model model,
			BoardVO vo,
			MultipartHttpServletRequest mtfRequest
			//화면에서 받아온 파일을 가지고 있는 객체이다.
	) 
	{
		
		
		try 
		{
			int 	write 	  = mapper.boardwrite(vo);
			BoardVO boardlast = mapper.boardlast();
			MultipartFile mf = mtfRequest.getFile("file");
			
			if(mf != null)
			{
				//파일 정보 추출 및 입력
				String 	originFileName 	= mf.getOriginalFilename();
				long 	fileSize 		= mf.getSize();
				String 	safeFile = UPLOAD_PATH + "\\" + originFileName;
				FileVO 	fileVO = new FileVO();
				fileVO.	setBno(boardlast.getBno());
				fileVO.	setUno(vo.getUno());
				fileVO.	setFsize(mf.getSize());
				fileVO.	setFname(mf.getOriginalFilename());
				fileVO.	setPath(UPLOAD_PATH);
				int 	index 	= originFileName.lastIndexOf('.');
				String 	hwak 	= originFileName.substring(index);
				fileVO.	setEx(hwak);
				
				mf.transferTo(new File(safeFile));
				int fileinsert = fmapper.fileinsert(fileVO);
			}
			
			
		} catch (IllegalStateException e) 
		{
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
	
	
	
	
	//글 삭제
	@GetMapping(value = "/board/delete")
	public String boarddelete(Model model, int bno) {
		
		int fdelet 	= fmapper.filedelete(bno);
		int cdelete = cmapper.commentdelete(bno);
		int delete 	= mapper.boarddelete(bno);
		return "redirect:/";
	}
	
	//글 수정 화면 이동
	@GetMapping(value = "/mody")
	public String mody(Model model, int bno) {
		BoardVO vo = mapper.boarddetail(bno);
		model.addAttribute("vo", vo);
		return "board/mody";
	}
	
	//글 수정
	@PostMapping(value = "/board/mody")
	public String boardmody(Model model, BoardVO vo) {
		int mody = mapper.boardmody(vo);
		BoardVO boardVO = mapper.boarddetail(vo.getBno());
		model.addAttribute("vo", boardVO);
		return "board/detail";
	}
}
