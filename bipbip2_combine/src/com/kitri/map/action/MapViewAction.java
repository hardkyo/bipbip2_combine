package com.kitri.map.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.action.Action;
import com.kitri.admin.model.PathDto;
import com.kitri.map.model.MapDto;
import com.kitri.map.service.MapServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.PageMove;

public class MapViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 멤버 체크부터 하자
		PathDto pathDto = new PathDto();
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
		String path = "";
		if (memberDto == null) {
			path = "/map/maploginerror.jsp";
		} else {
			// session에서 MemberDto를 불러온다.
			// memberDto 확인

			path = "/map/mapview.jsp";
			String act = "view";

			MapDto mapDto = new MapDto();
			mapDto.setId(memberDto.getId());
			mapDto.setName(memberDto.getName());
			mapDto.setEmail(memberDto.getEmail());
			mapDto.setLoc1X(Double.parseDouble(request.getParameter("loc1X")));
			mapDto.setLoc1Y(Double.parseDouble(request.getParameter("loc1Y")));
			mapDto.setLoc2X(Double.parseDouble(request.getParameter("loc2X")));
			mapDto.setLoc2Y(Double.parseDouble(request.getParameter("loc2Y")));
			mapDto.setSec1X(Double.parseDouble(request.getParameter("sec1X")));
			mapDto.setSec1Y(Double.parseDouble(request.getParameter("sec2Y")));
			mapDto.setSec2X(Double.parseDouble(request.getParameter("sec2X")));
			mapDto.setSec2Y(Double.parseDouble(request.getParameter("sec2Y")));
			mapDto.setSec3X(Double.parseDouble(request.getParameter("sec3X")));
			mapDto.setSec3Y(Double.parseDouble(request.getParameter("sec3Y")));
			mapDto.setLoc1(request.getParameter("loc1"));
			mapDto.setLoc2(request.getParameter("loc2"));
			mapDto.setSec1(request.getParameter("sec1"));
			mapDto.setSec2(request.getParameter("sec2"));
			mapDto.setSec3(request.getParameter("sec3"));
			mapDto.setMemo(request.getParameter("memo"));

			request.setAttribute("mapDto", mapDto);

			pathDto.setPath("/default.jsp");
			pathDto.setTitleHead("경로 확인");
			pathDto.setContentPath("/map/mapview.jsp");

			request.setAttribute("pathInfo", pathDto);
			// PageMove.forward(pathDto.getPath(), request, response);
		}
		return path;
	}

}
