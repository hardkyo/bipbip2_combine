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
		
		Cookie test = new Cookie("test", loginInfo.getId());
		test.setPath(root);
		test.setMaxAge(60*60*24);
		response.addCookie(test);
		
		String key = "";
		String value = "";
		Cookie[] cookies = request.getCookies(); 
		if (cookies != null && cookies.length >= 1) { 
			for (Cookie c : cookies) { 
				key = c.getName(); 
				value = c.getValue(); 
				System.out.println(key + " : " + value + "<br>"); 
			} 
		}

		String path = "";
		path = "/board/upinfo.jsp?up=";
		
		
		int up = CommonServiceImpl.getCommonService().plusUp(seq);
		path += up + "";
		return path;
	}

}
