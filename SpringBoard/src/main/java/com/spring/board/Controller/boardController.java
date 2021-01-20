package com.spring.board.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	private static final String UPLOAD_PATH = "C:\\Users\\cube\\Documents\\GitHub\\SpringBoard\\SpringBoard\\src\\main\\webapp\\resources\\file";
	
	//����ȭ��, �� �ҷ�����, ����¡ ó��
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
	
	//�� �� ����
	@GetMapping(value = "/board/detail")
	public String detail(Locale locale, Model model, int bno) {

		BoardVO vo = mapper.boarddetail(bno);
		model.addAttribute("vo", vo);
		return "board/detail";
	}
	
	
	
	//�� �ۼ�
	@PostMapping(value = "/board/write")
	public String boardwrite
	(
			Model model,
			BoardVO vo,
			MultipartHttpServletRequest mtfRequest
			//ȭ�鿡�� �޾ƿ� ������ ������ �ִ� ��ü�̴�.
	) 
	{
		int 	write 	  = mapper.boardwrite(vo);
		BoardVO boardlast = mapper.boardlast();
		
		MultipartFile mf = mtfRequest.getFile("file");
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
		
		
		try 
		{
			mf.transferTo(new File(safeFile));
			int fileinsert = fmapper.fileinsert(fileVO);
			FileOutputStream fos = new FileOutputStream(UPLOAD_PATH + mf.getOriginalFilename());
			InputStream 	 is  = mf.getInputStream();
			int readCount = 0;
            byte[] buffer = new byte[1024000];
            while ((readCount = is.read(buffer)) != -1) 
            {
                //  ���Ͽ��� ������ fileInputStream�� ������ ũ�� (1024byte) ��ŭ �а�
                
                fos.write(buffer, 0, readCount);
                // ������ ������ fileOutputStream ��ü�� ����ϱ⸦ �ݺ��Ѵ�
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
	
	
	
	
	//�� ����
	@GetMapping(value = "/board/delete")
	public String boarddelete(Model model, int bno) {
		
		int cdelete = cmapper.commentdelete(bno);
		int delete = mapper.boarddelete(bno);
		return "redirect:/";
	}
	
	//�� ���� ȭ�� �̵�
	@GetMapping(value = "/mody")
	public String mody(Model model, int bno) {
		BoardVO vo = mapper.boarddetail(bno);
		model.addAttribute("vo", vo);
		return "board/mody";
	}
	
	//�� ����
	@PostMapping(value = "/board/mody")
	public String boardmody(Model model, BoardVO vo) {
		int mody = mapper.boardmody(vo);
		BoardVO boardVO = mapper.boarddetail(vo.getBno());
		model.addAttribute("vo", boardVO);
		return "board/detail";
	}
}
