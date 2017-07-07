package com.kitri.map.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.action.Action;
import com.kitri.freeboard.dao.CommonDaoImpl;
import com.kitri.map.model.MapDto;
import com.kitri.map.service.MapServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.NumberCheck;
import com.kitri.util.PageMove;

public class MapCallAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto logininfo = (MemberDto) session.getAttribute("loginInfo");
		String path = "";
		if (logininfo == null) {
			path =  "/map/maploginerror.jsp";
			//response.sendRedirect(url);
		} else {

			// TODO call 페이지 서비스 호출
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));

			if (seq != 0) {
				MapDto mapDto = MapServiceImpl.getMapService().getMapArticle(seq);
				CommonDaoImpl.getCommonDao().updateHit(seq, logininfo);
				path = "/map/mapcall.jsp";
				request.setAttribute("mapcall", mapDto);
			}
		}
		return path;
	}

}
