<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%
	String root = request.getContentType();
%>


<script>
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
</script>

		<!-- Sign Modal -->
				<!-- <div class="modal fade" id="loginModal" role="dialog" data-backdrop="false">
				  <div class="modal-dialog">
				
				    Modal content
				    <div class="modal-content">
				    
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal">&times;</button>
				        <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
				      </div>
				      <div class="modal-body">
				      
				      	Login Form
				        <form role="form">
				          <div class="form-group">
				            <label> ID</label>
				            <input type="text" class="form-control" id="logId" placeholder="ID">
				          </div>
				          <div class="form-group">
				            <label> PASSWORD</label>
				            <input type="password" class="form-control" id="logPassword" placeholder="Password">
				          </div>
				          	<button type="submit" class="btn btn-block">
					          <span class="glyphicon glyphicon-ok"></span> Log In</button>
				        </form>
				        
				     </div>
				      <div class="modal-footer">
				        <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">
				          <span class="glyphicon glyphicon-remove"></span> Cancel</button>
				      </div>
				    </div>
				    
				  </div>
				</div>  -->

 









 <!-- <div class="container" style="padding-top: 50px">
		<div class="col-lg-4"></div>
		<div class="col-lg-5">
			<div class="jumbotron" style="padding-top: 30px">
				<form name ="loginform" method="post" action="">
					<input type="hidden" name="act" value="login">
					<h3 style="text-align: center;">로그인</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디"
							name="id" id="id" maxlength="20">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호"
							name="pass" id="pass" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control"
						value="로그인" onclick="javascript:login();">
				</form>
			</div>
		</div>
	</div> 
 -->
