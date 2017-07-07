package com.kitri.maproute.service;

import java.util.*;

import com.kitri.map.model.MapDto;
import com.kitri.maproute.dao.MapRouteDaoImpl;
import com.kitri.util.Constant;

public class MapRouteServiceImpl implements MapRouteService {
	private static MapRouteService mapRouteService;
	
	static{
		mapRouteService = new MapRouteServiceImpl();
	}
	private MapRouteServiceImpl(){}
	
	

	public static MapRouteService getMapRouteService() {
		return mapRouteService;
	}



	@Override
	public List<MapDto> listMapArtice(int bcode, int pg, String key, String word) {
		int end = pg*Constant.LIST_SIZE;
		int start = end-Constant.LIST_SIZE;
		Map<String, String> map = new HashMap<String, String>();
		map.put("bcode", bcode+"");
		map.put("key", key);
		map.put("word", word);
		map.put("start", start+"");
		map.put("end", end+"");
		System.out.println(MapRouteDaoImpl.getMapRouteDao().listMapArticle(bcode, pg, key, word).get(0).getMemo()+">>>>");
		return MapRouteDaoImpl.getMapRouteDao().listMapArticle(bcode, pg, key, word);
	}

}
