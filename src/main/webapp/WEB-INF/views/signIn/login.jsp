<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${param.error != null }">
	<p>
		Login NG!<br />
	</p>
	<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != NULL }">
		message:<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message }"/>
	</c:if>
</c:if>
<form name="lgoinForm" action="<c:url value="j_spring_security_check"/>" method="POST">
	<table border="1">
		<tr>
			<td>User</td>
			<td><input type="text" name="userId"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="text" name="userPassword"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="전송"/></td>
		</tr>
	</table>
	
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
		
</form>
</body>
</html>