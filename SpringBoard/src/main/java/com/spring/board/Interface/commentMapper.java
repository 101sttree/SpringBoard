package com.spring.board.Interface;

import java.util.List;

import com.spring.board.VO.CommentVO;

public interface commentMapper 
{
	public List<CommentVO> commentlist(int bno);
}
