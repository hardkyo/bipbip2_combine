
		// 이곳은 map 패키지에서 좌표 값과 주소값을 보내기 위한 함수들을 정리한 곳입니다. //

////// 컨트롤러로 변수값 보내기 + 경로 확정 화면으로 이동///////////////////////
	function moveLoc() {
		if(loc1!=null && loc2!=null){
			var memo = document.getElementById("memo").value;
			document.draftForm.memo.value = memo;
			document.draftForm.action = root + "/map";
			document.draftForm.submit();
		}

	}
	// 경로 선택 화면으로 이동
	function movecall(){
		document.draftForm.action = root + "/map";
		document.draftForm.submit();
	}
	
	// 경로 목록 화면으로 이동
	function listArticle(mpg) {
		   document.commonForm.act.value = "list";
		   document.commonForm.bcode.value = bcode;
		   document.commonForm.pg.value = pg;
		   document.commonForm.key.value = key;
		   document.commonForm.word.value = word;
		   
		   document.commonForm.action = root + "/map";
		   document.commonForm.submit();
		}
	
	// 경로 수정 화면으로 이동
	

	// 글쓰기 버튼
	function movePlanRoute() {
		document.commonForm.act.value = "mvshowmap";
		document.commonForm.action = root + "/map";
		document.commonForm.submit();
	}

	// 경로 삭제