package com.spring.board.Controller;

import java.io.File;
import java.lang.invoke.VolatileCallSite;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.style.ToStringCreator;
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
		
		//현재 페이지 및 페이지당 글 갯수 설정
		if (nowPage == null && cntPerPage == null) 
		{
			nowPage = "1";
			cntPerPage = "10";
		}
		if (nowPage == null) 
		{
			nowPage = "1";
		}
		if (cntPerPage == null) 
		{ 
			cntPerPage = "10";
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
		
		//기존 쿠키를 불러옴
		//로그인이 되있는 경우 유저 번호와 게시글 번호로 된 쿠키를 가져옴
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
		//로그인 되지 않은 pc의 경우 게시글 번호로만 된 쿠키를 가져옴
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
		
		//로그인 상태이고 조회한 적이 없는 경우
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
		//로그인되지 않은상태로 조회한 적이 없는 경우
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
		//파일 정보를 담는다.
		String fileName = (String) params.get("fileName");
		String fullPath = UPLOAD_PATH + "/" + fileName;
		File file = new File(fullPath);
		
		
		//해당 뷰로 파일 정보를 보낸다.
		mv.setViewName("downloadView");
		mv.addObject("downloadFile", file);
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
			if(vo.getBno() > 0)
			//답글 작성일 경우
			{	
				//답글의 답글
				if(vo.getGrouplayer() != 0)
				{
					//기존 글들 순서값 1 증가
					mapper.boardanup(vo);
					//본인의 값을 부모글순서 + 1 로 설정 
					vo.setGroupord(vo.getGroupord()+1);
					//부모글의 답글이기 때문에 층수 + 1
					vo.setGrouplayer(vo.getGrouplayer() + 1);
					System.out.println("답글의 답글" + vo.toString());
				}
				//원글의 답글
				if(vo.getGrouplayer() == 0)
				{
					//현재 작성된 마지막 글의 순서값을 가져온다.
					BoardVO	boardgrdmax = mapper.boardgrdmax(vo);
					//새로 작성되는 글의 순서값을 마지막글의 순서값에 + 1 값으로 설정
					vo.setGroupord(boardgrdmax.getGroupord()+1);
					//원글의 답글이기 때문에 층수 1로 설정
					vo.setGrouplayer(1);
					System.out.println("원글의 답글" + vo.toString());
				}
			}
			//일반 글 작성일 경우
			int 		write 		= mapper.boardwrite(vo);
			
			//일반 글 생성시 글의 그룹번호(origino)를 글번호로 설정
			BoardVO 	boardlast 	= mapper.boardlast(); 
			if(vo.getOrigino() == 0)
			{
				mapper.boardoriup(boardlast);
			}
			
			//글 작성시 등록된 파일 정보를 담은 객체 생성
			MultipartFile mf = mtfRequest.getFile("file");
			if(mf != null) 
			{ 
				//파일 정보 추출 및 입력 
				String 	originFileName 	= mf.getOriginalFilename(); 
				long 	fileSize 		= mf.getSize(); 
				String 	safeFile 		= UPLOAD_PATH + "\\" + originFileName; 
				FileVO 	fileVO 			= new FileVO();
				
				//파일 VO 객체에 파일의 정보를 담는다.
				fileVO. setBno(boardlast.getBno());
				fileVO. setUno(vo.getUno());
				fileVO. setFsize(mf.getSize());
				fileVO. setFname(mf.getOriginalFilename());
				fileVO. setPath(UPLOAD_PATH); 
				int index = originFileName.lastIndexOf('.'); String
				hwak = originFileName.substring(index); 
				fileVO. setEx(hwak);
				
				//실제 경로에 파일 생성
				mf.transferTo(new File(safeFile));
				//DB에 파일 정보 저장
				int fileinsert = fmapper.fileinsert(fileVO); 
			}
			
		  } 
		  catch (IllegalStateException e) 
		  {
			   e.fillInStackTrace();
		  }
		  catch (Exception e) 
		  {
			  System.out.println(e);
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
