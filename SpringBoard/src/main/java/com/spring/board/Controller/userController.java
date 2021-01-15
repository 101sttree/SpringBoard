package com.spring.board.Controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.spring.board.Interface.userMapper;
import com.spring.board.VO.UserVO;

@Controller
public class userController 
{
	@Autowired
	userMapper mapper;
	
	@PostMapping(value = "/joinok")
	public String userjoin(Model model, UserVO vo)
	{
		
		int join = mapper.userjoin(vo);
		
		return "redirect:/login";
	}
	
	@PostMapping(value = "/login")
	public void userlogin
	(
			Model model, 
			HttpServletRequest 	request,
			HttpServletResponse response,
			String id,
			String Pw
			
	) throws Exception
	{
		
		PrintWriter pw 		= response.getWriter();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		JsonObject 	jo 		= new JsonObject();
		HttpSession Session = request.getSession();
		
		try 
		{
			
			
			UserVO login = mapper.userlogin(id);
			
			if(login != null)
			{
				if(login.getPw().equals(Pw))
				{
					jo.add("result", gson.toJsonTree("loginok"));
					Session.setAttribute("user", Pw);
				}
				if (!login.getPw().equals(Pw))
				{
					System.out.println("로그인 비밀번호       "+Pw);
					System.out.println("불러온 유저 비밀번호"+login.getPw());
					jo.add("result", gson.toJsonTree("pwfail"));
				}
			}
			if(login == null) 
			{
				jo.add("result", gson.toJsonTree("idfail"));
			}
			
			jo.add("check", gson.toJsonTree("success"));
		}
		catch (Exception e) 
		{
			jo = new JsonObject();
			jo.add("check", gson.toJsonTree("fail"));
			jo.add("error", gson.toJsonTree(e.toString()));
		}
		finally 
		{
			pw.write(gson.toJson(jo));
		}
		
		
		
	}
	
}
