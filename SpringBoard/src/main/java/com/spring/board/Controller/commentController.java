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
import com.spring.board.VO.PagingVO;

@Controller
public class commentController 
{
	@Inject
	commentMapper mapper;
	
	//��� �ҷ�����
	@GetMapping(value = "/commentlist")
	public void commentlist
	(
		Model model,
		CommentVO 	commentVO,
		PagingVO 	pagingVO,
		String 		nowPage,
		String 		cntPerPage,
		HttpServletRequest request,
		HttpServletResponse response
	)
	throws Exception
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter pWriter = response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		
		
		try 
		{
			int total = mapper.commentcount(commentVO);
			
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
			//�޾ƿ� ���� ����¡��ü�� �־��ش�.
			commentVO.setStart(pagingVO.getStart());
			//��� ����� �ҷ����µ� ���� start ���� ����¡��ü���� ���´�.
			List<CommentVO> list = mapper.commentlist(commentVO);
			
			if(list.size() > 0)
			{
				jObject.add("list", 	gson.toJsonTree(list));
				jObject.add("paging", 	gson.toJsonTree(pagingVO));
				jObject.add("check", 	gson.toJsonTree("yes"));
			}
			if(list.size() == 0)
			{
				jObject.add("check", gson.toJsonTree("no"));
			}
		} 
		catch(Exception e) 
		{
			System.out.println(e.toString());
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
		CommentVO commentVO,
		HttpServletRequest request,
		HttpServletResponse response	
	)
	{
		int commentwrite = mapper.commentwrite(commentVO);
	}
	

}
