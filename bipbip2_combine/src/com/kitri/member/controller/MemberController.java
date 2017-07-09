package com.kitri.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.admin.model.PathDto;
import com.kitri.factory.MemberActionFactory;
import com.kitri.member.model.MemberDto;
import com.kitri.util.Constant;
import com.kitri.util.Encoding;
import com.kitri.util.NumberCheck;
import com.kitri.util.PageMove;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		<basic path>
		String root = request.getContextPath(); 
		String act = request.getParameter("act");
		String path = "/index.jsp";

		
		PathDto pathDto = new PathDto();
		pathDto.setContentPath("/main/main.jsp");
		if (act != null || !act.isEmpty()) {
			pathDto.setPath("/default.jsp");
		} else {
			pathDto.setPath("/index.jsp");
		}
//////////////////////////전체 총괄해서 합쳐주는 기본경로/////////////////////////////////
		
		
//		<login active logic>
		if ("mvlogin".equals(act)) {
			pathDto.setContentPath("/member/login.jsp");
			pathDto.setTitleHead("로그인");

			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);

			////// 로그인 페이지로 이동 ///////
			
		} else if ("mvjoin".equals(act)) {
			pathDto.setContentPath("/member/join.jsp");
			pathDto.setTitleHead("회원가입");

			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);

			////// 회원가입 페이지로 이동 //////
			
		} else if ("register".equals(act)) {
			path = MemberActionFactory.getRegisterAction().execute(request, response);
			// 회원가입 로직 구현을 위한 팩토리 연결
			
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(path, request, response); // 로직에 관해 담겨있는 정보를 컨트롤러에서 각각의 jsp 파일로 전달
			
			//// 회원가입 로직 구현 //////
			
		} else if ("login".equals(act)) {
			path = MemberActionFactory.getLoginAction().execute(request, response);
			// 로그인 로직구현을 위한 팩토리 연결
			
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(path, request, response); // 로직에 관해 담겨있는 정보를 컨트롤러에서 각각의 jsp 파일로 전달
			
			// 로그인 로직 구현 // 
			
		} else if ("logout".equals(act)) {
			HttpSession session = request.getSession();
			session.invalidate(); // session 값을 지워줌. 
			
			response.sendRedirect(root + path); // 로직에 관해 담겨있는 정보를 컨트롤러에서 각각의 jsp 파일로 전달
			
		} else if ("modify".equals(act)) {
			path = MemberActionFactory.getModifyAction().execute(request, response);
			
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(path, request, response);


		} else if ("mvmodify".equals(act)) {
			pathDto.setContentPath("/member/modify.jsp");
			pathDto.setTitleHead("회원정보수정");
			
			path = MemberActionFactory.getMvmodifyAction().execute(request, response); 
			
			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response); // 로직에 관해 담겨있는 정보를 컨트롤러에서 각각의 jsp 파일로 전달

		} else if ("memberdelete".equals(act)) {
			path = MemberActionFactory.getDeleteAction().execute(request, response);
			PageMove.forward(path, request, response);

		} else if ("idsearch".equals(act)) {
			path = MemberActionFactory.getIdcheckAction().execute(request, response);
			PageMove.forward(path, request, response);
		
		} else if ("main".equals(act)) {
			pathDto.setContentPath("/main/exmain.jsp");
			pathDto.setHeadPath("/main/exmainhead.jsp");
			pathDto.setTitleHead("자전거여행");

			request.setAttribute("pathInfo", pathDto);
			PageMove.forward(pathDto.getPath(), request, response);

		} else if ("contact".equals(act)) {
			pathDto.setContentPath("/member/contact.jsp");
			pathDto.setTitleHead("문의사항");
			
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