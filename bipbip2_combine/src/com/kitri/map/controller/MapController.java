package com.kitri.map.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.admin.model.PathDto;
import com.kitri.factory.MapActionFactory;
import com.kitri.util.*;

@WebServlet("/map")
public class MapController extends HttpServlet {
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
		pathDto.setContentPath("/main/main.jsp");

		if (act != null || !act.isEmpty())
			pathDto.setPath("/default.jsp");
		else
			pathDto.setPath("/index.jsp");

		if ("mvshowmap".equals(act)) {

			pathDto.setContentPath("/map/mapShow.jsp");
			pathDto.setTitleHead("지도");

			request.setAttribute("pathInfo", pathDto);

			PageMove.forward(pathDto.getPath(), request, response);
		} else if ("mapwrite".equals(act)) {
			String contentpath = MapActionFactory.getMapWriteAction().execute(request, response);

		} else if ("mapview".equals(act)) {
			//System.out.println("order recieved:::view");
			
			String contentpath = MapActionFactory.getMapViewAction().execute(request, response);
			pathDto.setContentPath(contentpath);
			pathDto.setTitleHead("경로 상세 화면");

			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
		} else if ("maplist".equals(act)) {
			//System.out.println("order recieved:::list");

			String contentpath = MapActionFactory.getMapListAction().execute(request, response);
			pathDto.setContentPath(contentpath);
			pathDto.setTitleHead("경로 목록 화면");

			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);

		} else if ("mapdelete".equals(act)) {
			//System.out.println("order recieved:::delete");
			String check = MapActionFactory.getMapDeleteAction().execute(request, response);
			String contentpath = MapActionFactory.getMapListAction().execute(request, response);
			pathDto.setContentPath(contentpath);
			pathDto.setTitleHead("경로 목록 화면");  

			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
			
		} else if ("mapcall".equals(act)) {
			//System.out.println("order recieved:::call");
			String contentpath = MapActionFactory.getMapCallAction().execute(request, response);
			pathDto.setContentPath(contentpath);
			pathDto.setTitleHead("경로 상세 화면");
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
		} else if ("".equals(act)) {
			pathDto.setContentPath("/default.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		doGet(request, response);
	}

}