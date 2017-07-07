package com.kitri.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.admin.model.PathDto;
import com.kitri.util.*;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String root = request.getContextPath();
		String act = request.getParameter("act");

		PathDto pathDto = new PathDto();
		pathDto.setContentPath("/main/main.jsp");
		if (act != null || !act.isEmpty())
			pathDto.setPath("/default.jsp");
		else
			pathDto.setPath("/index.jsp");

		if ("main".equals(act)) {
			pathDto.setContentPath("/main/exmain.jsp");
			pathDto.setHeadPath("/main/exmainhead.jsp");
			pathDto.setTitleHead("자전거여행");

			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);
		} else if ("mvroutehot".equals(act)) {
			System.out.println("mvroutehot!!!!");
			pathDto.setContentPath("/routehot/routehot.jsp");
			pathDto.setTitleHead("추천경로");

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
