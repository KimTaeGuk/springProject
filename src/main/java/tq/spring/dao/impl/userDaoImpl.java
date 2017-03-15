package tq.spring.dao.impl;

public interface userDaoImpl {
	
	//회원가입 처리 메소드입니다.
	public void userInsert(String userId, String userPassword, String userEmail, String userName, String userBirth);
	
	//회원정보 수정 메소드입니다.
	public void userModify(String userId);
	
	//회원탈퇴 메소드입니다.
	public void userDelete(String userId);
	
	//새로운 비밀번호 업데이트 메소드 입니다.
	public void userpasswordUpdate(String userId, String userName, String userPassword);
	
	//새로운 비밀번호 발생 메소드 입니다.
	public String userNewPassword();
	
	//이메일을 꺼내오는 메소드입니다.
	public String userEmailSearch(String userId, String userName);
	
	//아이디 중복처리 메소드입니다.
	public String userCheckId(String userId);
	
	//아이디 찾기 메소드입니다.
	public String userSearchId(String userName, String userBirth);
}
