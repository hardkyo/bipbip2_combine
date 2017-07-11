<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="java.io.Console"%>
<%@page import="com.kitri.map.model.MapDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=6d432994bce4d7c8c4c3ad20a20c496b&libraries=services,clusterer,drawing"></script>
	<%@ include file="/common/public.jsp" %>
	<!-- T-map Api 키 -->
	<script type="text/javascript" src="https://apis.skplanetx.com/tmap/js?version=1&format=javascript&appKey=b049998c-19f6-379f-893e-bd72e90ed980"></script>
	<script type="text/javascript" src="<%=root%>/js/maplocationfunction.js"></script>

<%
	MapDto mapDto = (MapDto) request.getAttribute("mapDto");			
	if(mapDto!=null){
%>

<script type="text/javascript">
window.onload="javascript:getViewData();"

function no_back(){
	window.history.forward();
}
</script>

<link rel="stylesheet" type="text/css" href="<%=root%>/css/mapCss.css" />
<body onload="no_back()" onpageshow="if(event.persisted)no_back();">

<section id="map">
<div id="map_div"></div>
<!-- <div class="map_place"></div> -->
<script type="text/javascript">
 
function setCoordinace1(){
    startX=<%=mapDto.getLoc1X()%>
    startY=<%=mapDto.getLoc1Y()%>
    endX = <%=mapDto.getSec1X()%>
    endY = <%=mapDto.getSec1Y()%>
    // 출발 ~ 경유1
}
function setCoordinace2(){
    startX=<%=mapDto.getSec1X()%>
    startY=<%=mapDto.getSec1Y()%>
    endX = <%=mapDto.getSec2X()%>
    endY = <%=mapDto.getSec2Y()%>
    // 경유1 ~ 경유2
}
function setCoordinace3(){
    startX=<%=mapDto.getSec2X()%>
      startY=<%=mapDto.getSec2Y()%>
    endX = <%=mapDto.getSec3X()%>
    endY = <%=mapDto.getSec3Y()%>
    // 경유2 ~ 경유3
}
function setCoordinace4(){
    startX=<%=mapDto.getSec3X()%>
    startY=<%=mapDto.getSec3Y()%>
    endX = <%=mapDto.getLoc2X()%>
    endY = <%=mapDto.getLoc2Y()%>
    // 경유3 ~ 도착
}
function setCoordinace5(){
    startX=<%=mapDto.getSec1X()%>
    startY=<%=mapDto.getSec1Y()%>
    endX = <%=mapDto.getLoc2X()%>
    endY = <%=mapDto.getLoc2Y()%>
    // 경유1 ~ 도착
}
function setCoordinace6(){
    startX=<%=mapDto.getSec2X()%>
    startY=<%=mapDto.getSec2Y()%>
    endX = <%=mapDto.getLoc2X()%>
    endY = <%=mapDto.getLoc2Y()%>
    // 경유2 ~ 도착
}
function setCoordinace7(){
    startX=<%=mapDto.getLoc1X()%>
    startY=<%=mapDto.getLoc1Y()%>
    endX = <%=mapDto.getLoc2X()%>
    endY = <%=mapDto.getLoc2Y()%>
    // 출발 ~ 도착
}

var startX;
var startY;
var endX;
var endY;

var sec1 = '<%=mapDto.getSec1()%>';
var sec2 = '<%=mapDto.getSec2()%>';
var sec3 = '<%=mapDto.getSec3()%>';
 
var map;
function initialize() {
   map = new Tmap.Map({div:"map_div", width:'600px', height:'400px'});
   map.addControl(new Tmap.Control.MousePosition());
   var markerLayer = new Tmap.Layer.Markers();
   map.addLayer(markerLayer);


if(sec1==""){
	   setCoordinace7();
	   searchRoute();
   }  else if(sec2==""){
	   setCoordinace1();
	   searchRoute();
	   setCoordinace5();
	   searchRoute();
	   alert("done"+sec2+"doneAt2=2");
   } else if(sec3==""){
	   setCoordinace1();
	   searchRoute();
	   setCoordinace2();
	   searchRoute();
	   setCoordinace6();
	   searchRoute();
	   alert("done"+sec2+"doneAt3=3");
   } else if(sec3!=""){
	   alert("done::"+sec3+"::done");
	   setCoordinace1();
	   searchRoute();
	   setCoordinace2();
	   searchRoute();
	   setCoordinace3();
	   searchRoute();
	   setCoordinace4();
	   searchRoute();
   } 
}
 window.onload = function() {
    initialize();
}
 function searchRoute(){
       var routeFormat = new Tmap.Format.KML({extractStyles:true, extractAttributes:true});
   /*    var startX = 14132105.182794;
       var startY = 4519396.684182;  */

       alert("startX:::"+startX);
       //////////// 좌표계를 변환 중입니다.
         var str =(String)(get3857LonLat(startX,startY));
         var end =(String)(get3857LonLat(endX,endY));
    
         
        ////////// 좌표계를 분리하는 중입니다.
      	var strmap = str.split(',');
       	var start1 = strmap[0].split('=');
       	var start2 = strmap[1].split('=');
      //alert(start1[1]+",,,"+start2[1]);
      	var endmap = end.split(',');
       	var end1 = endmap[0].split('=');
       	var end2 = endmap[1].split('=');
       	//alert(end1[1]+",,,"+end2[1]);
       
    
      /* var startX=;
       var statry=str.[1]; */
/*       console.log(get3857LonLat(startX,startY)); */
       var urlStr = "https://apis.skplanetx.com/tmap/routes?version=1&format=xml";
       urlStr += "&startX="+start1[1];
       urlStr += "&startY="+start2[1];
       urlStr += "&endX="+end1[1];
       urlStr += "&endY="+end2[1];
       urlStr += "&appKey=b049998c-19f6-379f-893e-bd72e90ed980";
       var prtcl = new Tmap.Protocol.HTTP({
                                           url: urlStr,
                                           format:routeFormat
                                           });
       var routeLayer = new Tmap.Layer.Vector("route", {protocol:prtcl, strategies:[new Tmap.Strategy.Fixed()]});
       routeLayer.events.register("featuresadded", routeLayer, onDrawnFeatures);
       map.addLayer(routeLayer);
   }
 var pr_3857 = new Tmap.Projection("EPSG:3857");
 var pr_4326 = new Tmap.Projection("EPSG:4326");//WGS84 GEO
 
 function get3857LonLat(coordX, coordY){
       return new Tmap.LonLat(coordX, coordY).transform(pr_4326, pr_3857);
   }
   //경로 그리기 후 해당영역으로 줌
   function onDrawnFeatures(e){
       map.zoomToExtent(this.getDataExtent());
   }

</script>
<div class="right">

	<section class="top">
	</section><!--top-->
    
    
    <div class="sbig">
    
    	<div class="sm lil">
    	<div class="na_01 na">출발</div>
        <div class="na_02 na"><input type="text" class="text" value="<%=mapDto.getLoc1()%>" readonly="readonly"></div>
    	</div>
        
        <div class="sm">
    	<div class="na_01 na">도착</div>
        <div class="na_02 na"> <input type="text" class="text" value="<%=mapDto.getLoc2()%>" readonly="readonly"></div>
    	</div>
        
        <div class="sm">
    	<div class="na_01 na sz">경유1</div>
        <div class="na_02 na"> <input type="text" class="text" value="<%=mapDto.getSec1()%>" readonly="readonly"></div>
    	</div>
        
        <div class="sm">
    	<div class="na_01 na sz">경유2</div>
        <div class="na_02 na"> <input type="text" class="text" value="<%=mapDto.getSec2()%>" readonly="readonly"></div>
    	</div>
        
        <div class="sm">
    	<div class="na_01 na sz">경유3</div>
        <div class="na_02 na"> <input type="text" class="text" value="<%=mapDto.getSec3()%>" readonly="readonly"></div>
    	</div>
        
        
        
    </div>
    
    <form id="draftForm" name="draftForm" method="post" action=""
				style="margin: 0px">
				<input type="hidden" name="act" value="mapwrite">
				<input type="hidden" name="loc1X" value="<%=mapDto.getLoc1X()%>">
				<input type="hidden" name="loc1Y" value="<%=mapDto.getLoc1Y()%>">
				<input type="hidden" name="loc2X" value="<%=mapDto.getLoc2X()%>">
				<input type="hidden" name="loc2Y" value="<%=mapDto.getLoc2Y()%>">
				<input type="hidden" name="sec1X" value="<%=mapDto.getSec1X()%>">
				<input type="hidden" name="sec1Y" value="<%=mapDto.getSec1Y()%>">
				<input type="hidden" name="sec2X" value="<%=mapDto.getSec2X()%>">
				<input type="hidden" name="sec2Y" value="<%=mapDto.getSec2Y()%>">
				<input type="hidden" name="sec3X" value="<%=mapDto.getSec3X()%>">
				<input type="hidden" name="sec3Y" value="<%=mapDto.getSec3Y()%>">
				<input type="hidden" name="loc1" value="<%=mapDto.getLoc1()%>">
				<input type="hidden" name="loc2" value="<%=mapDto.getLoc2()%>">
				<input type="hidden" name="sec1" value="<%=mapDto.getSec1()%>">
				<input type="hidden" name="sec2" value="<%=mapDto.getSec2()%>">
				<input type="hidden" name="sec3" value="<%=mapDto.getSec3()%>">
				<input type="hidden" name="memo" value="<%=mapDto.getMemo()%>">
				
				<input type="hidden" name="bcode" value="2">
				<input type="hidden" name="pg" value="1">
				<input type="hidden" name="key" value="">
				<input type="hidden" name="word" value="">
				
				<input type="hidden" name="subject" value=" ">
			</form>
    
    <div class="textarea">
    	
        <div class="memo_top">&nbsp;&nbsp;메모</div>
           <textarea class="memo" id="memo"><%=mapDto.getMemo()%></textarea>
         
         <div class="button">
            <div class="ok" onclick="movecall()">등록</div>
            <div class="no">취소</div>
        </div>
    </div>
    
    
</div><!--right-->

</section>

</body>
<%
	} else { 

%>


<script>
alert("Unathorized URL access.");

</script>

	<%
}
%>