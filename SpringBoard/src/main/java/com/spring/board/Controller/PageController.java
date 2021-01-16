package com.spring.board.Controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController 
{
	
	
	
	@GetMapping(value = "/login")
	public String login(Locale locale, Model model) 
	{
		
		return "user/login";
	}
	
	@GetMapping(value = "/join")
	public String join(Locale locale, Model model) 
	{
		
		return "user/join";
	}
	
	@GetMapping(value = "/write")
	public String write(Locale locale, Model model) 
	{
		
		return "board/write";
	}
	
	
}
