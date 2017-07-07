package com.kitri.map.service;

import java.util.List;

import com.kitri.map.model.MapDto;


public interface MapService {
	int writeMapArticle(MapDto mapDto);
	MapDto getMapArticle(int seq);
	List<MapDto> listMapArticle(int bcode, int pg, String key, String word);

	int modifyArticle(MapDto mapDto);
	int deleteArticle(int seq);

}
