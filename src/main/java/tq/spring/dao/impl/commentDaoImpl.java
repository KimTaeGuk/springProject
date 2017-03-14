package tq.spring.dao.impl;

import java.util.ArrayList;

import tq.spring.dto.commentDto;

public interface commentDaoImpl {
	
	//��� ���� ��������
	public commentDto commentModifyView(Integer boardNum, Integer commentNum);
	
	//��� ����
	public Integer commentMax(Integer boardNum);
	
	//��� ����Ʈ
	public ArrayList<commentDto> commentList(Integer boardNum);
	
	// ��� ����
	public commentDto commentView(Integer boardNum, Integer commentNum);
	
	// ��� ����
	public void commentInsert(Integer boardNum, Integer commentNum, String commentId, String commentContent);
	
	// ��� ����
	public void commentDelete(Integer boardNum, Integer commentNum);
	
	// ��� ����
	public void commentModify(Integer boardNum, Integer commentNum, String commentContent);
	
	// ��� ��ȣ �ø���
	public void commentNumUp(Integer boardNum, Integer commentNum);
	
	// �Խ��� ������ ��� �Բ� ���� ����
	public void boardDel(Integer boardNum);
	
	// �ٸ� �Խ��� ������ ��۵� boardNum �÷��ֱ�
	public void boardNumUp(Integer boardNum);

}
