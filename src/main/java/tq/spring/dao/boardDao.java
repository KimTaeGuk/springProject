package tq.spring.dao;

import java.util.ArrayList;

import tq.spring.dao.impl.boardDaoImpl;
import tq.spring.dto.boardDto;

public class boardDao implements boardDaoImpl {
	public boardDao() {
	// TODO Auto-generated constructor stub
	}
	@Override
	public Integer boardMax() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void boardDelete(Integer boardNum) {
	// TODO Auto-generated method stub
		
	}
	@Override
	public void boardInsert(int boardNum, String boardId, String boardSubject, String boardContent) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<boardDto> boardList() {
	// TODO Auto-generated method stub
		return null;
	} 
	@Override
	public void boardUpdate() {
	// TODO Auto-generated method stub
					
	}
	@Override
	public boardDto boardView(Integer boardNum) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void boardCount(Integer boardNum) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void boardDelUpdateNum(Integer boardNum) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void boardModify(Integer boardNum, String boardSubject, String boardContent) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ArrayList<boardDto> boardSearch(String keyword) {
		// TODO Auto-generated method stub
		return null;
	} 
}
