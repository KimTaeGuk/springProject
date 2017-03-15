package tq.spring.dao.impl;

import java.util.ArrayList;

import tq.spring.dto.boardDto;

public interface boardDaoImpl {

	//게시판 총 갯수 세기입니다.
	public Integer boardMax();
	
	//게시판 리스트 메소드입니다.
	public ArrayList<boardDto> boardList();
	
	//게시판 글쓰기 메소드입니다.
	public void boardInsert(int boardNum, String boardId, String boardSubject, String boardContent);
	
	//게시판 글 삭제 메소드입니다
	public void boardDelete(Integer boardNum);
	
	//글 삭제시 번호들을 올려주는 업데이트 메소드입니다.
	public void boardDelUpdateNum(Integer boardNum);
	
	//게시판 글 수정 메소드입니다.
	public void boardUpdate();
	
	//게시판 글 보기 메소드입니다.
	public boardDto boardView(Integer boardNum);
	
	//글 카운트(조회 수) 올리는 메소드입니다.
	public void boardCount(Integer boardNum);
	
	//게시판 수정 처리 메소드입니다.
	public void boardModify(Integer boardNum, String boardSubject, String boardContent);
	
	//게시판 검색 매소드입니다.
	public ArrayList<boardDto> boardSearch(String keyword);
	
}
