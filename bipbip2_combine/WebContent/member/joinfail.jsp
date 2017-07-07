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
<h3 style="padding: 30px">서버에 문제가 발생하여 회원가입이 실패하였습니다.<br><br>
다음에 이용해 주세요
</h3><br>

</center>

</body>
</html>
<jsp:include page="/common/footer.jsp"></jsp:include>