<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원 정보 수정 페이지입니다.</h1>
<form action="" method="POST">
	<input type="text" name="userId" value="${userId }"/>
	<table>
		<tr>
			<td>이메일</td>
			<td><input type="text" value="${userDto.userEmail }" name="userEmail"/></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><input type="file" name="userImg"></td>
		</tr>
	</table>
</form>
</body>
</html>