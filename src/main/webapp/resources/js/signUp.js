/**
 * 
 */

	$(document).ready(function(){
		$("#userPassword").on('keyup', function(){equal_pw();});		// keyup_function
		$("#userPasswordConfirm").on('keyup', function(){equal_pw();});		// keyup_function	
		
		// Enter 키 눌렀을 시 반응하도록 만든 함수
		$("input:text").on('keydown', function(){
			if(event.keyCode==13){
				
			}
		});
		
		var x=document.getElementById("day");
		x.options.length=0;
		for(var day=1; day<=31;day++){
			var option=document.createElement('option');
			option.text=day;
			option.value=day;
			x.add(option);
		}
	});
	
	var eq_pw=null;			// 비밀번호  서로 맞는지 확인하는 변수

	//ID 중복 확인
	function chkId(fm){
		var regType1 = /^[A-Za-z][A-Za-z0-9]{6,12}$/;
		var id=document.getElementById("id").value;
		
		if(/^[0-9]$/.test(id.charAt(0))){
			alert("첫 글자는 대,소문자로 작성해 주세요.");
			$("#id").focus();
		}	else {
				if (!regType1.test(id)) { 
					alert('아이디가 조건에 맞지 않습니다. \n 아이디는 영문을 포함하여야 하며 숫자를 사용가능합니다.'); 
					$("#id").focus();
					}		//특수문자를 포함하지 않아야 함
				// ID에 특수문자가 없고 첫 글자가 영어일 시 팝업창 열기
				else {
					//팝업창으로 열기(값을 다시 받아와야함)
 					var id=document.getElementById("id").value;
					var url="./Register_chk_id.jsp?id="+id;				
					var popup_Opt="toolbar=no, menubar=no, status=no, scrollbars=no, resizable=no, left=400, top=200, width=400, height=500";
					
					window.open(url,"",popup_Opt);
				}
		}
	}
	
	//ID중복확인 후 바꾸었을 떄
	function change_id(str){
		$("#id_hidden").val(null);
	}
	
	//비밀번호 대조
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
	
	//동적 일 수 생성 함수
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
	
	//전송
	function Go_submit(fm){
		var passwordRules = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
		var pw=document.getElementById("pw").value;
		var id_hidden=document.getElementById("id_hidden").value;
		var name=document.getElementById("name").value;
		var reg_email=/^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{2,5}$/;	

		if(!document.getElementById("id").value){
			alert("ID를 입력하세요");
			$("#id").focus();
			return false;
		}
  		if(id_hidden == "true"){
			alert("ID가 중복되어있습니다.");
			$("#id_check").focus();
			return false;
		} 
  		else if(id_hidden == "false"){}
  		else {
			alert("id확인 버튼을 눌러주세요");
			$("#id_check").focus();
			return false;
		}
 		if(!document.getElementById("pw").value){
			alert("PW를 입력하세요");
			$("#pw").focus();
			return false;			
		}
		if(!eq_pw){
			alert("비밀번호가 서로 맞지 않습니다. 확인해주세요.");
			$("#confirm_pw").focus();
			return false;
		}
		if(!passwordRules.test(pw)) {
			alert("비밀번호는 8~16글자로 작성하셔야합니다. \n 또한 특수문자(\!,\@,\#,\$,\%,\^,\*,\+,\=,\-)와 영문 및 숫자가 최소 하나씩은 포함 되어야합니다.");
			$("#pw").focus();
			return false;
		}
		if(!document.getElementById("name").value){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;			
		}
		
		//공백, 숫자, 특수문자 안되도록
		if(!/^[a-z]{3,16}$/.test(name)){
			alert("이름은 3~15자로, 공백,숫자 그리고 특수문자를 대입할 수 없습니다.");
			$("#name").focus();
			return false;
		}
		
		if(document.getElementById("day").value==0){
			alert("일을 선택해주세요.");
			$("#day").focus();
			return false;
		}
		
		if($('#email').val().match(reg_email)==null){
			alert("이메일을 확인해 주세요");
			$('#email').focus();
			return false;
		}	
		
		fm.action="./Register_seller_proc.jsp";
		fm.submit();
	}