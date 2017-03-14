package tq.spring.dao.impl;

import java.util.ArrayList;

import tq.spring.dto.commentDto;

public interface commentDaoImpl {
	
	//댓글 내용 가져오기
	public commentDto commentModifyView(Integer boardNum, Integer commentNum);
	
	//댓글 갯수
	public Integer commentMax(Integer boardNum);
	
	//댓글 리스트
	public ArrayList<commentDto> commentList(Integer boardNum);
	
	// 댓글 보기
	public commentDto commentView(Integer boardNum, Integer commentNum);
	
	// 댓글 삽입
	public void commentInsert(Integer boardNum, Integer commentNum, String commentId, String commentContent);
	
	// 댓글 삭제
	public void commentDelete(Integer boardNum, Integer commentNum);
	
	// 댓글 수정
	public void commentModify(Integer boardNum, Integer commentNum, String commentContent);
	
	// 댓글 번호 올리기
	public void commentNumUp(Integer boardNum, Integer commentNum);
	
	// 게시판 삭제시 댓글 함께 전부 삭제
	public void boardDel(Integer boardNum);
	
	// 다른 게시판 삭제시 댓글들 boardNum 올려주기
	public void boardNumUp(Integer boardNum);

}
