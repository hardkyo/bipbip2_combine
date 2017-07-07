<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.kitri.member.model.MemberDto"%>
<%
	String root = request.getContextPath();
	MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
%>
<script type="text/javascript" src="/bipbip1/js/myajax.js"></script>
<script type="text/javascript">
var view;
var flag = false;
var root = "<%=request.getContextPath()%>";

   	function moveFreeBoardList() {
   		document.location.href = root + "/freeboard?act=freeboard&bcode=1&pg=&key=&word=";
	}
   	
   	function moveGallerydList() {
		document.location.href = "<%=root%>/gallery?act=mvgallerylist";
	}
   	
   	function moveLogin() {
   		document.location.href = "<%=root%>/member?act=mvlogin";
	}
   	
   	function moveJoin() {
   		document.location.href = "<%=root%>/member?act=mvjoin";
	}
	
	function moveContact(){
		document.location.href = "<%=root%>/member?act=contact";
	}
	
   	function moveRouteHot() {
   		document.location.href = "<%=root%>/maproute?act=mvroutehot";
	}
   	
   	function movePlanRoute() {
   		document.location.href = "<%=root%>/map?act=mvshowmap";
	}
   	function movePlanRoute() {
   		document.location.href = "<%=root%>/map?act=mvshowmap";
	}
   	function movePlanList() {
   		document.location.href = "<%=root%>/map?act=maplist";
	}

	function logout() {
		if(confirm("로그아웃 하시겠습니까?") == true ){
			document.location.href = "<%=root%>/member?act=logout";
		}else {
			return;
		}
	}

	function memberDelete() {
		if(confirm("회원탈퇴를 하시겠습니까?") == true ){
			document.location.href = "<%=root%>/member?act=memberdelete";
		}else {
			return;
		}
	} 

	function moveModify() {
   		document.location.href = "<%=root%>/member?act=mvmodify";
	}

	function login() {
		if (document.getElementById("id").value == "") {
			alert("이름을 입력해주세요!");
			return;
		} else if (document.getElementById("pass").value == "") {
			alert("비밀번호를 입력해주세요!");
			return;
		} else {
			document.loginform.action = "/bipbip1/member";
			document.loginform.submit();
		}
	}

	function join(){
		if(document.getElementById("signid").value == "") {
			alert("아이디 입력!");
			return;
		} else if(document.getElementById("signname").value == "") {
			alert("이름 입력!");
			return;
		} else if(document.getElementById("signpass").value  == "") {
			alert("비밀번호 입력!");
			return;
		}  else if(document.getElementById("signpass").value != document.getElementById("signpasscheck").value) {
			alert("비밀번호가 일치하지 않습니다.!");
			return;  
		} else {
			document.joinform.action = "/bipbip1/member";
			document.joinform.submit();
		}	
	}
	
	function idcheck(){
		view = document.getElementById("idresult");
		var idval = document.getElementById("signid").value;
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
		var password = document.getElementById("signpass").value;
		var passwordCheck = document.getElementById("signpasscheck").value;
		if (password != passwordCheck) {
			document.getElementById("passresult").style.color = "red";
			document.getElementById("passresult").innerHTML = "동일한 비밀번호를 입력하세요";
		} else {
			document.getElementById("passresult").style.color = "blue";
			document.getElementById("passresult").innerHTML = "비밀번호가 확인되었습니다.";
		}
	} 


	
	 
