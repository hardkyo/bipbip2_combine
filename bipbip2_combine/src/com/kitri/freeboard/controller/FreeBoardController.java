package com.kitri.freeboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.admin.model.PathDto;
import com.kitri.factory.FreeBoardActionFactory;
import com.kitri.util.*;

@WebServlet("/freeboard")
public class FreeBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String root = request.getContextPath();
		String act = request.getParameter("act");

		int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key = Encoding.nullToBlank(request.getParameter("key"));
		String word = request.getParameter("word");

		if (request.getMethod().equals("GET"))
			word = Encoding.isoToEuc(word);

		word = Encoding.urlFormat(word);
		String queryStr = "?bcode=" + bcode + "&pg=" + pg + "&key=" + key + "&word=" + word;
		//System.out.println("RC >>> " + queryStr);

		PathDto pathDto = new PathDto();
		pathDto.setContentPath("/main/exmain.jsp");
		
		if (act != null || !act.isEmpty())
			pathDto.setPath("/default.jsp");
		else
			pathDto.setPath("/index.jsp");
		
		if ("freeboard".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottom.jsp");
			pathDto.setTitleHead("자유게시판");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardListAction().execute(request, response);
			pathDto.setContentPath(contentPath);
		
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
			
		} else if ("freewrite".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottom.jsp");
			pathDto.setTitleHead("자유게시판");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardWriteAction().execute(request, response);
			pathDto.setContentPath(contentPath);
			
			//삭제한 후 리스트를 뽑아 오기 위해 list를 request에 저장 할 함수를 실행한다.
			FreeBoardActionFactory.getFreeBoardListAction().execute(request, response);
			
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
			
		}  else if ("list".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottom.jsp");
			pathDto.setTitleHead("자유게시판");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardListAction().execute(request, response);
			contentPath += queryStr;
			pathDto.setContentPath(contentPath);
			
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
			
		} else if ("view".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottomview.jsp");
			pathDto.setTitleHead("자유게시판-글보기");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardViewAction().execute(request, response);
			contentPath += queryStr;
			pathDto.setContentPath(contentPath);
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
			
		} else if ("delete".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottom.jsp");
			pathDto.setTitleHead("자유게시판");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardDeleteAction().execute(request, response);
			contentPath += queryStr;
			
			//삭제한 후 리스트를 뽑아 오기 위해 list를 request에 저장 할 함수를 실행한다.
			FreeBoardActionFactory.getFreeBoardListAction().execute(request, response);
			
			pathDto.setContentPath(contentPath);
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
			
		} else if ("mvmodify".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottom.jsp");
			pathDto.setTitleHead("자유게시판");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardMoveModifyAction().execute(request, response);
			contentPath += queryStr;
			PageMove.forward(contentPath, request, response);
			
		} else if ("modify".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottom.jsp");
			pathDto.setTitleHead("자유게시판");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardModifyAction().execute(request, response);
			contentPath += queryStr;
			
			//삭제한 후 리스트를 뽑아 오기 위해 list를 request에 저장 할 함수를 실행한다.
			FreeBoardActionFactory.getFreeBoardListAction().execute(request, response);
			
			pathDto.setContentPath(contentPath);
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
			
		} else if ("mvreply".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottom.jsp");
			pathDto.setTitleHead("자유게시판");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardMoveReplyAction().execute(request, response);
			contentPath += queryStr;
			PageMove.forward(contentPath, request, response);
			
		} else if ("reply".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottom.jsp");
			pathDto.setTitleHead("자유게시판");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardReplyAction().execute(request, response);
			contentPath += queryStr;
			
			FreeBoardActionFactory.getFreeBoardListAction().execute(request, response);
			
			pathDto.setContentPath(contentPath);
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
		} else if ("hotlist".equals(act)) {
			pathDto.setHeadPath("/board/freeboardhead.jsp");
			pathDto.setBottomPath("/board/freeboardbottom.jsp");
			pathDto.setTitleHead("자유게시판");
			
			String contentPath = FreeBoardActionFactory.getFreeBoardHotListAction().execute(request, response);
			contentPath += queryStr;
			
			//삭제한 후 리스트를 뽑아 오기 위해 list를 request에 저장 할 함수를 실행한다.
			FreeBoardActionFactory.getFreeBoardListAction().execute(request, response);
			
			pathDto.setContentPath(contentPath);
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(Constant.DEFAULT_CHAR_SET);
		doGet(request, response);
	}

}
