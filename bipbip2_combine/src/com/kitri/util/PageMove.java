package com.kitri.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageMove {
	// 페이지이동방법 두가지 1. 리다이렉트 2. 포워드
	public static void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.sendRedirect(request.getContextPath()+url);
	}
	public static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher=request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}
}
