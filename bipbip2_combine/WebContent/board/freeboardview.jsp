<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"
    import="java.util.*"
    import ="com.kitri.freeboard.model.*"
	import ="com.kitri.util.*"
	import ="com.kitri.member.model.*"     
%>
<%
String root = request.getContextPath();
int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
int pg = NumberCheck.nullToOne(request.getParameter("pg"));
String key = Encoding.nullToBlank(request.getParameter("key"));
String word = request.getParameter("word");

FreeBoardDto freeboardDto = (FreeBoardDto)request.getAttribute("article");
MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
%>
<input type="hidden" id="memoseq" value="<%=freeboardDto.getSeq()%>"/>  
<input type="hidden" id="memoid" value="<%=freeboardDto.getId()%>"/>  
   
<!--freeboard header -->
			<header>
				<h1>자유게시판</h1>
			</header>
<!--freeboard content-->
			<div class="content">
				<section class="col-md-9 col-sm-8 col-xs-12">
					<article class="col-md-12">
						<div class="board">
<!-- top ad page -->						
							<div class="col-md-12 container-fluid" style="margin-top: 1%; margin-bottom: 2%;">
								<div class="col-md-12" style="text-align: center;">
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

							<div class="col-md-12 container boardlist">
								<div class="col-md-12" style="margin: 0.2%;">
									<div class="col-md-10">
										<div style="font-weight: bold; font-size: 3em; padding: 2%;">
											<%=freeboardDto.getSubject()%>
										</div>
									</div>
									<div class="col-md-2">
										<div style="hegith:100%; font-size: 12px;">
											<%=freeboardDto.getLogtime()%>
										</div>
									</div>
								</div>
								<div class="col-md-12" style="margin: 1%;">
									<div class="col-md-7">
										<div>
											<font size="3px"><strong><%=freeboardDto.getId()%></strong></font>
										</div>
									</div>
									<div class="col-md-5" style="margin: auto; text-align: right;">
										<div class="col-md-5">
											<div>
												<strong>Hit :&nbsp;<%=freeboardDto.getHit()%></strong>
											</div>
										</div>
										<div class="col-md-7">
											<div>
												<strong>
												<span>추천: </span>
												<a href="javascript:plusUp('<%=freeboardDto.getSeq()%>');">
													<span class="glyphicon glyphicon-thumbs-up" style="color: red;"></span>
												</a> 
												<span id="view-up">
													<%=freeboardDto.getUp()%>
												</span>
												/
												<a href="javascript:plusDown('<%=freeboardDto.getSeq()%>');">
												<span class="glyphicon glyphicon-thumbs-down"  style="color: blue;"></span> 
												</a>
												<span id="view-down">
													<%=freeboardDto.getDown()%>
												</span>
												</strong>
											</div>
										</div>
									</div>								
								</div>									
								<div class="col-md-12">
										<div class="col-md-12" style="margin:1%;">
											<label for="comment">내용:</label>
											<div class="panel panel-default">
												<div class="panel-body" style="font-size: 15px; padding: 1%; font-weight: bold;">
													<%=freeboardDto.getContent()%>
												</div>
											</div>
										</div>
								</div>
							</div>
						</div>
						<div class="container">
							<div class="col-md-12" style="text-align: center; margin:2%; margin-bottom: 3%;">
								<button onclick="javascript:plusUp('<%=freeboardDto.getSeq()%>');"
									type="button" class="btn btn-default">
									<strong>
									<span class="glyphicon glyphicon-thumbs-up" style="color: red;"></span>
									추천
									</strong>
								</button> 
								<button onclick="javascript:plusDown('<%=freeboardDto.getSeq()%>');"
									type="button" class="btn btn-default">
									<strong>
									<span class="glyphicon glyphicon-thumbs-down"  style="color: blue;"></span>
									 반대
									 </strong>
								</button>
							</div>
						</div>
					</article>
					
<!-- modify -->
					<div class="">
						<div class="col-md-12">					
