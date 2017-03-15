<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value='/resources/js/jquery-3.1.1.min.js'/>"></script>
<script>
$(document).ready(function(){
	var x=document.getElementById("day");
	x.options.length=0;
	for(var day=1; day<=31;day++){
		var option=document.createElement('option');
		option.text=day;
		option.value=day;
		x.add(option);
	}
});

function appendDay(Day){
		var x=document.getElementById("day");
		x.options.length=0;
		switch(Day){
			case "2":
				for(var day=1; day<=29;day++){
					var option=document.createElement('option');
					option.text=day;
					option.value=day;
					x.add(option);
				}
				break;
			case "4":
			case "6":
			case "9":
			case "11":
				for(var day=1; day<=30;day++){
					var option=document.createElement('option');
					option.text=day;
					option.value=day;
					x.add(option);
				}
				break;
			default :
				for(var day=1; day<=31;day++){
					var option=document.createElement('option');
					option.text=day;
					option.value=day;
					x.add(option);
				}
				break;
		}
	}
</script>
</head>
<body>
<h1>아이디 찾기 페이지입니다.</h1>
<form action="searchId" method="POST">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="userName" /></td>
		</tr>
		<tr>
			<td>생일</td>
				<td>
					<select id="year" name="register_year">
						<%
							Calendar today=Calendar.getInstance();	//오늘 날짜를 받음
							int year=today.get(Calendar.YEAR);
							for(int i=1917;i<=year;year--){
						%>		
							<option value="<%=year%>"><%=year%></option>
						<%	
							}
						%>
					</select>년
	
					<select id="month" name="register_month" onchange="appendDay(this.value);">
						<%	
							for(int i=1;i<=12;i++){
						%>		
							<option value="<%=i%>"><%=i%></option>
						<%	
							}
						%>
					</select>월
					<select id="day" name="register_day">
	
					</select>일
				</td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="전송"/></td>
		</tr>
	</table>
</form>
</body>
</html>