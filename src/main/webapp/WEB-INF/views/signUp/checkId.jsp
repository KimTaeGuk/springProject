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
	<c:choose>
		<c:when test="${!empty userCheckId}">
			중복되는 아이디입니다.
			<input type="text" id="existsId" value="false"/>
		</c:when>
		<c:otherwise>
			사용가능한 아이디입니다.
			<input type="text" id="existsId" value="true"/>
		</c:otherwise>
	</c:choose>
	
	<script>
		$(document).ready(function(){
			var existsId=$("#existsId").val();
			$("#existsId", opener.document).val(existsId);
		});
	</script>
</body>
</html>