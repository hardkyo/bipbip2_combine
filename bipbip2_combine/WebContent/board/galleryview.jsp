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

function moveModify(seq) {
	document.commonForm.act.value = "mvmodify";
	document.commonForm.bcode.value = bcode;
	document.commonForm.pg.value = ""; //글쓰기 페이지니까 항상 1
	document.commonForm.key.value = "";
	document.commonForm.word.value = "";
	document.commonForm.seq.value = seq;
	document.commonForm.action.value = "<%=root%>/gallery";
	document.commonForm.submit();

}
	

</script>

		
				
<div class="content">
				<article class="">
					
						<div class="board">
							<div class="container-fluid" style="margin-top: 1%; margin-bottom: 2%;">
								<div class="row" style="text-align: center;">
									<div class="hotchoice col-md-3" >
										<div class="">
										
											<button class="btn btn-default" type="button" >
											<a href="javascript:listArticle('<%=pg%>');">List</a></button> 
<%
if(memberDto.getId().equals(galleryDto.getId())) {
System.out.println(memberDto.getId() + " 434343  >>>>>>>>>>>>>>>>  "  + galleryDto.getId());
%>												
											
											
											<button class="btn btn-default" type="button" >
											<a href="javascript:moveModify('<%=galleryDto.getSeq() %>');">Modify</a></button> 																	
											
											<button class="btn btn-default" type="button">
											<a href="javascript:deleteArticle('<%=galleryDto.getSeq() %>');">
											Delete</a></button>
										
<%
	}
%>										
										
										</div>
									</div>
								</div>
							</div>
						</div>
										
							<div class="boardlist">
								<form name="viewform" method="post" style="margin: 0px">
									<table class="table">
										<thead class="">
											<tr>
												<th class="" colspan="12"><%=galleryDto.getSubject()%></th>
												<th></th>								
											<tr>
												<th class="bg_board_title_02" height="1" colspan="12" style="overflow: hidden; padding: 0px"></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<p align="center">
												<img src="<%=root%>/upload/album/<%=galleryDto.getSaveFolder() %>
												/<%=galleryDto.getSavePicture() %>">
												</p>		
											</tr>
											<tr>
												<p><td class="centercolumn" colspan="12"><%=galleryDto.getContent()%></td></p>
												<td></td>			
											</tr>
											<tr>
												<td bgcolor="#ededed" height="1" colspan="12" style="overflow: hidden; padding: 0px"></td>
											</tr>				
										</tbody>
									</table>
								</form>
							</div>
						</article>
					</div>



		
					
	<%
}
	%>				