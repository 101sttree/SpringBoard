package com.spring.board.Controller;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.spring.board.VO.UserVO;

@Controller
public class commentController 
{
	@Inject
	commentMapper mapper;
	
	//댓글 불러오기
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
		HttpSession session = request.getSession();
		
		try 
		{
			System.out.println(nowPage);
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
			//받아온 값을 페이징객체에 넣어준다.
			commentVO.setStart(pagingVO.getStart());
			//댓글 목록을 불러오는데 사용될 start 값을 페이징객체에서 얻어온다.
			List<CommentVO> list = mapper.commentlist(commentVO);
			UserVO userVO = (UserVO)session.getAttribute("user");
			if(list.size() > 0)
			{
				jObject.add("list", 	gson.toJsonTree(list));
				jObject.add("paging", 	gson.toJsonTree(pagingVO));
				jObject.add("user", 	gson.toJsonTree(userVO));
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
	
	//댓글 작성
	@PostMapping(value = "/commentwrite")
	public void commentwrite
	(
		Model model,
		CommentVO commentVO,
		HttpServletRequest request,
		HttpServletResponse response	
	)
	{
		/*
		 * for (int i = 0; i < 50; i++) { int commentwrite =
		 * mapper.commentwrite(commentVO); }
		 */
		int commentwrite = mapper.commentwrite(commentVO);
	}
	

}
