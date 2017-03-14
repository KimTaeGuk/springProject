package tq.spring.dao;

import java.util.ArrayList;

import tq.spring.dao.impl.commentDaoImpl;
import tq.spring.dto.commentDto;

public class commentDao implements commentDaoImpl {

	public commentDao() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public commentDto commentModifyView(Integer boardNum, Integer commentNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer commentMax(Integer boardNum) {
		// TODO Auto-generated method stub
		return null;
	} 
	
	@Override
	public ArrayList<commentDto> commentList(Integer boardNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public commentDto commentView(Integer boardNum, Integer commentNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commentInsert(Integer boardNum, Integer commentNum, String commentId, String commentContent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commentDelete(Integer boardNum, Integer commentNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commentModify(Integer boardNum, Integer commentNum, String commentContent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commentNumUp(Integer boardNum, Integer commentNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardDel(Integer boardNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardNumUp(Integer boardNum) {
		// TODO Auto-generated method stub

	}

}
