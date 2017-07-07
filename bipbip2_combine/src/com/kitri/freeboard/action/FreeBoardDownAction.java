package com.kitri.freeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.action.Action;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.freeboard.service.CommonServiceImpl;
import com.kitri.util.NumberCheck;

public class FreeBoardDownAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		path = "/board/downinfo.jsp?down=";
		int seq = NumberCheck.nullToZero(request.getParameter("seq"));
		int down = CommonServiceImpl.getCommonService().plusdown(seq);
		path += down+"";
		return path;
	}

}
