package com.kitri.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.action.Action;
import com.kitri.member.model.MemberDto;
import com.kitri.member.service.MemberServiceImpl;

public class LoginAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = "/index.jsp";

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDto memberDto = MemberServiceImpl.getMemberService().login(id, pass);
//		System.out.println(id +   ">>>>"  + pass);

		if (memberDto != null) {
			//////////////////// session ///////////////////////
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", memberDto);		
			path = "/index.jsp";
		} else {		
			path = "/member/loginfail.jsp";
		}

		return path;
	}

}
