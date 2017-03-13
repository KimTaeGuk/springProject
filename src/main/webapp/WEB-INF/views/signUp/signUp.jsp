<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<html>
<head>
	<title>MainPage</title>
 	<script src="<c:url value='/resources/js/jquery-3.1.1.min.js'/>"></script>
 	<script>
 		var eq_pw=null;
 		
		$(document).ready(function(){
			$("#userPassword").on('keyup', function(){equal_pw();});		// keyup_function
			$("#userPasswordConfirm").on('keyup', function(){equal_pw();});		// keyup_function	
		
			var x=document.getElementById("day");
			x.options.length=0;
			for(var day=1; day<=31;day++){
				var option=document.createElement('option');
				option.text=day;
				option.value=day;
				x.add(option);
			}		
		});
		
		//비밀번호 대조기능입니다.
		function equal_pw(){
				var pw=document.getElementById("userPassword").value;
				var cf_pw=document.getElementById("userPasswordConfirm").value;
				
				if(pw==""){
					document.getElementById("chkpwDiv").innerHTML="작성";
				}	else {
					//비밀번호가 서로 일치할 시에 일치, 아닐 시에는 불일치
					if(pw==cf_pw){
						document.getElementById("chkpwDiv").innerHTML="일치";
						eq_pw=true;
					}
					else {
						document.getElementById("chkpwDiv").innerHTML="불일치";				
						eq_pw=false;
					}
				}
		}
		
		//아이디 확인 기능입니다.
		function chkId(fm){
			//아이디는 6~12글자 이내여야 하며 첫 글자는 영문이어야만 하며 숫자를 포함하고 있어야합니다.
			var regType1 = /^[A-Za-z][A-Za-z0-9]{6,12}$/;
			var id=document.getElementById("userId").value;
			
			if(/^[0-9]$/.test(id.charAt(0))){
				alert("첫 글자는 대,소문자로 작성해 주세요.");
				$("#userId").focus();
			}	else {
					if (!regType1.test(id)) { 
						alert('아이디가 조건에 맞지 않습니다. \n 아이디는 영문을 포함하여야 하며 숫자를 사용가능합니다.'); 
						$("#userId").focus();
						}		//특수문자를 포함하지 않아야 함
					// ID에 특수문자가 없고 첫 글자가 영어일 시 팝업창 열기
					else {
						//팝업창으로 열기(값을 다시 받아와야함)
	 					var userId=document.getElementById("userId").value;
						var url="checkId?userId="+userId;				
						var popup_Opt="toolbar=no, menubar=no, status=no, scrollbars=no, resizable=no, left=400, top=200, width=400, height=500";

						window.open(url,"",popup_Opt);
					}
			}
		}
		
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
<h2>판매자 회원가입</h2>
<form action="signUpProc" method="POST">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userId"  id="userId"/></td>
			<td><input type="button" id="id_check" value="ID확인" onclick="javascript:chkId(this.form);"/></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="userPassword"  id="userPassword"/></td>
			<td rowspan=2 id="chkpwDiv" style="text-align: center;"></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" name="userPasswordConfirm" id="userPasswordConfirm"/></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="userEmail" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="userName" /></td>
		</tr>
		<tr>
			<td>생년월일</td>
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
			<td colspan="2"><input type="submit" value="전송" /></td>
		</tr>
	</table>
	<!-- csrf -->
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
</form>
</body>
</html>
