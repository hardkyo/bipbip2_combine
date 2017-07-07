package com.kitri.gallery.service;

import com.kitri.gallery.service.GalleryService;
import com.kitri.gallery.service.GalleryServiceImpl;

import com.kitri.util.Constant;

import java.util.*;

import com.kitri.gallery.dao.GalleryCommonDaoImpl;
import com.kitri.gallery.dao.GalleryDaoImpl;
import com.kitri.gallery.model.GalleryDto;

public class GalleryServiceImpl implements GalleryService {
	
	private static GalleryService galleryService;

	static {
		galleryService = new GalleryServiceImpl();
	}

	private GalleryServiceImpl() {
	}

	public static GalleryService getGalleryService() {
		return galleryService;
	}

	@Override
	public int writeArticle(GalleryDto galleryDto) {
		return GalleryDaoImpl.getGalleryDao().writeArticle(galleryDto);
	}

	@Override
	public GalleryDto getArticle(int seq) {
		GalleryDto dto = null;
		GalleryCommonDaoImpl.getCommonDao().updateHit(seq);
		
		return  GalleryDaoImpl.getGalleryDao().getArticle(seq);
	}

	@Override
	public List<GalleryDto> listArticle(int bcode, int pg, String key, String word) {
		
		
		
		Map<String, String> map = new HashMap<String, String>();
		int end = pg * Constant.PICTURE_SIZE;
		int start = end - Constant.PICTURE_SIZE;
		map.put("pg", pg + "");
		map.put("bcode", bcode + "");
		map.put("key", key);
		map.put("word", word);
		map.put("start", start + "");
		map.put("end", end + "");
		return GalleryDaoImpl.getGalleryDao().listArticle(map);
	}


	@Override
	public int modifyArticle(GalleryDto galleryDto) {

		return GalleryDaoImpl.getGalleryDao().modifyArticle(galleryDto);
	}

	@Override
	public int deleteArticle(int seq) {

		return GalleryDaoImpl.getGalleryDao().deleteArticle(seq);
	}

}
