<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>게시판 검색</h1>
<table border="1">
	<thead>
		<tr>
			<th>boardNum</th>
			<th>boardId</th>
			<th>boardSubject</th>
			<th>boardContent</th>
			<th>boardDate</th>
			<th>boardCount</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(boardSearch)!=0 }">
				<c:forEach items="${boardSearch }" var="list">
					<tr>
							<td>${list.boardNum }</td>
							<td>${list.boardId }</td>
							<td><a href="boardView?boardNum=${list.boardNum }">${list.boardSubject }</a></td>
							<td>${list.boardContent }</td>
							<td>${list.boardDate }</td>
							<td>${list.boardCount }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				게시판에 검색 글이 없습니다.
			</c:otherwise>
		</c:choose>
	</tbody>
	<tbody>
		<form action="boardSearch" method="GET">
			<tr>
				<td colspan="6" style="text-align: center;"><input type="text" name="keyword"/><input type="submit" value="검색"/></td>
			</tr>
		</form>
	</tbody>
</table>
</body>
</html>