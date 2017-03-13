<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="mailSend" method="POST">
	<table border="1">
		<tr>
			<th>userId</th>
			<th>userName</th>
		</tr>
		<tr>
			<td><input type="text" name="userId"/></td>
			<td><input type="text" name="userName"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="submit"/></td>
		</tr>
	</table>
	
	<!-- csrf -->
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	
</form>
</body>
</html>