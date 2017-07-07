package com.kitri.map.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.admin.model.PathDto;
import com.kitri.map.model.MapDto;
import com.kitri.map.service.MapServiceImpl;
import com.kitri.util.*;

public class MapListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 경로 목록을 불러 봅시다.
		int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key=Encoding.nullToBlank(request.getParameter("key"));
		String word = request.getParameter("word");
		PathDto pathDto = new PathDto();
		String path = "/map/maplist.jsp";
				
		List<MapDto> list = MapServiceImpl.getMapService().listMapArticle(bcode, pg, key, word);
		request.setAttribute("articleList", list);
		
//		PageNavigation pageNavigation = CommonServiceImpl.getCommonservice().makePageNavigation(bcode, pg, key, word, BoardConstance.LIST_SIZE);
//		pageNavigation.setRoot(request.getContextPath());
//		pageNavigation.setNavigator();
//		request.setAttribute("navigator", pageNavigation);
		return path;
	}

}
