package com.kitri.freeboard.service;

import java.util.*;

import com.kitri.freeboard.dao.CommonDaoImpl;
import com.kitri.freeboard.dao.FreeBoardDaoImpl;
import com.kitri.freeboard.model.BoardDto;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.util.Constant;

public class FreeBoardServiceImpl implements FreeBoardService {
	private static FreeBoardService service;
	
	static {
		service = new FreeBoardServiceImpl();
	}
	private FreeBoardServiceImpl() {}
	
	public static FreeBoardService getService() {
		return service;
	}

	@Override
	public int freeBoardWrite(FreeBoardDto dto) {
		int count = 0;
		count = FreeBoardDaoImpl.getDao().freeBoardWrite(dto);
		return count;
	}

	@Override
	public List<FreeBoardDto> listArticle(int bcode, int pg, String key, String word) {
		List<FreeBoardDto> list = null;
		
//		int end = pg * 20;
//		int start = (pg-1)*20 +1;
//		int start = end - 20;
		
		int end = pg * Constant.LIST_SIZE;
		int start = end - Constant.LIST_SIZE;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("bcode", bcode + "");
		map.put("key", key);
		map.put("word", word);
		map.put("start", start + "");
		map.put("end", end + "");
		
		list = FreeBoardDaoImpl.getDao().listArticle(map);
		return list;
	}

	@Override
	public int replyArticle(FreeBoardDto dto) {
		int count = 0;
		count = FreeBoardDaoImpl.getDao().replyArticle(dto);
		return count;
	}

	@Override
	public int modifyArticle(BoardDto boardDto) {
		int count = 0;
		count = FreeBoardDaoImpl.getDao().modifyArticle(boardDto);
		return count;
	}

	@Override
	public int deleteArticle(int seq) {
		int count = 0;
		count = FreeBoardDaoImpl.getDao().deleteArticle(seq);
		return count;
	}

	@Override
	public List<FreeBoardDto> getHotList() {
		List<FreeBoardDto> list = FreeBoardDaoImpl.getDao().getHotList();
		return list;
	}
	
}
