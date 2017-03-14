package tq.spring.dao.impl;

import java.util.ArrayList;

import tq.spring.dto.replyDto;

public interface replyDaoImpl {
	
	//리플 삽입
	public ArrayList<replyDto> replyList(Integer boardNum, Integer commentNum);
	
	//리플 수정 
	public void replyInsert(Integer boardNum, Integer commentNum, Integer replyNum, String replyId, String replyContent);

	//리플 삭제
	public void replyDelete(Integer boardNum, Integer commentNum, Integer replyNum);
	
	//리플 삭제 시 번호 올리기
	public void replyNumUp(Integer boardNum, Integer commentNum, Integer replyNum);
	
	
	/////////////////////////////
	//댓글 삭제 시 리플도 같이 삭제
	public void commentDel(Integer boardNum, Integer commentNum);
	//댓글 삭제 시 리플 번호 올리기
	public void commentDelNumUp(Integer boardNum, Integer commentNum);
	/////////////////////////////
	
	
	
	//////////////////////////////
	//게시판 삭제시 리플도 같이 삭제
	public void boardDel(Integer boardNum);
	//게시판 삭제시 리플 번호 올리기
	public void boardDelNumUp(Integer boardNum);
	//////////////////////////////
	
}
