package com.kitri.gallery.service;
import java.util.List;
import com.kitri.gallery.model.GalleryDto;

public interface GalleryService {
	
		int writeArticle(GalleryDto galleryDto);
		GalleryDto getArticle(int seq);
		List<GalleryDto> listArticle(int bcode, int pg, String key, String word);
		int modifyArticle(GalleryDto galleryDto);
		int deleteArticle(int seq);
		
	}


