<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.kitri.member.model.MemberDto"%>
<jsp:include page="/common/header.jsp"></jsp:include>

<jsp:include page="/common/bottom.jsp"></jsp:include>
<jsp:include page="/common/head.jsp"></jsp:include>

<%
	String root = request.getContextPath();

	MemberDto memberDto = (MemberDto) request.getAttribute("userInfo");
	if (memberDto != null) {
%>
<script>
function mvmain() {
	document.commonForm.act.value = "main";
	document.commonForm.action = root +"/member";
	document.commonForm.submit();
}
</script>
<div class="container" style="padding-top: 50px">
	<div class="col-lg-4"></div>
	<div class="col-lg-5">
		<form name="commonForm" id="commonForm" method="post" action="">
			<input type="hidden" name="act" value="main">
			<div class="jumbotron" style="padding-top: 30px">
				<h2>회원가입 축하드립니다.</h2>
				<br>

				<!-- 회원 가입 성공시 -->
				<%=memberDto.getName()%>님 회원가입을 환영 합니다.<br> 가입 하신 정보는 아래와 같습니다.<br>
				<ul>
					<li>아이디 : <%=memberDto.getId()%>
					<li>이메일 : <%=memberDto.getEmail()%>
					<li>휴대번호 : <%=memberDto.getPhone()%>
					<li>주소 : <%=memberDto.getAddr1()%> <br>
					          <%=memberDto.getAddr2()%> <br>
				</ul>
				
				로그인 후 서비스를 이용하실수 있습니다.<br> 
				<input type="submit"
					class="btn btn-primary " value="메인 페이지로 이동"
					onclick="javascript:mvmain();">
				</form>
			</div>
	</div>
</div>

<jsp:include page="/common/footer.jsp"></jsp:include>

<%
	} else {
%>
<script>
alert("부적절한 URL접근입니다.");
document.location.href = "<%=root%>";
</script>
<%
	}
%>