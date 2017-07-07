<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=6d432994bce4d7c8c4c3ad20a20c496b&libraries=services,clusterer,drawing"></script>
	<%@ include file="/common/public.jsp" %>
<script type="text/javascript" src="<%=root%>/js/mapfunction.js"></script>
<script type="text/javascript" src="<%=root%>/js/maplocationfunction.js"></script>

<link rel="stylesheet" type="text/css" href="<%=root%>/css/mapCss.css" />

<script type="text/javascript">
	var root = "<%=root%>";

	var addr = null; // 필요한 주소값 만을 온전히 받을 수 있도록 함수를 조정해야한다.

	var flag = false; //카테고리 검색 시 기존 인포윈도와의 충돌 방지
	var tag = ""; // 카테고리 값
	var keyword = null;

	var latlng;
	
	// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
	var markers = [];

	
</script>

<body>
	<section id="map">
		<div id="map_div"></div>
		<script>
			var cen1 = 37.57319;
			var cen2 = 126.96658;
			var container = document.getElementById('map_div');
			var options = {
				center : new daum.maps.LatLng(cen1, cen2),
				level : 4
			};
		
			var map = new daum.maps.Map(container, options);
			//////////////////////////
			// 지도에 컨트롤러를 추가해줍니다. //
			//////////////////////////
			// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
			var mapTypeControl = new daum.maps.MapTypeControl();
		
			// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
			// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
			map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
		
			// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
			var zoomControl = new daum.maps.ZoomControl();
			map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
		
			// 지도를 클릭한 위치에 표출할 마커입니다
			var marker = new daum.maps.Marker({});
			// 지도에 마커를 표시합니다
			//marker.setMap(map);
		
			//////////////////////
			// 주소값 호출을 위한 코드 //
			//////////////////////
			infowindow = new daum.maps.InfoWindow({
				zindex : 1
			}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
		
			//주소-좌표 변환 객체를 생성합니다
			var geocoder = new daum.maps.services.Geocoder();
		
			// 지도에 클릭 이벤트를 등록합니다
			// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		
			////////////////////////////////////////////
			// 마우스 클릭 이벤트 (클릭시 마커와 좌표 값을 가져온다) //////
			////////////////////////////////////////////
			daum.maps.event.addListener(map, 'click', function(mouseEvent) {
				// 지도에 마커를 표시합니다
				marker.setMap(map);
		
				// 클릭한 위도, 경도 정보를 가져옵니다 
				latlng = mouseEvent.latLng;
		
				// 마커 위치를 클릭한 위치로 옮깁니다
				marker.setPosition(latlng);
		
				//////////////////////////////////////////////
				// 마우스 클릭 이벤트 (클릭시 해당 좌표의 주소를 반환한다.) //
				//////////////////////////////////////////////
		
				searchDetailAddrFromCoords(mouseEvent.latLng, function(status, result) {
					if (status === daum.maps.services.Status.OK) {
						var detailAddr = !!result[0].roadAddress.name ? '<div>도로명주소 : ' + result[0].roadAddress.name + '</div>' : '';
						detailAddr += '<div>지번 주소 : ' + result[0].jibunAddress.name + '</div>';
		
						var content = '<div class="bAddr">' +
							detailAddr + '</div>';
		
						// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
						infowindow.setContent(content);
						infowindow.open(map, marker);
		
		
						addr = result[0].jibunAddress.name;
					}
				});
			});		
		</script>
		
		
		<div class="right">
			<p>
				<input type="checkbox" id="chkBicycle"
					onclick="javascript:setOverlayMapTypeId();" /> 자전거도로 정보 보기
			</p>

			<!-- 값을 넘기기 위한 form -->
			<form id="draftForm" name="draftForm" method="post" action=""
				style="margin: 0px">
				<input type="hidden" name="act" value="mapview">
				<input type="hidden" name="loc1X" value="">
				<input type="hidden" name="loc1Y" value="">
				<input type="hidden" name="loc2X" value="">
				<input type="hidden" name="loc2Y" value="">
				<input type="hidden" name="sec1X" value="">
				<input type="hidden" name="sec1Y" value="">
				<input type="hidden" name="sec2X" value="">
				<input type="hidden" name="sec2Y" value="">
				<input type="hidden" name="sec3X" value="">
				<input type="hidden" name="sec3Y" value="">
				<input type="hidden" name="loc1" value="">
				<input type="hidden" name="loc2" value="">
				<input type="hidden" name="sec1" value="">
				<input type="hidden" name="sec2" value="">
				<input type="hidden" name="sec3" value="">
				<input type="hidden" name="memo" value="">
				
				<input type="hidden" name="bcode" value="">
				<input type="hidden" name="pg" value="1">
				<input type="hidden" name="key" value="">
				<input type="hidden" name="word" value="">
			</form>
			<section class="top">

				<!-- 콤보 박스 선택에 따른 카테고리별 선택 -->
				<div class="select">
					<select class="sel" id="tag" onselect="" onclick="removeMarker(),categorySearch()">
						<option selected class="sele"><font color="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▼</font></option>
						<option class="selc" value="CS2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;편의점</option>
						<option class="selc" value="FD6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;식당</option>
						<option class="selc" value="AD5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;숙소</option>
					</select>
				</div>

				<div class="search">
					<input type="search" class="ser" id=sertxt>
					<div class="q" onclick='removeMarker(),keywordSearch()'></div>
				</div>

			</section>
			<!--top-->

			<!-- 각 지정처의 주소 값 및 좌표값 산출 -->
			<div class="nav">

				<div class="li lil">
					<div class="na_01 na">출발</div>
					<div class="na_02 na">
						<input type="text" class="text" id="get1">
					</div>
					<div class="na_03 na" onclick='input_Text1()'><input type="button" class="na_03 na" value="입력"></div>
				</div>
				
				<div class="li">
					<div class="na_01 na sz">경유1</div>
					<div class="na_02 na">
						<input type="text" class="text" id="get3">
					</div>
					<div class="na_03 na" onclick='input_Text3()'><input type="button" class="na_03 na" value="입력"></div>
				</div>

				<div class="li">
					<div class="na_01 na sz">경유2</div>
					<div class="na_02 na">
						<input type="text" class="text" id="get4">
					</div>
					<div class="na_03 na" onclick='input_Text4()'><input type="button" class="na_03 na" value="입력"></div>
				</div>

				<div class="li">
					<div class="na_01 na sz">경유3</div>
					<div class="na_02 na">
						<input type="text" class="text" id="get5">
					</div>
					<div class="na_03 na" onclick='input_Text5()'><input type="button" class="na_03 na" value="입력"></div>
				</div>
				
				<div class="li">
					<div class="na_01 na">도착</div>
					<div class="na_02 na">
						<input type="text" class="text" id="get2">
					</div>
					<div class="na_03 na" onclick='input_Text2()'><input type="button" class="na_03 na" value="입력"></div>
				</div>
				
			</div>
			
			
			<!-- 기타 정보를 위한 출력 칸 -->
			<div class="textarea">

				<div class="memo_top">&nbsp;&nbsp;메모</div><font color="#adadad" font-style="italic" font-size="5pt">
				<textarea class="memo" id="memo" onclick="this.value=''">여행에 관한 정보를 자유로이 메모하세요!</textarea></font>

				<div class="button">
					<div class="ok" onclick="moveLoc()">등록</div>
					<div class="no">취소</div>
				</div>
			</div>
		</div>
		<!--right-->
	</section>

</body>
