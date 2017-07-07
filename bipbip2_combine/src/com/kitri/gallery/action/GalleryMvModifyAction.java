package com.kitri.gallery.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kitri.action.Action;
import com.kitri.gallery.model.GalleryDto;
import com.kitri.gallery.service.GalleryServiceImpl;
import com.kitri.util.NumberCheck;

public class GalleryMvModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int seq = NumberCheck.nullToZero(request.getParameter("seq"));
		
		System.out.println(request.getParameter("subject") );
		
		if(seq != 0){
			GalleryDto galleryDto = GalleryServiceImpl.getGalleryService().getArticle(seq);
			request.setAttribute("article", galleryDto);
		}
		return  "/board/gallerymodify.jsp";
	}

}
