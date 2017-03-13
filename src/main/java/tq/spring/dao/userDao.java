package tq.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tq.spring.dao.impl.userDaoImpl;
import java.util.Random;

@Repository
public class userDao implements userDaoImpl {
	
	@Autowired
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public userDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void userInsert(String userId, String userPassword, String userEmail, String userName, String userBirth) {
		// TODO Auto-generated method stub

	}

	@Override
	public void userModify(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void userDelete(String userId) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void userpasswordUpdate(String userId, String userName, String userPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String userNewPassword() {
		// TODO Auto-generated method stub
		Random rnd=new Random();
		StringBuffer buf=new StringBuffer();
		
		for(int i=0; i<9; i++){
			if(rnd.nextBoolean()){
					buf.append((char)((int)rnd.nextInt(26)+97));
			}	else {
					buf.append(rnd.nextInt(10));
			}
		}
		
		return buf.toString();
	}
	
	@Override
	public String userEmailSearch(String userId, String userName){
		// TODO Auto-generated method stub
		return null;
	}
}
