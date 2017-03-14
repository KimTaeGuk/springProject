<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.1.1.js"
  integrity="sha256-16cdPddA6VdVInumRGo6IbivbERE8p7CQR3HzTBuELA="
  crossorigin="anonymous"></script>
</head>
<body>
<h1>게시판 뷰 페이지입니다.</h1>
<a href="boardDelete?boardNum=${boardView.boardNum }">글 삭제</a>
<a href="boardModify?boardNum=${boardView.boardNum }">글 수정</a>
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

<form action="commentInsert" method="POST">
	<input type="hidden" value="${userId }" name="commentId"/>
	<input type="hidden" value="${boardView.boardNum }" name="boardNum"/>
	<textarea name="commentContent"></textarea>
	<input type="submit" value="전송"/>
</form>


<button onclick="commentList(${boardView.boardNum});">댓글 보기</button>
<!-- 댓글 -->
<div id="comment"></div>

<script>
	function commentList(boardNum){
		$.ajax({
			type:"GET",
			url:"commentList?boardNum="+boardNum,
			//data:$('form').serialize()
			data:{
				
			},
			dataType:"html",
			success:function(data){
				$("#comment").html(data);
			}
		});
	}
</script>
</body>
</html>