package com.kitri.maproute.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.admin.model.PathDto;
import com.kitri.map.model.MapDto;
import com.kitri.map.service.MapServiceImpl;
import com.kitri.maproute.service.MapRouteServiceImpl;
import com.kitri.util.Encoding;
import com.kitri.util.NumberCheck;

public class MapRouteListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key=Encoding.nullToBlank(request.getParameter("key"));
		String word = request.getParameter("word");
		PathDto pathDto = new PathDto();
		String path = "/routehot/routehot.jsp";
				
		List<MapDto> list = MapRouteServiceImpl.getMapRouteService().listMapArtice(bcode, pg, key, word);
		request.setAttribute("list", list);
		
		pathDto.setPath("/default.jsp");
		pathDto.setTitleHead("추천경로");
		pathDto.setContentPath("/routehot/routehot.jsp");
		
		request.setAttribute("pathInfo", pathDto);
		
//		PageNavigation pageNavigation = CommonServiceImpl.getCommonservice().makePageNavigation(bcode, pg, key, word, BoardConstance.LIST_SIZE);
//		pageNavigation.setRoot(request.getContextPath());
//		pageNavigation.setNavigator();
//		request.setAttribute("navigator", pageNavigation);
		return path;
	}

}
