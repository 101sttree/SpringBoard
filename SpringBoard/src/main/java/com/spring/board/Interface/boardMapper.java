package com.spring.board.Interface;

import java.util.List;

import com.spring.board.VO.BoardVO;

public interface boardMapper 
{
	public List<BoardVO> boardlist();
	public BoardVO boarddetail(int bno);
	public int boardwrite(BoardVO vo);
	public int boarddelete(int bno);
}
