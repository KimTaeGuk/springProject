package tq.spring.dao.impl;

public interface userDaoImpl {
	
	//ȸ������ ó�� �޼ҵ��Դϴ�.
	public void userInsert(String userId, String userPassword, String userEmail, String userName, String userBirth);
	
	//ȸ������ ���� �޼ҵ��Դϴ�.
	public void userModify(String userId);
	
	//ȸ��Ż�� �޼ҵ��Դϴ�.
	public void userDelete(String userId);
	
	//���ο� ��й�ȣ ������Ʈ �޼ҵ� �Դϴ�.
	public void userpasswordUpdate(String userId, String userName, String userPassword);
	
	//���ο� ��й�ȣ �߻� �޼ҵ� �Դϴ�.
	public String userNewPassword();
	
	//�̸����� �������� �޼ҵ��Դϴ�.
	public String userEmailSearch(String userId, String userName);
}
