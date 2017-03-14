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
<form action="commentModify" method="POST">
	<input type="hidden" value="${comment.boardNum}" name="boardNum"/>
	<input type="hidden" value="${comment.commentNum }" name="commentNum"/>
	<textarea name="commentContent">${comment.commentContent }</textarea>
	<input type="submit" value="전송"/>
</form>
</body>
</html>