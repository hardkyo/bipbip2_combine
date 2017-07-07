package com.kitri.freeboard.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.kitri.action.Action;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.freeboard.service.FreeBoardServiceImpl;
import com.kitri.util.Encoding;
import com.kitri.util.NumberCheck;

public class FreeBoardDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "";
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		int count = FreeBoardServiceImpl.getService().deleteArticle(seq);

		path = "/board/freeboard.jsp";
		
		if (count == 0) {
			JOptionPane.showMessageDialog(null, "삭제실패");
		} else {
			JOptionPane.showMessageDialog(null, "삭제성공");
		}
		return path;
	}

}
