package com.kitri.map.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.admin.model.PathDto;
import com.kitri.freeboard.service.CommonServiceImpl;
import com.kitri.freeboard.service.FreeBoardServiceImpl;
import com.kitri.map.dao.MapDaoImpl;
import com.kitri.map.model.MapDto;
import com.kitri.map.service.MapService;
import com.kitri.map.service.MapServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.Encoding;
import com.kitri.util.NumberCheck;
import com.kitri.util.PageMove;

public class MapWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO 멤버 체크부터 하자
		PathDto pathDto = new PathDto();
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
		//session에서 MemberDto를 불러온다.
		if(memberDto==null){
			String root = request.getContextPath();
			String url = root+"/map/maploginerror.jsp";
			PageMove.redirect(url, request, response);
		}
		//memberDto 확인

		String root = request.getContextPath();
		String path = "/map/mapview.jsp";
		String act = "view";
		int seq = CommonServiceImpl.getCommonService().getNextSeq();

		MapDto mapDto = new MapDto();
		mapDto.setSeq(seq);
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
		mapDto.setBcode(NumberCheck.nullToOne(request.getParameter("bcode")));
		mapDto.setSubject(request.getParameter("subject"));
		
	


		int check = MapServiceImpl.getMapService().writeMapArticle(mapDto);
		// TODO 여기서 insert 한다.
		if(check!=0){
			System.out.println(check + " of data saved.");

			request.setAttribute("mapDto", mapDto);
			
			pathDto.setPath("/default.jsp");
			pathDto.setTitleHead("경로 확인");
			pathDto.setContentPath("/map/mapcall.jsp");
			
			request.setAttribute("pathInfo", pathDto);
	        PageMove.forward(pathDto.getPath(), request, response);
			
		} else {
			System.out.println("Data insert has failed");
			String url = root+"/admin?act=main";
			
			
			
			PageMove.redirect(url, request, response);
		}
		 //<< 뷰 페이지로 정보를 보내*/

		return path;
	}

}
