package com.kitri.freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.freeboard.service.CommonServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.NumberCheck;

public class FreeBoardUpAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String root = request.getContextPath();
		
		HttpSession session = request.getSession();
		MemberDto loginInfo = (MemberDto) session.getAttribute("loginInfo");
		
		int seq = NumberCheck.nullToZero(request.getParameter("seq"));

		String path = "";
		path = "/board/upinfo.jsp?up=";
				
		int up = CommonServiceImpl.getCommonService().plusUp(seq);
		path += up + "";
		return path;
	}

}
