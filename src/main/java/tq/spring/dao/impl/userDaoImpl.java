package tq.spring.dao.impl;

public interface userDaoImpl {
	public void userInsert(String userId, String userPassword, String userEmail, String userName, String userBirth);
	public void userModify(String userId);
	public void userDelete(String userId);
}
