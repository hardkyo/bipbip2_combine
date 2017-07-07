<%@ page language="java" contentType="text/plain; charset=euc-kr"
    pageEncoding="euc-kr"
    import="com.kitri.freeboard.model.FreeBoardDto"%>

<%
FreeBoardDto dto = (FreeBoardDto) request.getAttribute("article");
System.out.println("modifydata.jsp === " + dto.getSubject());
System.out.println("modifydata.jsp === " + dto.getContent());
%>

<%=dto.getSubject()%> ### <%=dto.getContent()%>