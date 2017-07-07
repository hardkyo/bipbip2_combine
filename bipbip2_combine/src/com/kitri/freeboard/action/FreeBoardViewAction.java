package com.kitri.freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.action.Action;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.freeboard.service.CommonServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.NumberCheck;

public class FreeBoardViewAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		path = "/board/freeboardview.jsp";
		
		HttpSession session = request.getSession();
		MemberDto loginInfo = (MemberDto) session.getAttribute("loginInfo");
		
		int seq = NumberCheck.nullToZero(request.getParameter("seq"));
		
/*		if (loginInfo != null && loginInfo.getCheckhit() == 0) {
			CommonServiceImpl.getCommonService().callCommonDaoUpdateHit(seq, loginInfo);
			MemberDto newLoginInfo = CommonServiceImpl.getCommonService().updateLoginInfo(loginInfo);
			session.setAttribute("loginInfo", newLoginInfo);
		}*/
		
		if (seq !=0) {
			CommonServiceImpl.getCommonService().updateHit(seq);
			FreeBoardDto dto = CommonServiceImpl.getCommonService().getArticle(seq);
			request.setAttribute("article", dto);
		}

		return path;
	}

}
