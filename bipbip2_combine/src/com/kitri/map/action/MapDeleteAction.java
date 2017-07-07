package com.kitri.map.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitri.action.Action;
import com.kitri.admin.model.PathDto;
import com.kitri.map.service.MapServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.*;

public class MapDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO map 경로 삭제 
		int seq = NumberCheck.nullToZero(request.getParameter("seq"));
		int check = MapServiceImpl.getMapService().deleteArticle(seq);
		return check+"";
	}

}
