<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import="com.kitri.member.model.*"
    import="com.kitri.freeboard.service.*"%>
<%
String root = request.getContextPath();
MemberDto memberDto = new MemberDto();
memberDto.setId("admin");

/* MemberDto newLoginInfo = CommonServiceImpl.getCommonService().updateLoginInfo(memberDto);
session.setAttribute("loginInfo", newLoginInfo); */

response.sendRedirect(root + "/admin?act=main");

%>   