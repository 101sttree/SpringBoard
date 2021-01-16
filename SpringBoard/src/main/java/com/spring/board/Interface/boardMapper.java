package com.spring.board.Interface;

import java.util.List;

import com.spring.board.VO.BoardVO;
import com.spring.board.VO.PagingVO;

public interface boardMapper 
{
	public List<BoardVO> boardlist(PagingVO bo);
	public BoardVO boarddetail(int bno);
	public int boardwrite(BoardVO vo);
	public int boardcount();
	public int boardmody(BoardVO vo);
	public int boarddelete(int bno);
}
