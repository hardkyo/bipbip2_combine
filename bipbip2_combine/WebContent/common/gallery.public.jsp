<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.kitri.util.*,com.kitri.member.model.MemberDto"%>
<%
String root = request.getContextPath();

System.out.println("어디서 왔냐? >>> " + request.getHeader("Referer"));

int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
int pg = NumberCheck.nullToOne(request.getParameter("pg"));
String key = Encoding.nullToBlank(request.getParameter("key"));
String word = Encoding.isoToEuc(request.getParameter("word"));

MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>게시판 글쓰기</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="<%=root%>/css/skin_purple.css" type="text/css">
<script type="text/javascript">
var root = "<%=root%>";
var control = "";
var bcode = "<%=bcode%>";
var pg = "<%=pg%>";
var key = "<%=key%>";
var word = "<%=word%>";
</script>
<script type="text/javascript" src="<%=root%>/js/board.js"></script>
</head>

<body>
<form name="commonForm" method="get" action="">
	<input type="hidden" name="act" value="">
	<input type="hidden" name="bcode" value="">
	<input type="hidden" name="pg" value="">
	<input type="hidden" name="key" value="">
	<input type="hidden" name="word" value="">
	<input type="hidden" name="seq" value="">
	
</form>
