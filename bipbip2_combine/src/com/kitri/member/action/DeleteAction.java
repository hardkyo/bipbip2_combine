package com.kitri.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.action.Action;
import com.kitri.member.model.MemberDto;
import com.kitri.member.service.MemberServiceImpl;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = "/index.jsp";
		
		MemberDto memberDto = (MemberDto) request.getSession().getAttribute("loginInfo");
		String id = memberDto.getId();
		
		int cnt = MemberServiceImpl.getMemberService().delete(id);
		if (cnt != 0) {
			path =  "/index.jsp";
			HttpSession session = request.getSession();
			session.invalidate();
			
		}else {
			path = "/member/login.jsp";
		}
		
		return path;
	}

}
