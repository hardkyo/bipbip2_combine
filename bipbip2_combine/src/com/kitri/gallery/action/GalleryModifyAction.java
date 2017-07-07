package com.kitri.gallery.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.admin.model.PathDto;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.gallery.model.GalleryDto;
import com.kitri.gallery.service.GalleryServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.PageMove;

public class GalleryModifyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			return null;
	}
}