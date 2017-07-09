package com.kitri.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.member.model.MemberDto;
import com.kitri.member.service.MemberServiceImpl;

public class MvmodifyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = "/index.jsp";
		MemberDto memberDto = (MemberDto) request.getSession().getAttribute("loginInfo");
		
		if (memberDto != null) {
			String id = memberDto.getId();
			MemberDto memberDto2 = MemberServiceImpl.getMemberService().getMember(id);
			if (memberDto2 != null) {
				
				path = "/member/modify.jsp";
				request.setAttribute("modify", memberDto2);
			}else {
				path= "/member/login.jsp";
			}
		}
		return path;
	}
}