<%
		if(memberDto != null) {
			if(memberDto.getId().equals(freeboardDto.getId())){
%>						
							<div class="col-md-6" style="padding-left: 6%;">
								<button onclick="javascript:mvmodifyArticle('<%=freeboardDto.getSeq()%>');" id="view-modifybtn"
									type="button" class="btn btn-info find"><span class="glyphicon glyphicon-share-alt"></span> 수정
								</button>
								<button onclick="javascript:deleteArticle('<%=freeboardDto.getSeq()%>', '<%=freeboardDto.getReply()%>');" id="view-deletebtn"  data-backdrop="static" 
										type="button" class="btn btn-info find" ><span class="glyphicon glyphicon-remove-circle"></span> 삭제
								</button>														
							</div>
							<div class="col-md-6" style="text-align: right; margin-bottom:2%; padding-right: 3%;">
								<button onclick="javascript:mvreplyArticle('<%=freeboardDto.getSeq()%>');" id="view-replybtn" 
										type="button" class="btn btn-info find" ><span class="glyphicon glyphicon-pencil"></span> 답글
								</button>							
							</div>							
<%
			}
			if(!memberDto.getId().equals(freeboardDto.getId())){
%>							
							<div class="col-md-12" style="text-align: right; margin-bottom:2%; padding-right: 3%;">
								<button onclick="javascript:mvreplyArticle('<%=freeboardDto.getSeq()%>');" id="view-replybtn" 
										type="button" class="btn btn-info find" ><span class="glyphicon glyphicon-pencil"></span> 답글
								</button>							
							</div>							
<%
			}
		}
%>
						</div>				

<!-- back to list button-->
						<div class="writebtn-containter" style="margin-bottom: 2%">
							<div class="writebtn-wrapper">
								<button type="button" class="btn btn-info" onclick="javascript:listArticle('<%=pg%>')">목록</button>
							</div>
						</div>
						
						
<!-- memo! -->
						
						<div class="feedback container" id="comment">
							<div class="fbHeader col-md-19">
								<h2>댓글 <em>갯수</em></h2>
									<div class="clear">
									</div>
							</div>
<!-- memolist -->												
							<div id="memolist" class="col-md-12" style="margin: 2%;">
								<ul class="fbList" id="memotbody">
<!-- memo 뿌리는 곳 -->
								</ul>
							</div>
							<div class="refreshArea">
								<span class="btn">
									<button id="refresh-memo" class="btn btn-default">
									<span class="glyphicon glyphicon-refresh"></span> 댓글 새로고침
									</button>
								</span>
							</div>
							<form class="write_comment" id="write_comment" onsubmit="return procFilter(this, insert_comment)" action="./" method="post">
								<input name="error_return_url" type="hidden" value="/free/190678448">
								<input name="act" type="hidden" value="">
								<input name="vid" type="hidden" value="">
								<input name="mid" type="hidden" value="free">
								<input name="document_srl" type="hidden" value="190678448">
								<input name="comment_srl" type="hidden" value="">
								<input name="content" type="hidden" value="">
								
								<div class="xeTextEditor">
									<input id="htm_8" type="hidden" value="n">
									<textarea class="iTextArea" id="mcontent" style="width: 100%; height: 100px;" rows="8" cols="42"></textarea>
								</div>
								<div class="write_author">
								</div>
							</form>
<!-- memo write btn -->			
						<%if (memberDto != null) {%>
							<div class="col-md-12" style="margin: 2%;">
								<div class="" style="text-align:right;">
									<div style="display:inline-block;">
										<Button id="memobtn" class="regBtn btn btn-default" type="button" value="" >
											<span class="glyphicon glyphicon-ok"></span> 댓글
										</Button>
									</div>
								</div>
							</div>
						<%} %>							
						</div>
	
	
	
	
<!-- back to list button-->
						<div class="writebtn-containter">
							<div class="writebtn-wrapper">
								<button type="button" class="btn btn-info" onclick="javascript:listArticle('<%=pg%>')">목록</button>
							</div>
						</div>
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
<!-- modify modal -->
			<div class="modal fade" id="mymodal" role="dialog">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
<!-- modify modal header -->
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">글수정</h4>
						</div>
<!-- modify modal body -->
						<div class="modal-body">
							<form name="viewform" method="post" action="" onsubmit="return false;">
								<input type="hidden" name="act" value=""/>
								<input type="hidden" name="bcode" value=""/>
								<input type="hidden" name="pg" value=""/>
								<input type="hidden" name="key" value=""/>
								<input type="hidden" name="word" value=""/>
								<input type="hidden" name="seq" value=""/>
								<input type="hidden" name="content" value=""/>
								<input type="hidden" name="pseq" value="<%=freeboardDto.getSeq()%>">
								
								
								<div class="form-group">
									<label for="usr">제목</label>
									<input type="text" class="form-control" id="subject" name="subject" />
								</div>						
								<label for="usr">내용</label>
								<div id="summernote"></div>
<!-- modify modal footer -->
								<div class="modal-footer">
									<button onclick="javascript:modifyArticle('<%=freeboardDto.getSeq()%>');" style="display:none;"
										type="button" class="btn btn-default" id="modal-modifybtn" name="modifybtn" value="">수정</button>
									
									<button onclick="javascript:replyArticle('<%=freeboardDto.getSeq()%>');" style="display:none;"
										type="button" class="btn btn-default" id="modal-replybtn" name="modifybtn" value="">답글</button>							
									
									<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>