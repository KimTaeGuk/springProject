package tq.spring.dao.impl;

import java.util.ArrayList;

import tq.spring.dto.replyDto;

public interface replyDaoImpl {
	
	//���� ����
	public ArrayList<replyDto> replyList(Integer boardNum, Integer commentNum);
	
	//���� ���� 
	public void replyInsert(Integer boardNum, Integer commentNum, Integer replyNum, String replyId, String replyContent);

	//���� ����
	public void replyDelete(Integer boardNum, Integer commentNum, Integer replyNum);
	
	//���� ���� �� ��ȣ �ø���
	public void replyNumUp(Integer boardNum, Integer commentNum, Integer replyNum);
	
	
	/////////////////////////////
	//��� ���� �� ���õ� ���� ����
	public void commentDel(Integer boardNum, Integer commentNum);
	//��� ���� �� ���� ��ȣ �ø���
	public void commentDelNumUp(Integer boardNum, Integer commentNum);
	/////////////////////////////
	
	
	
	//////////////////////////////
	//�Խ��� ������ ���õ� ���� ����
	public void boardDel(Integer boardNum);
	//�Խ��� ������ ���� ��ȣ �ø���
	public void boardDelNumUp(Integer boardNum);
	//////////////////////////////
	
}
