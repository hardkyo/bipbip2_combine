	
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
	
	// map �� act �� ȣ�� �Լ� ����Դϴ�.
	
//////��Ʈ�ѷ��� ������ ������ ///////////////////////

	
	
	
	// �̰��� ���� api �� Ȱ��ȭ ��Ű�� ���� �Լ����� �����Դϴ�. //

////////////////////������ ���� ���� �Լ�//////////////////////
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
   
	////////////////��� �Լ� ��� /////////////////////

	// �ּ� �� �ޱ� �Լ� _ ���� �� �Լ����� ���� ��ǥ���� �ּ� ���� ��� �������־���Ѵ�.
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
		if(loc1!=null){
			document.getElementById("get2").value = addr;
			loc2X=latlng.getLng();
			loc2Y=latlng.getLat();
			loc2 = addr;
			document.draftForm.loc2X.value = loc2X;
			document.draftForm.loc2Y.value = loc2Y;
			document.draftForm.loc2.value = loc2;
		} else {
			alert("출발지가 지정되지 않았습니다.")
		}
		
	}
	function input_Text3() {
		if(loc1!=null){
			document.getElementById("get3").value = addr;
			sec1X=latlng.getLng();
			sec1Y=latlng.getLat();
			sec1=addr;
			document.draftForm.sec1X.value = sec1X;
			document.draftForm.sec1Y.value = sec1Y;
			document.draftForm.sec1.value = sec1;
		} else {
			alert("출발지가 지정되지 않았습니다.")
		}
	
	}
	function input_Text4() {
		if(sec1!=null){
			document.getElementById("get4").value = addr;
			sec2X=latlng.getLng();
			sec2Y=latlng.getLat();
			sec2=addr;
			document.draftForm.sec2X.value = sec2X;
			document.draftForm.sec2Y.value = sec2Y;
			document.draftForm.sec2.value = sec2;
		} else {
			alert("1번 경유지가 지정되지 않았습니다.")
		}
		
	}
	function input_Text5() {
		if(sec2!=null){
			document.getElementById("get5").value = addr;
			sec3X=latlng.getLng();
			sec3Y=latlng.getLat();
			sec3=addr;
			document.draftForm.sec3X.value = sec3X;
			document.draftForm.sec3Y.value = sec3Y;
			document.draftForm.sec3.value = sec3;
		} else {
			alert("2번 경유지가 지정되지 않았습니다.")
		}
		
	}

	
//////////////////////// View ���������� ���� ��� ���Դϴ�. /////////
	
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
	
	
	
	///////////////// �˻� �Լ� //////////////////////
	function keywordSearch() {
		keyword = document.getElementById("sertxt").value;
		console.log(keyword);
		if (keyword != "") {
			// ��� �˻� ��ü�� �����մϴ�
			
			var kps = new daum.maps.services.Places();

			// Ű����� ��Ҹ� �˻��մϴ�
			kps.keywordSearch(keyword, placesSearchCBK);
		}  else {
			alert("ERROR : Empty text box");
		}
	}
	
	function categorySearch(){
		tag = document.getElementById("tag").value;

		// ��� �˻� ��ü�� �����մϴ�
		var cps = new daum.maps.services.Places(map);

		// ī�װ��� �˻��մϴ�
		cps.categorySearch(tag, placesSearchCB, {
			useMapBounds : true
		});
	}

	/////////////////////////
	// ī�װ� �� �˻��� ���� �ڵ� //
	/////////////////////////
	// Ű���� �˻� �Ϸ� �� ȣ��Ǵ� �ݹ��Լ� �Դϴ�
	function placesSearchCB(status, data, pagination) {
		if (status === daum.maps.services.Status.OK) {
			for (var i = 0; i < data.places.length; i++) {
				displayMarker(data.places[i]);
			}
		}
	}
	
	// Ű���� �˻� �Ϸ� �� ȣ��Ǵ� �ݹ��Լ� �Դϴ�
	function placesSearchCBK (status, data, pagination) {
	    if (status === daum.maps.services.Status.OK) {

	        // �˻��� ��� ��ġ�� �������� ���� ������ �缳���ϱ�����
	        // LatLngBounds ��ü�� ��ǥ�� �߰��մϴ�
	        var bounds = new daum.maps.LatLngBounds();

	        for (var i=0; i<data.places.length; i++) {
	            displayMarker(data.places[i]);    
	            bounds.extend(new daum.maps.LatLng(data.places[i].latitude, data.places[i].longitude));
	        }       

	        // �˻��� ��� ��ġ�� �������� ���� ������ �缳���մϴ�
	        map.setBounds(bounds);
	    } 
	}

	// ������ ��Ŀ�� ǥ���ϴ� �Լ��Դϴ�
	function displayMarker(place) {
		// ��Ŀ�� �����ϰ� ������ ǥ���մϴ�
		var marker = new daum.maps.Marker({
			map : map,
			position : new daum.maps.LatLng(place.latitude, place.longitude)
		});

		// ��Ŀ�� Ŭ���̺�Ʈ�� ����մϴ�
		daum.maps.event.addListener(marker, 'click', function() {
			// ��Ŀ�� Ŭ���ϸ� ��Ҹ��� ���������쿡 ǥ��˴ϴ�
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
		// ��ǥ�� ������ �ּ� ������ ��û�մϴ�
		geocoder.coord2addr(coords, callback);
	}

	function searchDetailAddrFromCoords(coords, callback) {
		// ��ǥ�� ������ �� �ּ� ������ ��û�մϴ�
		geocoder.coord2detailaddr(coords, callback);
	}
	

	function memoClear(){
		var flag = false
		if(flag = false){
			this.value=''
			flag = true;
		}
	}