<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
String root = request.getContextPath();
String url = root+"/admin?act=main";
%>
<script type="text/javascript"> 
	alert("로그인이 필요한 페이지입니다.")
    location.href = "<%=url%>";
    </script>
<body>
<center>
<h1>
잘못된 url 접근 입니다.<br>
로그인이 필요한 페이지입니다.
</h1>
</center>
</body>
