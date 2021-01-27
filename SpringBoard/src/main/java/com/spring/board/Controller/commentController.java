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
import com.spring.board.VO.BoardVO;
import com.spring.board.VO.CommentVO;
import com.spring.board.VO.PagingVO;
import com.spring.board.VO.UserVO;

@Controller
public class commentController 
{
	@Inject
	commentMapper mapper;
//=====================================================================================	
//댓글 불러오기
//=====================================================================================		
	
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
		response.setContentType("application/json");
		PrintWriter pWriter = response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		
		try 
		{
			int total = mapper.commentcount(commentVO);
			if (nowPage == null && cntPerPage == null) 
			{
				nowPage = "1";
				cntPerPage = "10";
			}
			if (nowPage == null) 
			{
				nowPage = "1";
			}
			if (cntPerPage == null) 
			{ 
				cntPerPage = "10";
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
			jObject = new JsonObject();
			jObject.add("cheack", 	gson.toJsonTree("fail"));
			jObject.add("erroe", 	gson.toJsonTree(e.toString()));
		}
		finally 
		{
			pWriter.write(gson.toJson(jObject));
		}
	}
//=====================================================================================	
//댓글 작성	
//=====================================================================================		
	
	@PostMapping(value = "/commentwrite")
	public void commentwrite
	(
		Model model,
		CommentVO commentVO,
		HttpServletRequest request,
		HttpServletResponse response	
	)
	{
		if(commentVO.getCno() > 0)
		{	
			//현재 작성된 마지막 글의 순서값을 가져온다.
			CommentVO commentgrdmax = mapper.commentgrdmax(commentVO);
			//새로 작성되는 글의 순서값을 해당 글 그룹의 마지막글의 순서값에 + 1 값으로 설정
			commentVO.setGroupord(commentgrdmax.getGroupord()+1);
			//새로 작성되는 글의 그룹값을 해당 글 그룹의 마지막글의 그룹번호와 같게 해줌
			commentVO.setOrigino(commentgrdmax.getOrigino());
			//원글의 답글이기 때문에 층수 1로 설정
			commentVO.setGrouplayer(1);
		}
		
		//댓글 작성 구문
		int commentwrite = mapper.commentwrite(commentVO);
		//마지막 댓글의 정보를 얻어옴
		CommentVO commentlast = mapper.commentlast();
		//댓글의 그룹번호가 0일경우 즉 답글이 아닌 일반 댓글일 경우
		if(commentVO.getOrigino() == 0)
		{
			//가장 최신 댓글, 즉 본인의 댓글 번호를 그룹 번호로 설정하는 구문
			mapper.commentoriup(commentlast);
		}
	}
//=====================================================================================
//댓글 수정
//=====================================================================================	
	
	@PostMapping(value = "/commentmody")
	public void commentmody
	(
		CommentVO commentVO,
		HttpServletRequest request,
		HttpServletResponse response
	)
	throws Exception
	{
		response.setContentType("application/json");
		PrintWriter pWriter = response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		UserVO 		userVO 	= (UserVO)session.getAttribute("user");
		try 
		{
			if(userVO.getUno() == commentVO.getUno())
			{
				int 		cmody 		= mapper.commentmody(commentVO);
				CommentVO 	commentinfo = mapper.commentinfo(commentVO.getCno());
				jObject.add("check", gson.toJsonTree("ok"));
				jObject.add("cinfo", gson.toJsonTree(commentinfo));
			}
			if(userVO.getUno() != commentVO.getUno())
			{
				CommentVO 	commentinfo = mapper.commentinfo(commentVO.getCno());
				jObject.add("check", gson.toJsonTree("no"));
				jObject.add("cinfo", gson.toJsonTree(commentinfo));
			}
		}
		catch (Exception e) 
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
//=====================================================================================
//댓글 삭제
//=====================================================================================	
	
	@PostMapping(value = "/commentdelete")
	public void commentdeleteone
	(
		int cno,
		HttpServletRequest request,
		HttpServletResponse response
	)
	throws Exception
	{
		
		response.setContentType("application/json");
		PrintWriter pWriter = response.getWriter();
		JsonObject 	jObject = new JsonObject();
		Gson 		gson 	= new GsonBuilder().setPrettyPrinting().create();
		HttpSession session = request.getSession();
		UserVO 		userVO 	= (UserVO)session.getAttribute("user");
		CommentVO 	commentVO = mapper.commentinfo(cno);
		try 
		{
			if(userVO.getUno() == commentVO.getUno())
			{
				if(commentVO.getGrouplayer() == 1)
				{
					int cdelete = mapper.commentdeleteone(cno);
				}
				if(commentVO.getGrouplayer() == 0)
				{
					int cdeleteori = mapper.commentdeleteori(cno);
				}
				jObject.add("check", gson.toJsonTree("ok"));
			}
			if(userVO.getUno() != commentVO.getUno())
			{
				jObject.add("check", gson.toJsonTree("no"));
			}
		}
		catch (Exception e) 
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
	

}
