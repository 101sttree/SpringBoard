package com.spring.board.Interface;

import java.util.List;

import com.spring.board.VO.CommentVO;

public interface commentMapper 
{
	public List<CommentVO> commentlist(CommentVO commentVO);
	public int commentwrite(CommentVO commentVO);
	public int commentcount(CommentVO commentVO);
}
