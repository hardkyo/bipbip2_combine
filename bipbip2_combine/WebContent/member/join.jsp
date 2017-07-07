<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	String root = request.getContentType();
%>
<script type="text/javascript" src="/bipbip1/js/myajax.js"></script>


<!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
<script type="text/javascript">
/* var view;
var flag = false;


function join(){
	
	if(document.getElementById("id").value == "") {
		alert("아이디 입력!");
		return;
	} else if(document.getElementById("name").value == "") {
		alert("이름 입력!");
		return;
	} else if(document.getElementById("pass").value  == "") {
		alert("비밀번호 입력!");
		return;
	}  else if(document.getElementById("pass").value != document.getElementById("passcheck").value) {
		alert("비밀번호가 일치하지 않습니다.!");
		
		return;  
	} else {
		document.joinform.action = "/bipbip1/member";
		document.joinform.submit();
	}	
}

function idcheck(){
	view = document.getElementById("idresult");
	var idval = document.getElementById("id").value;
	//alert(idval);
	if(idval.length < 5 || idval.length > 16) {
		view.innerHTML = "<font color='orange'>아이디는 5자이상 16자이하입니다.</font>";
	} else {
		var param = "act=idsearch&id=" + encodeURI(idval);
		sendRequest("/bipbip1/member", param, idresult, "GET");
	}
}

function idresult() {
	if(httpRequest.readyState == 4) {
		if(httpRequest.status == 200) {
			var txt = httpRequest.responseText;
			view.innerHTML = txt;
		} else {
			alert("문제발생 : " + httpRequest.status);
		}
	}
}

function passwordCheck(){

	var password = document.getElementById("pass").value;
	var passwordCheck = document.getElementById("passcheck").value;

	if (password != passwordCheck) {
		document.getElementById("passresult").style.color = "red";
		document.getElementById("passresult").innerHTML = "동일한 비밀번호를 입력하세요";
	} else {
		document.getElementById("passresult").style.color = "blue";
		document.getElementById("passresult").innerHTML = "비밀번호가 확인되었습니다.";
	}
} 
 */
</script>
<div class="container" style="padding-top: 50px">
	<div class="col-lg-4"></div>
	<div class="col-lg-5">
		<div class="jumbotron" style="padding-top: 30px">
			<form name="joinform" id="joinform" method="post" action="">
				<input type="hidden" name="act" value="register">
				<h3 style="text-align: center;">회원가입 화면</h3>
				<div class="form-group">

					<input type="text" class="form-control" placeholder="아이디" name="id" id="id" value="" maxlength="20" onkeyup="javascript:idcheck();">
					<div id="idresult"></div>
				</div>
		
				<div class="form-group">
					<input type="password" class="form-control" placeholder="비밀번호입력"
						name="pass" id="pass" value="" maxlength="20"><!-- &nbsp;&nbsp;<font
						color="#3cb371">6~12</font>자리의 영문(대소문자 구별)이나 숫자 -->
				</div>
				<div class="form-group">
					<input type="password" class="form-control" placeholder="비밀번호확인"
						name="passcheck" id="passcheck" value="" maxlength="20" onkeyup="javascript:passwordCheck();">
					<div id="passresult"></div>
			
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="이름"
						name="name" id="name" value="" maxlength="20">
				</div>
				<div class="form-group" style="text-align: center;"></div>
				<div class="form-group">
					<input type="email" class="form-control" placeholder="이메일"
						name="email" id="email" value="" maxlength="20">
				</div>
				<div class="form-group">
					<input type="tel" class="form-control" placeholder="연락처"
						name="phone" id="phone" value="" maxlength="20">
				</div>
				
				<div class="form-group">
					<input type="text" class="form-control" placeholder="주소"
						name="address" id="address" value="" maxlength="20">
				</div>
				
				<input type="submit" class="btn btn-primary form-control"
					value="회원가입" onclick="javascript:join();">
			</form>
		</div>
	</div>
</div>



