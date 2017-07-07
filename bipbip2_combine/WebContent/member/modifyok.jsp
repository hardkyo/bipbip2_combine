<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.kitri.member.model.MemberDto"%>
<%
String root = request.getContextPath();

MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
if(memberDto != null) {

%>
<script type="text/javascript">
	location.href = "/bipbip2_combine/index.jsp";
</script>

<%
} else {
	response.sendRedirect(root + "/member?act=mvlogin");
}
%>




