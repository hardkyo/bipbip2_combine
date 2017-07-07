package com.kitri.maproute.dao;

import java.util.List;

import com.kitri.map.model.MapDto;

public interface MapRouteDao {
	List<MapDto> listMapArticle(int bcode,int pg,String key, String word);
}
