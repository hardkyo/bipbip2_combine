	
	var loc1X;
	var loc1Y;
	var loc2X;
	var loc2Y;
	var sec1X;
	var sec1Y;
	var sec2X;
	var sec2Y;
	var sec3X;
	var sec3Y;
	var loc1;
	var loc2;
	var sec1;
	var sec2;
	var sec3;
	var memo;
	var mapHit;
	
	// map 의 act 별 호출 함수 목록입니다.
	
//////컨트롤러로 변수값 보내기 ///////////////////////

	
	
	
	// 이곳은 지도 api 를 활성화 시키기 위한 함수들의 모음입니다. //

////////////////////자전거 도로 생성 함수//////////////////////
	var mapTypes = {
		bicycle : daum.maps.MapTypeId.BICYCLE,
	};

	function setOverlayMapTypeId() {
		for (var type in mapTypes) {
			map.removeOverlayMapTypeId(mapTypes[type]);
		}
		//var chkBicycle = document.getElementById('chkBicycle'),

		if (chkBicycle.checked) {
			map.addOverlayMapTypeId(mapTypes.bicycle);
		}

	}
   
	////////////////기능 함수 목록 /////////////////////

	// 주소 값 받기 함수 _ 이후 각 함수별로 받을 좌표값과 주소 값을 모두 지정해주어야한다.
	function input_Text1() {
		document.getElementById("get1").value = addr;
		loc1X=latlng.getLng();
		loc1Y=latlng.getLat();
		loc1 = addr;
		document.draftForm.loc1X.value = loc1X;
		document.draftForm.loc1Y.value = loc1Y;
		document.draftForm.loc1.value = loc1;
	}
	function input_Text2() {
		document.getElementById("get2").value = addr;
		loc2X=latlng.getLng();
		loc2Y=latlng.getLat();
		loc2 = addr;
		document.draftForm.loc2X.value = loc2X;
		document.draftForm.loc2Y.value = loc2Y;
		document.draftForm.loc2.value = loc2;
	}
	function input_Text3() {
		document.getElementById("get3").value = addr;
		sec1X=latlng.getLng();
		sec1Y=latlng.getLat();
		sec1=addr;
		document.draftForm.sec1X.value = sec1X;
		document.draftForm.sec1Y.value = sec1Y;
		document.draftForm.sec1.value = sec1;
	}
	function input_Text4() {
		document.getElementById("get4").value = addr;
		sec2X=latlng.getLng();
		sec2Y=latlng.getLat();
		sec2=addr;
		document.draftForm.sec2X.value = sec2X;
		document.draftForm.sec2Y.value = sec2Y;
		document.draftForm.sec2.value = sec2;
	}
	function input_Text5() {
		document.getElementById("get5").value = addr;
		sec3X=latlng.getLng();
		sec3Y=latlng.getLat();
		sec3=addr;
		document.draftForm.sec3X.value = sec3X;
		document.draftForm.sec3Y.value = sec3Y;
		document.draftForm.sec3.value = sec3;
	}

	
//////////////////////// View 페이지에서 값을 담는 중입니다. /////////
	
	function getViewData() {

		document.write(loc1X);
		document.write(loc2Y);
		document.write(sec1);
		document.write(memo);
		
		document.draftForm.loc1X.value = loc1X
		document.draftForm.loc1Y.value = loc1Y;
		document.draftForm.loc1.value = loc1;
		
		document.draftForm.loc2X.value = loc2X;
		document.draftForm.loc2Y.value = loc2Y;
		document.draftForm.loc2.value = loc2;

		document.draftForm.sec1X.value = sec1X;
		document.draftForm.sec1Y.value = sec1Y;
		document.draftForm.sec1.value = sec1;

		document.draftForm.sec2X.value = sec2X;
		document.draftForm.sec2Y.value = sec2Y;
		document.draftForm.sec2.value = sec2;

		document.draftForm.sec3X.value = sec3X;
		document.draftForm.sec3Y.value = sec3Y;
		document.draftForm.sec3.value = sec3;
		
		document.draftForm.memo.value = memo;

	}
	
	
	
	///////////////// 검색 함수 //////////////////////
	function keywordSearch() {
		keyword = document.getElementById("sertxt").value;
		console.log(keyword);
		if (keyword != "") {
			// 장소 검색 객체를 생성합니다
			
			var kps = new daum.maps.services.Places();

			// 키워드로 장소를 검색합니다
			kps.keywordSearch(keyword, placesSearchCBK);
		}  else {
			alert("ERROR : Empty text box");
		}
	}
	
	function categorySearch(){
		tag = document.getElementById("tag").value;

		// 장소 검색 객체를 생성합니다
		var cps = new daum.maps.services.Places(map);

		// 카테고리로 검색합니다
		cps.categorySearch(tag, placesSearchCB, {
			useMapBounds : true
		});
	}

	/////////////////////////
	// 카테고리 별 검색을 위한 코드 //
	/////////////////////////
	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCB(status, data, pagination) {
		if (status === daum.maps.services.Status.OK) {
			for (var i = 0; i < data.places.length; i++) {
				displayMarker(data.places[i]);
			}
		}
	}
	
	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCBK (status, data, pagination) {
	    if (status === daum.maps.services.Status.OK) {

	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        var bounds = new daum.maps.LatLngBounds();

	        for (var i=0; i<data.places.length; i++) {
	            displayMarker(data.places[i]);    
	            bounds.extend(new daum.maps.LatLng(data.places[i].latitude, data.places[i].longitude));
	        }       

	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	        map.setBounds(bounds);
	    } 
	}

	// 지도에 마커를 표시하는 함수입니다
	function displayMarker(place) {
		// 마커를 생성하고 지도에 표시합니다
		var marker = new daum.maps.Marker({
			map : map,
			position : new daum.maps.LatLng(place.latitude, place.longitude)
		});

		// 마커에 클릭이벤트를 등록합니다
		daum.maps.event.addListener(marker, 'click', function() {
			// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.title + '</div>');
			infowindow.open(map, marker);
		});

		markers.push(marker);
	}

	function removeMarker() {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
	}

	function searchAddrFromCoords(coords, callback) {
		// 좌표로 행정동 주소 정보를 요청합니다
		geocoder.coord2addr(coords, callback);
	}

	function searchDetailAddrFromCoords(coords, callback) {
		// 좌표로 법정동 상세 주소 정보를 요청합니다
		geocoder.coord2detailaddr(coords, callback);
	}