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
	
	function SignUpSubmit(fm){
		var passwordRules = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
		if(!$("#userId").val()){	
			alert("ID를 입력하세요.");
			$("#userId").focus();
			return false;
		}
		if($("#existsId").val()=="false"){
				alert("ID가 중복되어있습니다. ID를 바꿔주세요.");
				$("#userId").focus();
				return false;
		}	else if($("#existsId").val()=="true"){
			
		}	else{
				alert("id확인버튼을 눌러주세요");
				$("#id_check").focus();
				return false;
		}
		if(!eq_pw){
			alert("비밀번호가 서로 맞지 않습니다. 확인해주세요.");
			$("#userPasswordConfirm").focus();
			return false;
		}
		if(!passwordRules.test($("#userPassword").val())){
			alert("비밀번호는 8~16글자로 작성하셔야합니다. \n 또한, 특수문자(\!,\@,\#,\$,\%,\^,\*,\+,\=,\-)와 영문 및 숫자가 최소 하나씩은 포함 되어야합니다.");
			$("#userPassword").focus();
			return false;
		}
		
		if(!$("#userEmailId").val()){
			alert("이메일을 입력해주세요");
			$("#userEmailId").focus();
			return false;
		}
		//공백, 숫자, 한글은 작성이 불가능합니다.
		if(!/^[a-z]{3,16}$/.test($("#userName").val())){
			alert("이름은 3~15자로, 공백, 숫자 그리고 특수문자를 대입할 수 없습니다.");
			$("#name").focus();
			return false;
		}
			
		fm.submit();
	}
	
	//아이디를 바꿨을 시 ID확인에 null 값을 주어 회원가입 제출 시 다시 ID확인을 하도록 합니다.
	function changeId(userId){
		$("#existsId").val(null);
	}
	</script>
</head>
<body>
<h2>판매자 회원가입</h2>
<form action="signUpProc" method="POST" id="fm" enctype="multipart/form-data">
	<input type="hidden" id="existsId"/>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="userId"  id="userId" onchange="javascript:changeId(this.value);"/></td>
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
			<td>
				<div id="emailDiv">
					<input type="text" id="userEmailId" name="userEmailId" size="4"/>
					@
					<select name="userEmailAddress" id="userEmailAddress">
						<option value="gmail.com">gmail.com</option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="self">직접입력</option>
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" id="userName" name="userName" /></td>
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
			<td>이미지</td>
			<td><input type="file" name="userImg"/></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" onclick="SignUpSubmit(this.form);" value="제출"/></td>
		</tr>
	</table>
	<!-- csrf -->
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
</form>
</body>
</html>
