<%@page import="com.kitri.util.Encoding"%>
<%@page import="com.kitri.util.NumberCheck"%>
<%@page import="com.kitri.member.model.MemberDto"%>
<%@page import="com.kitri.gallery.model.GalleryDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import="java.util.*"
  %>

<%
String root = request.getContextPath();
int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
int pg = NumberCheck.nullToOne(request.getParameter("pg"));
String key = Encoding.nullToBlank(request.getParameter("key"));
String word = Encoding.isoToEuc(request.getParameter("word"));

GalleryDto galleryDto = (GalleryDto)request.getAttribute("article");
MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
if (galleryDto != null) {
%>     
<script type="text/javascript" src="js/gallery.js"></script>
<script type="text/javascript">
var control = "";
control = "/gallery";

function modifyArticle(){
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
	
</script>
<div class="">
						<form name="writeForm" method="post" action=""
							enctype="multipart/form-data">
							<input type="hidden" name="act" value="modify">
							 <input	type="hidden" name="bcode" value="3"> 
							 <input	type="hidden" name="pg" value="1"> 
							 <input type="hidden" name="key" value="">
							  <input type="hidden" name="word"value="">
							  <input type="hidden" name="seq"value="<%=galleryDto.getSeq() %>">
							 


							<div class="form-group">
								<label for="usr">제목</label> <input type="text"
									class="form-control" id="subject" name="subject" value="<%=galleryDto.getSubject()%>">
							</div>
							<div class="form-group">
								<label for="usr">사진첨부</label> <input type="file"
									class="form-control" id="picturename" name="picturename" 
										size="76">
							</div>
							<div class="form-group">
								<label for="comment">Comment:</label>
								<textarea class="form-control" rows="15" id="content"
									name="content"><%=galleryDto.getContent()%></textarea>
							</div>
						</form>
					</div>
					
					<div class="">
						<button type="button" class="btn btn-default" id="writeBtn">
							<a href="javascript:modifyArticle();">Write 
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<a href="javascript:history.back();"> Close 
						</button>
				</div>
<%
}
%>
