package tq.spring.dao;

import java.util.ArrayList;

import tq.spring.dao.impl.replyDaoImpl;
import tq.spring.dto.replyDto;

public class replyDao implements replyDaoImpl {

	public replyDao() {
		// TODO Auto-generated constructor stub
	} 

	@Override
	public ArrayList<replyDto> replyList(Integer boardNum, Integer commentNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer replyMax(Integer boardNum, Integer commentNum) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void replyInsert(Integer boardNum, Integer commentNum, Integer replyNum, String replyId,
			String replyContent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replyDelete(Integer boardNum, Integer commentNum, Integer replyNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replyNumUp(Integer boardNum, Integer commentNum, Integer replyNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commentDel(Integer boardNum, Integer commentNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commentDelNumUp(Integer boardNum, Integer commentNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardDel(Integer boardNum) {
		// TODO Auto-generated method stub

	}

	@Override
	public void boardDelNumUp(Integer boardNum) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void replyModifyProc(Integer boardNum, Integer commentNum, Integer replyNum, String replyContent) {
		// TODO Auto-generated method stub
		
	} 
	
	@Override
	public replyDto replyModifyView(Integer boardNum, Integer commentNum, Integer replyNum) {
		return null;
		// TODO Auto-generated method stub
		
	} 

}
