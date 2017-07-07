package com.kitri.gallery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.admin.model.PathDto;
import com.kitri.factory.FreeBoardActionFactory;
import com.kitri.factory.GalleryActionFactory;
import com.kitri.gallery.model.GalleryDto;
import com.kitri.util.*;

@WebServlet("/gallery")
public class GalleryController extends HttpServlet {
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
		System.out.println("RC >>> " + queryStr);

		PathDto pathDto = new PathDto();
		pathDto.setContentPath("/main/main.jsp");
		if (act != null || !act.isEmpty())
			pathDto.setPath("/default.jsp");
		else
			pathDto.setPath("/index.jsp");
		
		if ("mvgallerylist".equals(act)) {
			String path = GalleryActionFactory.getGalleryListAction().execute(request, response);
			pathDto.setContentPath(path);
			pathDto.setTitleHead("°¶·¯¸®");
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
		}else if("view".equals(act)){
			String path = GalleryActionFactory.getGalleryViewAction().execute(request, response);
			pathDto.setContentPath(path);
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);	
		}else if("delete".equals(act)){
			String path = GalleryActionFactory.getGalleryDeleteAction().execute(request, response);
			pathDto.setContentPath(path);
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
								
		}else if("mvmodify".equals(act)){
			String path = GalleryActionFactory.galleryMvModifyAction().execute(request, response);
			pathDto.setContentPath(path);
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
		}else if("modify".equals(act)){
			String path = GalleryActionFactory.getGalleryModifyAction().execute(request, response);
			pathDto.setContentPath(path);
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
