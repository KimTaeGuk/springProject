<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<title>Main Page</title>
</head>
<body>
<a href="signUp">회원가입</a><br/>
<a href="welcome">로그인</a><br/>
<a href="mailView">메일 보내기</a><br/>
<a href="searchId">아이디 찾기</a><br/>
아이디 : ${userId }<br/>
<a href="<c:url value="j_spring_security_logout" />" > Logout</a><br/>
<a href="boardList">게시판 리스트</a><br/>
<a href="UserImgUpload">이미지 설정</a><br/>
<a href="userModify">회원 수정</a>
<a href="userImgModify">회원 이미지 수정</a><br/>
<img src="${pageContext.request.contextPath }/resources/images/userImg/${userImg}" width="150" height="150">
</body>
</html>
