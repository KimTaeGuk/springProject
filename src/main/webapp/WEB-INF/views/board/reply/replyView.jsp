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
<form action="replyInsert" method="POST">
	<input type="hidden" name="boardNum" value="${boardNum}"/>
	<input type="hidden" name="commentNum" value="${commentNum }"/>
	<input type="hidden" name="replyId" value="${userId }"/>
	<textarea name="replyContent"></textarea>
	
	<input type="submit" value="전송"/>
</form>
	<table border="1">
		<c:choose>
			<c:when test="${fn:length(replyList)!=0 }">		
				<c:forEach items="${replyList }" var="list">
					<tr>
						<td>${list.boardNum }</td>
						<td>${list.commentNum }</td>
						<td>${list.replyNum }</td>
						<td>${list.replyId }</td>
						<td><div id="commentReply${list.replyNum }">${list.replyContent }</div></td>
						<td>${list.replyDate }</td>
						<td><span onclick="replyModify(${list.boardNum},${list.commentNum },${list.replyNum });">수정</span></td>
						<td><a href="replyDelete?boardNum=${list.boardNum }&&commentNum=${list.commentNum}&&replyNum=${list.replyNum}">삭제</a></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				달려있는 리플이 없습니다.
			</c:otherwise>
		</c:choose>		
	</table>
	
	<script>
		function replyModify(boardNum, commentNum, replyNum){
			$.ajax({
				type:"GET",
				url:"replyModify?boardNum="+boardNum+"&&commentNum="+commentNum+"&&replyNum="+replyNum,
				data:{
					
				},
				dataType:"html",
				success:function(data){
					$("#commentReply"+replyNum).html(data);
				}
			});
		}
	</script>
</body>
</html>