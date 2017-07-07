package com.kitri.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.member.service.MemberServiceImpl;

public class IdcheckAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = "/index.jsp";
		String sid = request.getParameter("id");
//		System.out.println(request.getParameter("id"));
//		System.out.println(request.getParameter("signid"));
		int count = MemberServiceImpl.getMemberService().idCheck(sid);
		
		path = "/member/idcheckresult.jsp?sid=" + sid + "&count=" + count;
//		System.out.println(sid + ">>>>>" + count);
		return path;
	}

}
