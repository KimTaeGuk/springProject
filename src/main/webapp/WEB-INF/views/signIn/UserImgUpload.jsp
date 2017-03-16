<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이미지 업로드 페이지</title>
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
<h1>이미지 업로드 페이지입니다.</h1>
<img id="UploadImg" src="${pageContext.request.contextPath }/resources/images/userImg/BasicMaleImg.png" width="100" height="130">
<form action="UserImgUpload" method="POST" enctype="multipart/form-data">
	<input type="file" name="file" id="file" onchange="readURL(this)"/>
	<input type="button" value="전송" onclick="userImgSubmit(this.form);"/>
</form>
<form action="#" method="POST">
	<input type="submit" value="다음에 등록하기"/>
</form>
</body>
</html>