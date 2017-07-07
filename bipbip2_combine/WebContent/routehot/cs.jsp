<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<div id="map_div<%=mapDto.getSeq() %>" ></div>
<script type="text/javascript">
var map<%=mapDto.getSeq() %>;

function initialize<%=mapDto.getSeq() %>() {
	map<%=mapDto.getSeq() %> = new Tmap.Map({div:"map_div<%=mapDto.getSeq() %>", width:'350px', height:'350px'});
	map<%=mapDto.getSeq() %>.addControl(new Tmap.Control.MousePosition());
	var markerLayer<%=mapDto.getSeq() %> = new Tmap.Layer.Markers();
	map<%=mapDto.getSeq() %>.addLayer(markerLayer<%=mapDto.getSeq() %>);
	map<%=mapDto.getSeq() %>.events.register("click", map<%=mapDto.getSeq() %>, onClickMap<%=mapDto.getSeq() %>);
	searchRoute<%=mapDto.getSeq() %>();
}

	function searchRoute<%=mapDto.getSeq() %>(){
	    var routeFormat<%=mapDto.getSeq() %> = new Tmap.Format.KML({extractStyles:true, extractAttributes:true});
	/*    var startX = 14132105.182794;
	    var startY = 4519396.684182;  */
	    var startX = <%=mapDto.getLoc1X()%>;
	    var startY = <%=mapDto.getLoc1Y()%>;
	    var endX = <%=mapDto.getLoc2X()%>;
	    var endY = <%=mapDto.getLoc2Y()%>;
	  	
	    startX+="";
	    startY+="";
	    endX+="";
	    endY+="";
	    var str =(String)(get3857LonLat(startX,startY));
			var strmap = str.split(',');
			var start1 = strmap[0].split('=');
			var start2 = strmap[1].split('=');
			var loc1x = start1[1];
			var loc1y = start2[1];
		
			var str2 =(String)(get3857LonLat(endX,endY));
			var strmap2 = str2.split(',');
			var end1 =strmap2[0].split('=');
			var end2 =strmap2[1].split('=');
			var loc2x = end1[1];
			var loc2y = end2[1];
			
		/* var startX=;
		var statry=str.[1]; */
	/* 		console.log(get3857LonLat(startX,startY)); */
	    var urlStr<%=mapDto.getSeq() %> = "https://apis.skplanetx.com/tmap/routes?version=1&format=xml";
	    urlStr<%=mapDto.getSeq() %> += "&startX="+loc1x;
	    urlStr<%=mapDto.getSeq() %> += "&startY="+loc1y;
	    urlStr<%=mapDto.getSeq() %> += "&endX="+loc2x;
	    urlStr<%=mapDto.getSeq() %> += "&endY="+loc2y;
	    urlStr<%=mapDto.getSeq() %> += "&appKey=b049998c-19f6-379f-893e-bd72e90ed980";
	    var prtcl<%=mapDto.getSeq() %> = new Tmap.Protocol.HTTP({
	                                        url: urlStr<%=mapDto.getSeq() %>,
	                                        format:routeFormat<%=mapDto.getSeq() %>
	                                        });
	    var routeLayer<%=mapDto.getSeq() %> = new Tmap.Layer.Vector("route", {protocol:prtcl<%=mapDto.getSeq() %>, strategies:[new Tmap.Strategy.Fixed()]});
	    routeLayer<%=mapDto.getSeq() %>.events.register("featuresadded", routeLayer<%=mapDto.getSeq() %>, onDrawnFeatures<%=mapDto.getSeq() %>);
	    map<%=mapDto.getSeq() %>.addLayer(routeLayer<%=mapDto.getSeq() %>);
	}
	 var pr_3857 = new Tmap.Projection("EPSG:3857");
	 var pr_4326 = new Tmap.Projection("EPSG:4326");//WGS84 GEO
	 
	 function get3857LonLat(coordX, coordY){
		    return new Tmap.LonLat(coordX, coordY).transform(pr_4326, pr_3857);
		}
		//경로 그리기 후 해당영역으로 줌
		function onDrawnFeatures<%=mapDto.getSeq() %>(e){
			map<%=mapDto.getSeq() %>.zoomToExtent(this.getDataExtent());
		}
	function onClickMap<%=mapDto.getSeq() %>(evt){
	    console.log("type = "+evt.type);
	    //alert("clientX = "+evt.clientX + ", clientY = "+evt.clientY);
	    console.log("clientX = "+evt.clientX);
	    console.log("clientY = "+evt.clientY);
	    
	    var realXY<%=mapDto.getSeq() %> =  map<%=mapDto.getSeq() %>.getLonLatFromPixel(new Tmap.Pixel(evt.clientX, evt.clientY));
	    alert(realXY<%=mapDto.getSeq() %>);
	}

	</script>