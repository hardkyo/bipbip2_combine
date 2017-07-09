$(document).ready(function() {
    $('#summernote').summernote({
  	  height: 300,
	  minHeight: 200,
	  maxHeight: null,
	  focus: true
    });
});

function freeboardwrite() {
	getControl()
	var content = $('#summernote').summernote('code');

	if(document.writeForm.subject.value == ""){
		alert("제목을 입력해주세요!");
		return;
	}else if(content == ""){
		alert("내용을 입력해 주세요!");
		return;
	}else{
		document.writeForm.content.value = content;
		document.writeForm.action = root + control;
		document.writeForm.submit();
	}
}

function searchArticle () {
	getControl()
	
	document.searchForm.action = root + control;
	document.searchForm.submit();
}

function firstArticle() {
	getControl()
	
	document.commonForm.act.value = "list";
	document.commonForm.bcode.value = bcode;
	document.commonForm.pg.value = "1";
	document.commonForm.key.value = "";
	document.commonForm.word.value = "";
	document.commonForm.action = root + control;
	document.commonForm.submit();
}

function listArticle(mpg) {
	getControl()
	
	document.commonForm.act.value = "list";
	document.commonForm.bcode.value = bcode;
	document.commonForm.pg.value = mpg;
	document.commonForm.key.value = key;
	document.commonForm.word.value = word;
	document.commonForm.action = root + control;
	document.commonForm.submit();
}

function viewArticle(seq) {
	getControl()
	
	document.commonForm.act.value = "view";
	document.commonForm.bcode.value = bcode;
	document.commonForm.pg.value = pg;
	document.commonForm.key.value = key;
	document.commonForm.word.value = word;
	document.commonForm.seq.value = seq;
	document.commonForm.action = root + control;
	document.commonForm.submit();
}

function modifyArticle(seq) {
	getControl()
	var content = $('#summernote').summernote('code');

	if(document.viewform.subject.value == ""){
		alert("제목을 입력해주세요!");
		return;
	}else if(content == ""){
		alert("내용을 입력해주세요!");
		return;
	}else{
		document.viewform.act.value = "modify";
		document.viewform.bcode.value = bcode;
		document.viewform.pg.value = pg;
		document.viewform.key.value = key;
		document.viewform.word.value = word;
		document.viewform.seq.value = seq;
		document.viewform.content.value = content;
	
		document.viewform.action = root + control;
		document.viewform.submit();
	}
}

function deleteArticle(seq, reply) {
	getControl()
	
	if (reply != 0) {
		alert("답변이 있습니다. 삭제가 불가능 합니다!")
	} else {
		document.commonForm.act.value = "delete";
		document.commonForm.bcode.value = 1;

		document.commonForm.pg.value = pg;
		document.commonForm.key.value = key;
		document.commonForm.word.value = word;
		document.commonForm.seq.value = seq;
		document.commonForm.action = root + control;
		document.commonForm.submit();
	}
}

function replyArticle(seq){
	var content = $('#summernote').summernote('code');
	getControl();

	if(document.viewform.subject.value == ""){
		alert("제목을 입력해 주세요!");
		return;
	}else if(content == ""){
		alert("내용을 입력해 주세요!");
		return;
	}else{
		document.viewform.act.value = "reply";
		document.viewform.bcode.value = bcode;
		document.viewform.pg.value = pg;
		document.viewform.key.value = key;
		document.viewform.word.value = word;
		document.viewform.seq.value = seq;
		document.viewform.pseq.value = seq;
		document.viewform.content.value = content;

		document.viewform.action = root + control;
		document.viewform.submit();
	}
}
	
function getControl() {
	
	switch (parseInt(bcode)) {
	case 1:
		control = "/freeboard";
		break;
	case 2:
		control = "/map";
		break;
	case 3:
		control = "/album";
		break;
	default:
		control = "/admin?act=main";
		break;
	}
}

///////// view modal btn control
$('#view-modifybtn').on('click', function () {
	$('#modal-modifybtn').css("display", "");
	$('#modal-replybtn').css("display", "none");
})
$('#view-replybtn').on('click', function () {
	$('#modal-replybtn').css("display", "");
	$('#modal-modifybtn').css("display", "none");
})



//updateUP
function plusUp(seq) {
	$.ajax({
		url: root + "/hotlist",
		type: "post",
		data: {"act" : "up", "seq" : seq},
		dataType: "text",
		success: updateUp
	});
}
function updateUp(text) {
	$('#view-up').empty();
	$('#view-up').text(text);
}


//updateDown
function plusDown(seq) {
	$.ajax({
		url: root + "/hotlist",
		type: "post",
		data: {"act" : "down", "seq" : seq},
		dataType: "text",
		success: updateDown
	});
}
function updateDown(text) {
	$('#view-down').empty();
	$('#view-down').text(text);
}