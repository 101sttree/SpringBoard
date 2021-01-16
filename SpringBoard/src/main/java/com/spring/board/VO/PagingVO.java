package com.spring.board.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO 
{
		
	private int nowPage, 	//���� ������
				// 0 0 1 0 0
				startPage, 	//���� ������
				// 1 0 0 0 0 
				endPage, 	//�� ������
				// 0 0 0 0 1
				total, 		//��ü �� ����
				// 100��
				cntPerPage, //�������� �� ����
				// 10��
				lastPage, 	//������ ������
				start, 		//sql�� ����� �� ���� ��ȣ
				end;		//�� �� ��ȣ

	private int cntPage = 5;
	
	public PagingVO() {
	}
	public PagingVO(int total, int nowPage, int cntPerPage) {
		//�Խñ� �� ����, ���� ������, �������� �� ���� 5
		// ��ü���� 10���̰� ���� �������� 1�̰� �������� 
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
	// ���� ������ ������ ���
	public void calcLastPage(int total, int cntPerPage) 
	{
		setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
		//100 / 5 = 20
	}
	
	// ����, �� ������ ���
	public void calcStartEndPage(int nowPage, int cntPage) {
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		// 10 / 5 * 5 = 10 
		if (getLastPage() < getEndPage()) 
		{
			//20 < 10
			setEndPage(getLastPage());
			//���� �������� ������ ������ ���� ��ü �������� ������ ������ ������ ������
			//���� �������� ������ ������ ���� ��ü �������� ������ ������ �����Ѵ�.
		}
		setStartPage(getEndPage() - cntPage + 1);
		//���� �������� ���� ���� ȭ���� ������ ���������� -5�� �ϰ� 1�� ���Ѱ����� �����Ѵ�.
		// 10 - 5 + 1 = 6
		if (getStartPage() < 1) 
		{
			setStartPage(1);
		}
		//���۹�ȣ�� 0�ϰ�� �⺻���� 1�� �����Ѵ�.
	}
	
	// DB �������� ����� start, end�� ���
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		//����Ŭ�� ���� ��
		setStart(getEnd() - cntPerPage + 1);
		//limit ���� ��
	}
}
