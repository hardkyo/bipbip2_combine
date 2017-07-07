package com.kitri.gallery.dao;

import java.util.List;
import java.util.Map;

import com.kitri.gallery.model.GalleryDto;

public interface GalleryDao {
	
		int writeArticle(GalleryDto galleryDto);
		GalleryDto getArticle(int seq);
		List<GalleryDto> listArticle(Map<String, String> map);		
		int modifyArticle(GalleryDto galleryDto);
		int deleteArticle(int seq);
		
	}


