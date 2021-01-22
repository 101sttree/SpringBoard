package com.spring.board.Interface;

import java.util.List;

import com.spring.board.VO.BoardVO;
import com.spring.board.VO.PagingVO;
import com.spring.board.VO.SearchVO;

public interface boardMapper 
{
	public List<BoardVO> boardlist(SearchVO vo);
	public BoardVO boarddetail(int bno);
	public BoardVO boardlast();
	public int boardwrite(BoardVO vo);
	public int boardcount(SearchVO vo);
	public int boardmody(BoardVO vo);
	public int boarddelete(int bno);
	public int boardhit(int bno);
	public int boardanup(BoardVO vo);
	public int boardoriup(BoardVO vo);
	
}
