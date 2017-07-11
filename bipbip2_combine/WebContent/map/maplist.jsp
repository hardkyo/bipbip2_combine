<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="java.util.List"%>
    <%@page import="com.kitri.member.model.MemberDto"%>
<%@page import="com.kitri.map.model.MapDto, com.kitri.util.*"%>


<%
String root = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script type="text/javascript" src="<%=root%>/js/maplocationfunction.js"></script>
<link rel="stylesheet" type="text/css" href="<%=root%>/css/list.css" />

<script>
// 경로 보기 클릭
function callMap(seq) {
	document.callForm.seq.value = seq;
	document.callForm.action = root + "/map";
	document.callForm.submit();
}

function getAct(){
	document.callForm.act.value = "mapcall";
}
</script>
<%
List<MapDto> list = (List<MapDto>) request.getAttribute("articleList");
PageNavigation pageNavigation = (PageNavigation) request.getAttribute("navigator");
%>
<body onload="getAct()">

<section id="notice">

   <div class="notice">

	<h2>게시판 정보</h2>

	<div class="top">

        <a href="javascript:movePlanRoute()"><div class="write_but">글쓰기</div></a>
        
     </div>
     
            <form id="callForm" name="callForm" method="post" action=""
			style="margin: 0px">
            <input type="hidden" name="act" value="">
            <input type="hidden" name="bcode" value="2">
			<input type="hidden" name="pg" value="1">
			<input type="hidden" name="key" value="">
			<input type="hidden" name="word" value="">
			<input type="hidden" name="seq" value="">
            </form>
            
        <div id="con">
        
        	<div class="con_up">
            
            <div class="number">번호</div>
            <div class="where">출발지역</div>
            <div class="text">도착지역</div>
            <div class="who">아이디</div>
            <div class="when">닉네임</div>
            <div class="view">추천수</div>
        
        	</div>
        
        <%
	int size = list.size();
	if (size!=0){
		for(MapDto mapDto : list){ // for-each
	%>
        	<a href="javascript:callMap(<%=mapDto.getSeq()%>)"><div>
            
            <div class="number"><%=mapDto.getSeq() %></div>
            <div class="where"><%=mapDto.getLoc1() %></div>
            <div class="text"><%=mapDto.getLoc2() %></div>
            <div class="who"><%=mapDto.getName() %></div>
            <div class="when"><%=mapDto.getId()%></div>
            <div class="view"><%=mapDto.getHit()%></div>
            </div></a>

         <%
		}
		%>

		
		<%
	} else { %>


<script>
alert("Unathorized URL access.");
document.location.herf = "<%=root%>/index.jsp";
</script>

	<%
}
%>  
            
        </div>
    </div>
    

</section>

</body>