package com.kitri.map.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kitri.map.dao.MapDaoImpl;
import com.kitri.map.model.MapDto;
import com.kitri.util.BoardConstance;

public class MapServiceImpl implements MapService {
	
	private static MapService mapService;
	
	public static MapService getMapService() {
		return mapService;
	}

	static {
		mapService = new MapServiceImpl();
	}

	@Override
	public int writeMapArticle(MapDto mapDto) {

		
		return MapDaoImpl.getMapDao().writeMapArticle(mapDto);
	}

	@Override
	public MapDto getMapArticle(int seq) {
		// TODO 경로 call 화면 출력
		return MapDaoImpl.getMapDao().getMapArticle(seq);
	}

	@Override
	public List<MapDto> listMapArticle(int bcode, int pg, String key, String word) {
		// TODO 경로 목록
		int end = pg*BoardConstance.LIST_SIZE;
		int start = end-BoardConstance.LIST_SIZE;
		// TODO 글 목록 보기
		Map<String, String> map = new HashMap<String, String>();
		map.put("bcode", bcode+"");
		map.put("key", key);
		map.put("word", word);
		map.put("start", start+"");
		map.put("end", end+"");
		return MapDaoImpl.getMapDao().listMapArticle(map);
	}

	@Override
	public int modifyArticle(MapDto mapDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteArticle(int seq) {
		// TODO map 삭제 dao 호출
		return MapDaoImpl.getMapDao().deleteArticle(seq);
	}

}
