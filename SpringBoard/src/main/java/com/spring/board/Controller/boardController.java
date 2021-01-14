package com.spring.board.Controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.Interface.boardMapper;
import com.spring.board.VO.BoardVO;

@Controller
public class boardController 

{
	@Inject
	boardMapper mapper;
	
	@GetMapping(value = "/board/detail")
	public String detail(Locale locale, Model model, int bno) 
	{
		
		
		BoardVO vo = mapper.boarddetail(bno);
		
		model.addAttribute("vo",vo);
		
		return "board/detail";
	}
	
	
	 @GetMapping(value = "/")
	 public String boardlist (Model model) 
	 {
		 List<BoardVO> list = mapper.boardlist();
		  
		 model.addAttribute("list",list);
		  
		 return "main";
	 }
	 
	@PostMapping(value = "/board/write") 
	public String boardwrite (Model model, BoardVO vo) 
	{
		int write = mapper.boardwrite(vo);
		return "redirect:/";
	}
	 
	@GetMapping(value = "/board/delete")
	public String boarddelete (Model model, int bno)
	{
		int delete = mapper.boarddelete(bno);
		return "redirect:/";
	}
}
