<%@page import="com.kitri.util.PageNavigation"%>
<%@page import="java.util.List"%>
<%@page import="com.kitri.gallery.model.GalleryDto"%>
<%@page import="com.kitri.util.Encoding"%>
<%@page import="com.kitri.util.NumberCheck"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
	String root = request.getContextPath();
	int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
	int pg = NumberCheck.nullToOne(request.getParameter("pg"));
	String key = Encoding.nullToBlank(request.getParameter("key"));
	String word = Encoding.isoToEuc(request.getParameter("word"));
	List<GalleryDto> list = (List<GalleryDto>) request.getAttribute("photoList");
	PageNavigation pageNavigation = (PageNavigation) request.getAttribute("navigator");
	if(list != null) {
%>


<head>


<!-- Custom CSS -->
<link href="css/4-col-portfolio.css" rel="stylesheet">
<script type="text/javascript" src="js/gallery.js"></script>
<script type="text/javascript">
var control = "";
control = "/gallery";

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

	

</script>

</head>
<body>

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					gallery <small></small>
				</h1>
			</div>
		</div>
		<!-- /.row -->

		<!-- Projects Row -->
		

			<div class="row">
				<div class="boardlist">
					<form name="listForm" method="post" style="margin: 0px">
					<input type="hidden" name="act"value="view">
<%
int size = list.size();
if(size != 0) {
	for(int i=0;i<size;i++) {
		GalleryDto galleryDto = list.get(i);
		System.out.println(list.get(0).getHit());
%>

	<div class="col-md-3 portfolio-item">
	<p><a href="javascript:viewArticle('<%=galleryDto.getSeq() %>');">
	<img src="<%=root %>/upload/album/<%=galleryDto.getSaveFolder() %>/<%=galleryDto.getSavePicture() %>" 
	width="100" height="100">
	</a></p>
		<tr>
			<td class="centercolumn" width="11%">#</td>
			<td nowrap class="board_bar"></td>
			<td></td>
			<p><td class="subject" width="54%">제목 :
			<a href="javascript:viewArticle('<%=galleryDto.getSeq() %>');">
			<b><%=galleryDto.getSeq() %>. <%=galleryDto.getSubject() %></b></a></td></p>
			<td nowrap class="board_bar"></td>
			<td class="centercolumn" width="11%">글쓴이 :<%=galleryDto.getName() %></td>
			<td nowrap class="board_bar"></td>
			<td class="centercolumn" width="10%">날짜 :<%=galleryDto.getLogtime() %></td>
			<td nowrap class="board_bar"></td>
			<td class="centercolumn" width="7%">조회 :<%=galleryDto.getHit() %></td>

		</tr>
		<tr>
			<td class="bg_board_title_02" height="1" colspan="12"
					style="overflow: hidden; padding: 0px"></td>
		</tr>

	</div>
		
<%	
	if(i % 4 == 3) {
%>
	<tr>
		<td class="bg_board_title_02" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>

	<tr>
		<td class="bg_board_title_02" height="1" colspan="11"
			style="overflow: hidden; padding: 0px"></td>
	</tr>	
		
	</div>
</div>


<%
		}
	}
}else{
%>
	<td>게시글이 없습니다.</td>
<%	
}
%>	
</div>
</form>
	<!-- /.row -->

	<hr>

	<!-- Pagination -->
	<div class="container-fluid">
									<div class="row" style="text-align: center;">
										<div class="col-md-12">
											<ul class="pagination">
 <%=pageNavigation.getNavigator()%>
												<li>
													<a><%=pageNavigation.getPageNo()%>/<%=pageNavigation.getTotalPageCount()%></a>
												</li>
											</ul>
										</div>
									</div>
								</div>				
							</div>
						</div>
					</article>
	<!-- /.row -->


	<!-- write button & write modal -->
	<div class="container">
		<button type="button" class="btn btn-info" data-toggle="modal"
			data-target="#myModal">글쓰기</button>

		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">

					<!-- write modal header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">글쓰기</h4>
					</div>
					<!-- write modal body -->
					<div class="modal-body">
						<form name="writeForm" method="post" action=""
							enctype="multipart/form-data">
							<input type="hidden" name="act" value="write">
							 <input	type="hidden" name="bcode" value="3"> 
							 <input	type="hidden" name="pg" value="1"> 
							 <input type="hidden" name="key" value="">
							  <input type="hidden" name="word"value="">


							<div class="form-group">
								<label for="usr">제목</label> <input type="text"
									class="form-control" id="subject" name="subject">
							</div>
							<div class="form-group">
								<label for="usr">사진첨부</label> <input type="file"
									class="form-control" id="picturename" name="picturename"
									size="76">
							</div>
							<div class="form-group">
								<label for="comment">Comment:</label>
								<textarea class="form-control" rows="15" id="content"
									name="content"></textarea>
							</div>
						</form>
					</div>
					<!-- write modal body -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default" id="writeBtn">
							<a href="javascript:writeArticle();">Write 
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<a href="javascript:history.back();"> Close 
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>




	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>

</html>
<%
}
%>