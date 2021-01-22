package com.spring.board.Controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.spring.board.VO.UserVO;

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
		HttpServletRequest request,
		HttpServletResponse response,
		HttpSession session,
		Model model,
		int bno,
		@RequestParam HashMap<Object, Object> params,
		ModelAndView mv
	) {
		
		UserVO 		userVO 		= (UserVO)session.getAttribute("user");
		//기존에 존재하던 쿠키 가져옴
		Cookie[] 	reqCookie 	= request.getCookies();
		//null값 비교용 쿠키
		Cookie 		nullCookie 	= null;
		//기존 쿠키가 존재할 경우 새로운 비교용 쿠키에 기존 쿠키 값을 넣어준다.
		if(reqCookie != null && reqCookie.length > 0 && userVO != null)
		{
			for (int i = 0; i < reqCookie.length; i++) 
			{
				if(reqCookie[i].getName().equals("cookie" + userVO.getUno() + bno))
				{
					nullCookie = reqCookie[i];
				}
			}
		}
		
		if(reqCookie != null && reqCookie.length > 0 && userVO == null)
		{
			for (int i = 0; i < reqCookie.length; i++) 
			{
				if(reqCookie[i].getName().equals("cookie" + bno))
				{
					nullCookie = reqCookie[i];
				}
			}
		}
		
		if(userVO != null && nullCookie == null)
		{
			Cookie cookie = new Cookie("cookie"+ userVO.getUno() + bno, "cookie"+ userVO.getUno() + bno);
			cookie.setMaxAge(60*60*12);
			response.addCookie(cookie);
			int boardhit = mapper.boardhit(bno);
			
			if(boardhit > 0)
			{
				System.out.println("조회수 증가 성공");
			}
			if(boardhit <= 0)
			{
				System.out.println("조회수 증가 실패");
			}
		}
		
		if(userVO == null && nullCookie == null)
		{
			Cookie cookie = new Cookie("cookie" + bno, "cookie" + bno);
			cookie.setMaxAge(60*60*12);
			response.addCookie(cookie);
			int boardhit = mapper.boardhit(bno);
			
			if(boardhit > 0)
			{
				System.out.println("조회수 증가 성공");
			}
			if(boardhit <= 0)
			{
				System.out.println("조회수 증가 실패");
			}
		}
		
		
		BoardVO vo 		= mapper.boarddetail(bno);
		FileVO fileVO 	= fmapper.fileselect(bno);
		mv.addObject("vo", vo);
		mv.addObject("fvo", fileVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
	
	//파일 다운로드 클래스 연동 컨트롤러
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
		System.out.println(vo.getBno());
		if(vo.getBno() > 0)
		//답글 작성일 경우
		{
			if(vo.getOrigino() != 0)
			{
				
				vo.setGrouplayer(vo.getGrouplayer() + 1);
				System.out.println("답글의 답글" + vo.toString());
				mapper.boardanup(vo);
			}
			if(vo.getGroupord() == 0)
			{
				vo.setOrigino(vo.getBno());
				vo.setGroupord(1);
				vo.setGrouplayer(1);
				System.out.println("원글의 답글" + vo.toString());
			}
		}
		  try 
		  {
			  //일반 글 작성일 경우
			 
			  int 		write 		= mapper.boardwrite(vo);
			  BoardVO 	boardlast 	= mapper.boardlast(); 
			  if(vo.getOrigino() == 0)
			  {
				  mapper.boardoriup(boardlast);
			  }
			  MultipartFile mf = mtfRequest.getFile("file");
		  
			  if(mf != null) 
			  { 
				  //파일 정보 추출 및 입력 
			  String 	originFileName = mf.getOriginalFilename(); 
			  long 		fileSize 	= mf.getSize(); 
			  String 	safeFile 	= UPLOAD_PATH + "\\" + originFileName; 
			  FileVO 	fileVO 		= new FileVO();
			  
			  fileVO. setBno(boardlast.getBno());
			  fileVO. setUno(vo.getUno());
			  fileVO. setFsize(mf.getSize());
			  fileVO. setFname(mf.getOriginalFilename());
			  fileVO. setPath(UPLOAD_PATH); 
			  int index = originFileName.lastIndexOf('.'); String
			  hwak = originFileName.substring(index); 
			  fileVO. setEx(hwak);
			  
			  mf.transferTo(new File(safeFile)); 
			  int fileinsert = fmapper.fileinsert(fileVO); 
			  }
		  
		  
		  } 
		  catch (IllegalStateException e) 
		  {
			  e.printStackTrace(); 
		  }
		  catch (Exception e) 
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
