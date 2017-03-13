<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<title>Main Page</title>
</head>
<body>
<a href="signUp">회원가입</a><br>
<a href="welcome">로그인</a>
<%
	Connection conn=null;
	try{
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/mysql");
		conn=ds.getConnection();
		
		out.println("연결 되었습니다");
	}	catch(Exception e){
			out.println("연결에 실패하였습니다.");
			e.printStackTrace();
	}
%>

</body>
</html>
