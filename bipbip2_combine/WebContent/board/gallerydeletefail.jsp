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
%>
<script type="text/javascript" src="js/gallery.js"></script>
<script type="text/javascript">
var control = "";
control = "/gallery";

	

</script>


<table width="100%" cellpadding="6" cellspacing="2" border="0"
	bgcolor="#ffffff" style="border: #e1e1e1 solid 1px">
	
	<tr>
		<td height="1" bgcolor="#e1e1e1"
			style="overflow: hidden; padding: 0px;"></td>
	</tr>
	<tr>
		<td class="bg_menu" width="100%" style="padding: 25px" height="35"
			align="center"><b>게시물 삭제가 실패 되었습니다.</b><br>
		<br>

		<div class="container-fluid" style="margin-top: 1%; margin-bottom: 2%;">
								<div class="row" style="text-align: center;">
									<div class="hotchoice col-md-2" >
										<div class="">
											<button class="btn btn-default" type="button" bgcolor="cyon" >
											<a href="javascript:listArticle('<%=pg%>');">List</a></button>
											</div>
										</div>
									</div>
								</div>
											
											
		</td>
	</tr>
</table>
<br>
</body>
</html>
