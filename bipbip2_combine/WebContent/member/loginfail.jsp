<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<jsp:include page="/common/header.jsp"></jsp:include>
<jsp:include page="/common/bottom.jsp"></jsp:include>
<jsp:include page="/common/head.jsp"></jsp:include>
<%
	String root = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<center>
		<!-- 회원 가입 실패시 -->
		<h3 style="padding: 30px">
			아이디 또는 비밀번호가 틀렸습니다.<br> <br> 아이디 또는 비밀번호 확인 후 다시 로그인하세요.
		</h3>
		<br> <a href="/bipbip2_combine/admin?act=main"><input
			type="submit" class="btn btn-primary " value="메인 페이지로 이동"></a>


	</center>

</body>
</html>

<jsp:include page="/common/footer.jsp"></jsp:include>