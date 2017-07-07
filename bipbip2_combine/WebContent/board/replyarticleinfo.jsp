<%@ page language="java" contentType="text/plain; charset=euc-kr"
    pageEncoding="euc-kr"
    import="com.kitri.freeboard.model.FreeBoardDto"%>
<%
FreeBoardDto dto = (FreeBoardDto) request.getAttribute("article");
System.out.println("replydata.jsp === " + dto.getSubject());
System.out.println("replydata.jsp === " + dto.getContent());
%>
Re: <%=dto.getSubject()%> ### ----- [¿ø±Û] ----- <br/><br/><%=dto.getContent()%>
