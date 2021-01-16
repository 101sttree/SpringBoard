package com.spring.board.Controller;

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
import com.spring.board.VO.UserVO;

@Controller

public class boardController

{
	@Inject
	boardMapper mapper;

	@GetMapping(value = "/")
	public String boardlist
	(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			PagingVO vo,
			String nowPage,
			String cntPerPage
	) 
	{
		int total = mapper.boardcount();
		
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
		
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		List<BoardVO> list = mapper.boardlist(vo);

		model.addAttribute("paging", vo);
		model.addAttribute("list", list);
		
		return "main";
	}

	@GetMapping(value = "/board/detail")
	public String detail(Locale locale, Model model, int bno) {

		BoardVO vo = mapper.boarddetail(bno);
		model.addAttribute("vo", vo);
		return "board/detail";
	}

	@PostMapping(value = "/board/write")
	public String boardwrite(Model model, BoardVO vo) {
		int write = mapper.boardwrite(vo);
		return "redirect:/";
	}

	@GetMapping(value = "/board/delete")
	public String boarddelete(Model model, int bno) {
		int delete = mapper.boarddelete(bno);
		return "redirect:/";
	}

	@GetMapping(value = "/mody")
	public String mody(Model model, int bno) {
		BoardVO vo = mapper.boarddetail(bno);
		model.addAttribute("vo", vo);
		return "board/mody";
	}

	@PostMapping(value = "/board/mody")
	public String boardmody(Model model, BoardVO vo) {
		int mody = mapper.boardmody(vo);
		BoardVO boardVO = mapper.boarddetail(vo.getBno());
		model.addAttribute("vo", boardVO);
		return "board/detail";
	}
}
