
function writeArticle(){
	if(document.writeForm.subject.value == ""){
		alert("제목을 입력하세요");
		return;
	}else if(document.writeForm.content.value == ""){
		alert("내용을 입력하세요");
		return;
	}else{
			document.writeForm.action = "<%=root%>/picture";
			document.writeForm.submit();
		}
	}


//function moveModify(seq) {
//	document.commonForm.act.value = "mvmodify";
//	document.commonForm.bcode.value = bcode;
//	document.commonForm.pg.value = ""; //글쓰기 페이지니까 항상 1
//	document.commonForm.key.value = "";
//	document.commonForm.word.value = "";
//	document.commonForm.seq.value = seq;
//	
//	//document.commonForm.action = root + "/reboard";
//	document.commonForm.action.value = "<%=root%>/picture";
//	document.commonForm.submit();
//}




function moveWrite() {
	document.commonForm.act.value = "mvwrite";
	document.commonForm.bcode.value = bcode;
	document.commonForm.pg.value = "1";
	document.commonForm.key.value = "";
	document.commonForm.word.value = "";
	
	document.commonForm.action = root + control;
	document.commonForm.submit();
}

function firstArticle() {
	document.commonForm.act.value = "list";
	document.commonForm.bcode.value = bcode;
	document.commonForm.pg.value = "1";
	document.commonForm.key.value = "";
	document.commonForm.word.value = "";
	
	document.commonForm.action = root + control;
	document.commonForm.submit();
}

function listArticle(mpg) {
	document.commonForm.act.value = "mvgallerylist";
	document.commonForm.bcode.value = bcode;
	document.commonForm.pg.value = mpg;
	document.commonForm.key.value = key;
	document.commonForm.word.value = word;
	
	document.commonForm.action = root + control ;
	document.commonForm.submit();
}

function viewArticle(seq) {
	
	document.commonForm.act.value = "view";
	document.commonForm.bcode.value = bcode;
	document.commonForm.pg.value = pg;
	document.commonForm.key.value = key;
	document.commonForm.word.value = word;
	document.commonForm.seq.value = seq;
	
	document.commonForm.action = root + control;
	document.commonForm.submit();
}

function deleteArticle(seq) {
	
		document.commonForm.act.value = "delete";
		document.commonForm.bcode.value = bcode;
		document.commonForm.pg.value = pg;
		document.commonForm.key.value = key;
		document.commonForm.word.value = word;
		document.commonForm.seq.value = seq;
		
		document.commonForm.action = root + control;
		document.commonForm.submit();
	
}




