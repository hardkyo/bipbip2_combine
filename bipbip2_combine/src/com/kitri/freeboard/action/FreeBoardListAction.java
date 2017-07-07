package com.kitri.freeboard.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.freeboard.service.CommonServiceImpl;
import com.kitri.freeboard.service.FreeBoardServiceImpl;
import com.kitri.util.*;

public class FreeBoardListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/board/freeboard.jsp";
		
		int bcode = NumberCheck.nullToZero(request.getParameter("bcode"));
		int pg = NumberCheck.nullToOne(request.getParameter("pg"));
		String key = Encoding.nullToBlank(request.getParameter("key"));
		String word = Encoding.isoToEuc(request.getParameter("word"));
		
		List<FreeBoardDto> list = 
				FreeBoardServiceImpl.getService().listArticle(bcode, pg, key, word);
		request.setAttribute("freeList", list);
		
		PageNavigation pageNavigation = CommonServiceImpl.getCommonService().makePageNavigation(bcode, pg, key, word);
		pageNavigation.setRoot(request.getContextPath());
		pageNavigation.setNavigator();
		request.setAttribute("navigator", pageNavigation);
		return path;
	}

}
