package com.kitri.maproute.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.admin.model.PathDto;
import com.kitri.factory.MapActionFactory;
import com.kitri.factory.MapRouteActionFactory;
import com.kitri.util.Encoding;
import com.kitri.util.NumberCheck;
import com.kitri.util.PageMove;


@WebServlet("/maproute")
public class MapRouteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		      

		      if("mvroutehot".equals(act)){

		    	String contentpath=MapRouteActionFactory.getMapRouteListAction().execute(request, response);
		        pathDto.setContentPath(contentpath);
		        pathDto.setTitleHead("추천경로");
		        
		        request.setAttribute("pathInfo", pathDto);

		         PageMove.forward(pathDto.getPath(), request, response);
		      } else if("".equals(act)){
		      }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