</script>
</head>
<body>

	<!-- board common form -->
	<form name="commonForm" method="get" action="">
		<input type="hidden" name="act" value="" /> <input type="hidden"
			name="bcode" value="" /> <input type="hidden" name="pg" value="" />
		<input type="hidden" name="key" value="" /> <input type="hidden"
			name="word" value="" /> <input type="hidden" name="seq" value="" />
	</form>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-default navbar-fixed-top"
					role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="/bipbip1/admin?act=main"><strong>Bike</strong></a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class=""><a href="javascript:moveRouteHot();">추천경로</a></li>
							<li class=""><a href="javascript:movePlanRoute();">경로계획</a></li>
							<li class=""><a href="javascript:moveGallerydList();">앨범</a></li>
							<li class=""><a href="javascript:moveFreeBoardList();">자유게시판</a></li>
							<li class="dropdown"><a class="dropdown-toggle" href="#"data-toggle="dropdown">모임 <strong class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="javascript:movePlanList();">경로보기</a></li>
									<li><a href="#">기능2</a></li>
								</ul></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="javascript:moveContact();">문의하기</a></li>
							<li class="dropdown"><a class="dropdown-toggle" href="#"
								data-toggle="dropdown"> <%=(memberDto == null ? "접속하기" : "회원관리")%><strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<%
										if (memberDto == null) {
									%>
									<li><a href="#login" data-toggle="modal"
										data-target="#loginModal" id="mylogin">LOGIN</a></li>
									<li><a href="#sign" data-toggle="modal"
										data-target="#signModal">SIGN UP</a></li>


									<!-- <li class="divider"></li> -->
									<%
										} else {
									%>
									<li><a href="<%=root%>/member?act=mvmypage">마이페이지</a></li>
									<li><a href="javascript:moveModify();">회원정보수정</a></li>
									<li><a href="javascript:memberDelete();">회원탈퇴</a></li>
									<li><a href="javascript:logout();">로그아웃</a></li>
									<%
										}
									%>
								</ul></li>

						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>
	<br />
	<br />
	<br />


<!-- Total Modal Action -->

	<!-- login Modal -->
	<div class="modal fade in" id="loginModal" role="dialog"
		data-backdrop="true">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-lock"></span> Login
					</h4>
				</div>

				<div class="modal-body">
					<form name="loginform" method="post" action="">
						<input type="hidden" name="act" value="login">
						<!-- Login Form -->

						<div class="form-group">
							<label> ID</label> <input type="text" class="form-control"
								name="id" id="id" placeholder="ID">
						</div>
						<div class="form-group">
							<label> PASSWORD</label> <input type="password"
								class="form-control" name="pass" id="pass"
								placeholder="Password">
						</div>
						<button type="submit" class="btn btn-block"
							onclick="javascript:login();">
							<span class="glyphicon glyphicon-ok"></span> Log In
						</button>
					</form>


				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-danger btn-default pull-left"
						data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span> Cancel
					</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Sign Modal -->
	<div class="modal fade in" id="signModal" role="dialog"
		data-backdrop="true">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>
						<span class="glyphicon glyphicon-user"></span> Sign Up
					</h4>
				</div>
				<div class="modal-body">
					<form name="joinform" method="post" action="">
						<input type="hidden" name="act" value="register">

						<!-- Sign Form -->
						<div class="form-group">
							<label> ID</label> 
							<input type="text" class="form-control"name="signid" id="signid" placeholder="ID" onkeyup="javascript:idcheck();">
							<div id="idresult"></div>
						</div>
						<div class="form-group">
							<label> PASSWORD</label> <input type="password"
								class="form-control" name="signpass" id="signpass"
								placeholder="Password">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="비밀번호확인"
								name="signpasscheck" id="signpasscheck" value="" maxlength="20"
								onkeyup="javascript:passwordCheck();">
							<div id="passresult"></div>
						</div>

						<label> Name </label>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="이름"
								name="signname" id="signname" value="" maxlength="20">
						</div>

						<div class="form-group">
							<label> E-MAIL</label> <input type="email" class="form-control"
								name="email" id="email" placeholder="Email">
						</div>
						<label> 휴대폰 </label>
						<div class="form-group">
							<input type="tel" class="form-control" placeholder="연락처"
								name="phone" id="phone" value="" maxlength="20">
						</div>

						<label> 주소 </label>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="주소"
								name="address1" id="address1" value="" maxlength="20">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="상세주소"
								name="address2" id="address2" value="" maxlength="20">
						</div>

						<button type="submit" class="btn btn-block"
							onclick="javascript:join();">
							<span class="glyphicon glyphicon-ok"></span> Sign In
						</button>
					</form>

				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-danger btn-default pull-left"
						data-dismiss="modal">
						<span class="glyphicon glyphicon-remove"></span> Cancel
					</button>
				</div>
			</div>
		</div>
	</div>
	
<script src="/bipbip1/js/search.min.js"></script>