<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<table border="1">
	<c:choose>
		<c:when test="${fn:length(commentList)!=0 }">	
			<c:forEach items="${commentList }" var="list">
				<tr>
					<td>${list.commentNum }</td>
					<td>${list.commentId }</td>
					<td><div id="comment${list.commentNum }">${list.commentContent }</div></td>
					<td>${list.commentDate }</td>
					<td><span onclick="commentModify(${list.boardNum },${list.commentNum });">수정</span></td>
					<td><a href="commentDelete?boardNum=${list.boardNum }&&commentNum=${list.commentNum}" >삭제</a></td>
					<td><span onclick="replyList(${list.boardNum},${list.commentNum });">대댓글 보기</span></td>
				</tr>
				<tr>
					<td colspan="7"><div id="reply${list.commentNum }">a</div></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="7">달려있는 댓글이 없습니다.</td>
			</tr>
		</c:otherwise>	
	</c:choose>
</table>
<script>
	function commentModify(boardNum ,commentNum){
		$.ajax({
			type:"GET",
			url:"commentModify?boardNum="+boardNum+"&&commentNum="+commentNum,
			data:{
				
			},
			dataType:"html",
			success:function(data){
				$("#comment"+commentNum).html(data);
			}	
		});
	}
	
	function replyList(boardNum, commentNum){
		$.ajax({
			type:"GET",
			url:"replyList?boardNum="+boardNum+"&&commentNum="+commentNum,
			data:{
				
			},
			dataType:"html",
			success:function(data){
				$("#reply"+commentNum).html(data);
			}
		});
	}
</script>
</body>
</html>