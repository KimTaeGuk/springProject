package tq.spring.dao.impl;

import tq.spring.dto.userDto;

public interface userDaoImpl {
	
	//ȸ������ ó�� �޼ҵ��Դϴ�.
	public void userInsert(String userId, String userPassword, String userEmail, String userName, String userBirth);
	
	//ȸ������ ���� �޼ҵ��Դϴ�.
	public void userModify(String userId, String userEmail, String userImg);

	//ȸ�� �̹��� ���� �޼ҵ��Դϴ�.
	public void userImgModify(String fileName);
	
	//ȸ�������� ������ �޼ҵ��Դϴ�.
	public userDto userModifyView(String userId);
	
	//ȸ��Ż�� �޼ҵ��Դϴ�.
	public void userDelete(String userId);
	
	//���ο� ��й�ȣ ������Ʈ �޼ҵ� �Դϴ�.
	public void userpasswordUpdate(String userId, String userName, String userPassword);
	
	//���ο� ��й�ȣ �߻� �޼ҵ� �Դϴ�.
	public String userNewPassword();
	
	//�̸����� �������� �޼ҵ��Դϴ�.
	public String userEmailSearch(String userId, String userName);
	
	//���̵� �ߺ�ó�� �޼ҵ��Դϴ�.
	public String userCheckId(String userId);
	
	//���̵� ã�� �޼ҵ��Դϴ�.
	public String userSearchId(String userName, String userBirth);
	
	//�̹��� ���ε� �޼ҵ��Դϴ�.
	public void userImgUpload(String userId, String fileName);

	//�̹��� �̸� ã�� �޼ҵ��Դϴ�.
	public String userImgView(String userId);

}
