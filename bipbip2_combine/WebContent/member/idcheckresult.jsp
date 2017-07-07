<%@ page language="java" contentType="text/plain; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
String sid = request.getParameter("sid");
int count = Integer.parseInt(request.getParameter("count"));
if(count == 0) {
%>
<b><%=sid %></b><font color="blue">는 사용가능합니다.</font>
<%
} else {
%>
<b><%=sid %></b><font color="red">는 사용중입니다. 다른 아이디로 검색하세요.</font>
<%	
}
%>