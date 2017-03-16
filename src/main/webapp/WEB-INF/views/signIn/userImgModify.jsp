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
 <script>
 	function readURL(input){
 		if(input.files&& input.files[0]){
 			var reader=new FileReader();
 			reader.onload=function(e){
 				$("#UploadImg").attr("src",e.target.result);
 			}
 			reader.readAsDataURL(input.files[0]);
 		}
 	}
 	
 	function userImgSubmit(fm){
 		var imgVal=$("#file").val();
 		if(imgVal==''){
 			alert("이미지를 넣어주세요.");
 			return false;
 		}
 		fm.submit();
 	}
 </script>
</head>
<body>
<h1>회원 이미지 수정 페이지입니다.</h1><br/>

<img id="UploadImg" src="${pageContext.request.contextPath }/resources/images/userImg/BasicMaleImg.png" width="100" height="130">
<form action="userImgModify" method="POST" enctype="multipart/form-data">
	<input type="hidden" name="userImg" value="${userDto.userImg }"/>
	<table>
		<tr>
			<td>이미지</td>
			<td><input type="file" name="file" id="file" onchange="readURL(this)"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="전송" onclick="userImgSubmit(this.form);"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>