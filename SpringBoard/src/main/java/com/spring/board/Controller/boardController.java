package com.spring.board.Controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class boardController 
{
	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public String detail(Locale locale, Model model) 
	{
		
		return "board/detail";
	}
	
	@PostMapping
	public void boardlist(Model model)
	{
		
	}
	
}
