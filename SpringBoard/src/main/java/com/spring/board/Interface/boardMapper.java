package com.spring.board.Interface;

import java.util.List;

import com.spring.board.VO.BoardVO;

public interface boardMapper 
{
	public List<BoardVO> boardlist();
	public int boardwrite(BoardVO vo);
	public BoardVO boarddetail(int bno);
		
	
}
