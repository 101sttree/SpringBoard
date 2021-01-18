package com.spring.board.Controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.Interface.boardMapper;
import com.spring.board.VO.BoardVO;
import com.spring.board.VO.PagingVO;
import com.spring.board.VO.SearchVO;
import com.spring.board.VO.UserVO;

@Controller

public class boardController

{
	@Inject
	boardMapper mapper;
	
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
	public String boardwrite(Model model, BoardVO vo) {
		
//		for (int i = 0; i < 50; i++) {
//			int write = mapper.boardwrite(vo);
//		}
		int write = mapper.boardwrite(vo);
		return "redirect:/";
	}
	
	//�� ����
	@GetMapping(value = "/board/delete")
	public String boarddelete(Model model, int bno) {
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
