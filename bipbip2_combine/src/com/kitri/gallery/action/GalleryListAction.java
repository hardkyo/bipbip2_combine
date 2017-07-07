package com.kitri.gallery.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.freeboard.service.CommonServiceImpl;
import com.kitri.gallery.model.GalleryDto;
import com.kitri.gallery.service.GalleryServiceImpl;
import com.kitri.util.*;

public class GalleryListAction implements Action {

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int bcode = 4;
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key = Encoding.nullToBlank(request.getParameter("key"));
		String word = Encoding.isoToEuc(request.getParameter("word"));
		
		List<GalleryDto> list = GalleryServiceImpl.getGalleryService().listArticle(bcode, pg, key, word);
		request.setAttribute("photoList", list);
		
		PageNavigation pageNavigation = CommonServiceImpl.getCommonService().makePageNavigation(bcode, pg, key, word);
		pageNavigation.setRoot(request.getContextPath());
		pageNavigation.setNavigator();
		request.setAttribute("navigator", pageNavigation);
		
		return "/board/gallery.jsp";
	}

}
