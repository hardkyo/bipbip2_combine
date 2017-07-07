	package com.kitri.map.dao;

import java.util.List;
import java.util.Map;

import com.kitri.map.model.MapDto;

public interface MapDao {	
	int writeMapArticle(MapDto mapDto);
	MapDto getMapArticle(int seq);
	List<MapDto> listMapArticle(Map<String, String> map);

	int modifyArticle(MapDto mapDto);
	int deleteArticle(int seq);

}
