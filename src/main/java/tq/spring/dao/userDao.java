package tq.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tq.spring.dao.impl.userDaoImpl;


@Repository
public class userDao implements userDaoImpl {
	
	@Autowired
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
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

}
