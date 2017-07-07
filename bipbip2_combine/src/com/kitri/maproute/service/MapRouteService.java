package com.kitri.maproute.service;

import java.util.List;

import com.kitri.map.model.MapDto;

public interface MapRouteService {
	
	List<MapDto> listMapArtice(int bcode,int pg,String key, String word);

}
