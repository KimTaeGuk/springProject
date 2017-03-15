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
<h1>글 쓰기 페이지입니다.</h1>

<form action="boardWriteProc" method="POST">
	<input type="text" value="${userId }" name="boardId"/>
	<table>
		<tr>
			<td>제목</td>
			<td><input type="text" name="boardSubject"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="boardContent"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="전송" /></td>
		</tr>
	</table>
</form>
</body>
</html>