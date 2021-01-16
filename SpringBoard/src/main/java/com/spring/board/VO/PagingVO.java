package com.spring.board.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO 
{
		
	private int nowPage, 	//현재 페이지
				// 0 0 1 0 0
				startPage, 	//시작 페이지
				// 1 0 0 0 0 
				endPage, 	//끝 페이지
				// 0 0 0 0 1
				total, 		//전체 글 갯수
				// 100개
				cntPerPage, //페이지당 글 갯수
				// 10개
				lastPage, 	//마지막 페이지
				start, 		//sql에 사용할 글 시작 번호
				end;		//글 끝 번호

	private int cntPage = 5;
	
	public PagingVO() {
	}
	public PagingVO(int total, int nowPage, int cntPerPage) {
		//게시글 총 갯수, 현재 페이지, 페이지당 글 갯수 5
		// 전체글이 10개이고 현재 페이지가 1이고 페이지당 
		setNowPage(nowPage);
		// 10
		setCntPerPage(cntPerPage);
		// 5
		setTotal(total);
		// 100
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage());
	}
	// 제일 마지막 페이지 계산
	public void calcLastPage(int total, int cntPerPage) 
	{
		setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
		//100 / 5 = 20
	}
	
	// 시작, 끝 페이지 계산
	public void calcStartEndPage(int nowPage, int cntPage) {
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		// 10 / 5 * 5 = 10 
		if (getLastPage() < getEndPage()) 
		{
			//20 < 10
			setEndPage(getLastPage());
			//현재 페이지의 마지막 페이지 값이 전체 페이지의 마지막 페이지 값보다 높으면
			//현재 페이지의 마지막 페이지 값을 전체 페이지의 마지막 값으로 설정한다.
		}
		setStartPage(getEndPage() - cntPage + 1);
		//시작 페이지의 값을 현재 화면의 마지막 페이지에서 -5를 하고 1을 더한값으로 설정한다.
		// 10 - 5 + 1 = 6
		if (getStartPage() < 1) 
		{
			setStartPage(1);
		}
		//시작번호가 0일경우 기본값을 1로 설정한다.
	}
	
	// DB 쿼리에서 사용할 start, end값 계산
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		//오라클용 종료 값
		setStart(getEnd() - cntPerPage + 1);
		//limit 시작 값
	}
}
