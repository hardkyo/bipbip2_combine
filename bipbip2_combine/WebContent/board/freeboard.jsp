<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import="java.util.*"
    import ="com.kitri.freeboard.model.*"
	import ="com.kitri.util.*"
	import ="com.kitri.member.model.*"     
%>
<%
List<FreeBoardDto> list = (List<FreeBoardDto>) request.getAttribute("freeList");
MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
PageNavigation pageNavigation = (PageNavigation) request.getAttribute("navigator");
if (list != null) {
%>     
<!--freeboard header -->
			<header>
				<h1><strong>자유게시판</strong></h1>
			</header>
<!--freeboard content-->
			<div class="content">
				<section class="col-md-9">
					<article class="">
						<div class="board">
<!-- top ad page -->						
							<div class="container-fluid" style="margin-top: 1%; margin-bottom: 2%;">
								<div class="row" style="text-align: center;">
									<div class="hotchoice col-md-2">
										<div class="">
											<button class="btn btn-default" type="button">기본</button>
										</div>
										<div class="">
											<button class="btn btn-default" type="button">HOT</button>
										</div>
										<div class="">
											<button class="btn btn-default" type="button">Best</button>
										</div>
									</div>
									<div class="col-md-10" style="border:2px solid black;">
										<div style="font-size: 5.1em;">
											광고입니다.!!!
										</div>
									</div>
								</div>
							</div>

<!-- board list -->
							<div class="boardlist">
								<form name="listForm" method="post" style="margin: 0px">
									<table class="table" style="table-layout: fixed;">
	
<!-- board list head -->
										<thead class="">
											<tr>
												<th class="centercolumn" width="11%">#</th>
												<th nowrap class="board_bar">|</th>
												<th></th>								
												<th class="subject" width="54%">&nbsp;&nbsp;&nbsp;제목</th>
												<th nowrap class="board_bar">|</th>												
												<th class="centercolumn" width="11%">글쓴이</th>
												<th nowrap class="board_bar">|</th>												
												<th class="centercolumn" width="10%">날짜</th>
												<th nowrap class="board_bar">|</th>								
												<th class="centercolumn" width="7%">조회</th>
												<th nowrap class="board_bar">|</th>
												<th class="centercolumn" width="8%">추천</th>
											</tr>
											<tr>
												<th class="bg_board_title_02" height="1" colspan="12" style="overflow: hidden; padding: 0px"></th>
											</tr>
										</thead>
	
<!-- board body -->
										<tbody>
<%
	int size = list.size();
	if (size != 0) {
		for (FreeBoardDto dto: list) {
		//	System.out.println(reboardDto.getSubject());
%>
											<tr>
												<td class="centercolumn"><%=dto.getSeq()%></td>
												<td></td>
												<td nowrap class="onetext" style="padding-right: 5px"></td>												
												<td class="subject" style="word-break: break-all; overflow:hidden; text-overflow:ellipsis">
													<%if (dto.getLev() != 0) {
														for (int i=0; i<dto.getLev(); i++) {%>
															&nbsp;&nbsp;
														<%}%>
													<img src="/bipbip2_combine/img/indent.JPG" width="17" height="17" >
													<%}%>
													<a id="article-view" class="memo-article" href="javascript:viewArticle('<%=dto.getSeq()%>');">													
														<nobr><%=dto.getSubject()%>&nbsp;&nbsp;</nobr>
													</a>
												</td>
												<td></td>												
												<th class="centercolumn" style="word-break: break-all;"><%=dto.getId()%></th>	
														<td></td>
																															
												<td align="center" class="centercolumn"><%=dto.getLogtime()%></td>
														<td></td>
												
												<td align="center" class="centercolumn"><%=dto.getHit()%></td>
														<td></td>												
												<td align="center" class="centercolumn">
													<span class="glyphicon glyphicon-thumbs-up" style="color: red;"></span>
													<span id="up-count"><%=dto.getUp()%></span>
												</td>
											</tr>
											<tr>
												<td bgcolor="#ededed" height="1" colspan="12" style="overflow: hidden; padding: 0px"></td>
											</tr>											
	<%
		}
	} else {
	%>
<!-- if there is no article searched -->
											<tr>
												<td  align="center" class="text_gray" colspan="11">
												<br/>
													게시글이 없습니다.
												<br/>
												</td>
											</tr>
											<tr>
												<td bgcolor="#ededed" height="1" colspan="12"
													style="overflow: hidden; padding: 0px"></td>
											</tr>
	<%
	}
	%>
										</tbody>
									</table>
								</form>
<!-- paging navigation -->
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
<!-- search -->
					<div class="">
						<form name="searchForm" method="get" action="">
							<input type="hidden" name="act" value="list">
							<input type="hidden" name="bcode" value="1">
							<input type="hidden" name="pg" value="1">
							
							<div class="search-container" id="bs-example-navbar-collapse-1">
								<div class="search-wrapper">
									<div class="inner">
										<select name="key" class="form-control">
											<option value='subject' selected>제목</option>
											<option value='content'>내용</option>
											<option value='subject||content'>제목+내용</option>
											<option value='id'>회원아이디</option>
											<option value='name'>이름</option>
										</select>
									</div>
									<div class="inner word">
										<input class="form-control find" type="text" name="word" onkeypress="javascript:if(event.keyCode == 13) {searchArticle();}" />
									</div>
									<div class="inner">
										<button type="button" class="btn btn-info find" onclick="javascript:searchArticle();">
											<span class="glyphicon glyphicon-search"></span> Search
										</button>
									</div>
								</div>
							</div>
						</form>
<!-- write button for modal-->
				<%if (memberDto !=null) {%>
						<div class="writebtn-containter">
							<div class="writebtn-wrapper">
								<button data-backdrop="static" id="writebtn" type="button" class="btn btn-info" data-toggle="modal" data-target="#writemodal">글쓰기</button>
							</div>
						</div>
				<%}%>
					</div>
				</section>
								
<!-- aside hot content-->
				<div class="col-md-3 col-sm-4 col-xs-12">
					<aside class="col-md-12">
						<div class="ads">
							<div style="overflow:hidden;">
								<div style="*zoom:1;padding:0px 0px 0px 0px !important; padding:none !important;">
									<div class="mydocWrap" id="mydocDocuments">
							   			<div class="w-header">
							        		<div class="w-title">
							    				<h4><a href="">내 글 반응</a></h4>
							    			</div>
								       		<div class="w-barWrap">
								           		<div class="w-bar">
								           		</div>
								        	</div>
							    		</div>
										<div class="non_action" style="display:block;">
							        		새 반응이 없습니다
							    		</div>    
									</div>
								</div>
							</div>
							<div style="overflow:hidden;">
								<div style="*zoom:1;padding:0px 0px 0px 0px !important; padding:none !important;">
									<div class="mydocWrap" id="mydocComments">
						    			<div class="w-header">
						       				<div class="w-title">
						    					<h4><a href="">내 댓글 반응</a></h4>
						    				</div>
						        			<div class="w-barWrap">
						            			<div class="w-bar"></div>
						        			</div>
						    			</div>
						            	<div class="non_action" style="display:block;">
						        			새 반응이 없습니다
						    			</div>   
						    		</div>
								</div>
							</div>
					
						</div>
					</aside>
				</div>
				<div class="col-md-3 col-sm-4 col-xs-12">
					<aside class="col-md-12">
						<div style="overflow:hidden;">
							<div style="*zoom:1;padding:0px 0px 0px 0px !important; padding:none !important;">
								<div class="newslatestWrap">
							    	<div class="w-header">
							        	<div class="w-title">
							    			<h4><a href="javascript:hotlistArticle();">HOT</a></h4>
							    		</div>
							        	<div class="w-barWrap">
							            	<div class="w-bar">
							            	</div>
							        	</div>
							    	</div>
							    	<ul id ="hotlist">
	
									</ul>
								</div>
							</div>
						</div>					
					</aside>
				</div>
			</div>
<!-- write modal -->
			<div class="modal fade" id="writemodal" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
<!-- write modal header -->
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">글쓰기</h4>
						</div>
<!-- write modal body -->
						<div class="modal-body">
							<form name="writeForm" method="post" action="" onsubmit="return false;">
								<input type="hidden" name="act" id="act" value="freewrite"/>
								<input type="hidden" name="bcode" value="1"/>
								<input type="hidden" name="pg" value=""/>
								<input type="hidden" name="key" value=""/>
								<input type="hidden" name="word" value=""/>
								<input type="hidden" name="seq" value=""/>
								<input type="hidden" name="content" value=""/>
								
								<div class="form-group" style="vertical-align: bottom;">
									<label for="usr">제목</label>
									<input type="text" class="form-control" id="subject" name="subject" />
								</div>
							<label for="usr">내용</label>
							<div id="summernote">
								<p>왜 처음에 크기가 고정이 안 될까?</p>
								<p>왜 왜 제이쿼리를 인식 못 하는건지.. ㅠㅠ</p>
								<p>글 써주세요!!</p>
								<p>왜 안 될까요?</p>
								<p>ㅋㅋㅋㅋㅋ</p>
							</div>
<!-- write modal footer -->
								<div class="modal-footer">
									<button onclick="javascript:freeboardwrite();"
										type="button" class="btn btn-default" id="writebtn" name="writebtn"	value="">Write</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
<%
} else {
%>
<script>
alert("부적절한 URL 접근입니다.");
document.location.href = "/bipbip2_combine/index.jsp";
</script>
<%
}
%>