package tq.spring.dao.impl;

import java.util.ArrayList;

import tq.spring.dto.boardDto;

public interface boardDaoImpl {

	//�Խ��� �� ���� �����Դϴ�.
	public Integer boardMax();
	
	//�Խ��� ����Ʈ �޼ҵ��Դϴ�.
	public ArrayList<boardDto> boardList();
	
	//�Խ��� �۾��� �޼ҵ��Դϴ�.
	public void boardInsert(int boardNum, String boardId, String boardSubject, String boardContent);
	
	//�Խ��� �� ���� �޼ҵ��Դϴ�
	public void boardDelete();
	
	//�Խ��� �� ���� �޼ҵ��Դϴ�.
	public void boardUpdate();
	
	//�Խ��� �� ���� �޼ҵ��Դϴ�.
	public boardDto boardView(Integer boardNum);
	
	//�� ī��Ʈ(��ȸ ��) �ø��� �޼ҵ��Դϴ�.
	public void boardCount(Integer boardNum);
	
}
