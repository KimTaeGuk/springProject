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
<h1>게시판 뷰 페이지입니다.</h1>

<table border="1">
	<tr>
		<th>boardNum</th>
		<th>boardId</th>
		<th>boardSubject</th>
		<th>boardContent</th>
		<th>boardDate</th>
		<th>boardCount</th>
	</tr>
	<tr>
		<td>${boardView.boardNum }</td>
		<td>${boardView.boardId }</td>
		<td>${boardView.boardSubject }</td>
		<td>${boardView.boardContent }</td>
		<td>${boardView.boardDate }</td>
		<td>${boardView.boardCount }</td>
	</tr>
</table>
</body>
</html>