package com.spring.board.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.spring.board.Interface.commentMapper;
import com.spring.board.VO.CommentVO;

@Controller
public class commentController 
{
	@Inject
	commentMapper mapper;
	
	@GetMapping(value = "/commentlist")
	public void commentlist
	(
		Model model,
		int bno,
		HttpServletRequest request,
		HttpServletResponse response
	)
	throws Exception
	{
		PrintWriter pWriter = response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		
		try 
		{
			List<CommentVO> list = mapper.commentlist(bno);
			jObject.add("list", gson.toJsonTree(list));
		} 
		catch(Exception e) 
		{
			jObject = new JsonObject();
			jObject.add("cheack", 	gson.toJsonTree("fail"));
			jObject.add("erroe", 	gson.toJsonTree(e.toString()));
		}
		finally 
		{
			pWriter.write(gson.toJson(jObject));
		}
		
	}
	
	@PostMapping(value = "/commentwrite")
	public void commentwrite
	(
		Model model,
		int bno,
		String ctext,
		HttpServletRequest request,
		HttpServletResponse response	
	)
	{
		
	}
}
