package com.kitri.gallery.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.kitri.action.Action;
import com.kitri.gallery.dao.GalleryDaoImpl;
import com.kitri.gallery.model.GalleryDto;
import com.kitri.gallery.service.GalleryServiceImpl;
import com.kitri.member.model.MemberDto;
import com.kitri.util.Encoding;
import com.kitri.util.NumberCheck;

public class GalleryDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("loginInfo");
		String path = "";
		if (memberDto != null) {
			int seq = NumberCheck.nullToZero(request.getParameter("seq"));
			if (seq != 0) {
				GalleryDto galleryDto = GalleryServiceImpl.getGalleryService().getArticle(seq);
				request.setAttribute("article", galleryDto);

				int cnt = GalleryServiceImpl.getGalleryService().deleteArticle(seq);
				// System.out.println("cnt : " + cnt);
				if (cnt != 0) {
					request.setAttribute("seq", seq + "");
					path = "/board/gallerydeleteok.jsp";
				} else {
					path = "/board/gallerydeletefail.jsp";
				}
			}
		}

		return path;
	}

}