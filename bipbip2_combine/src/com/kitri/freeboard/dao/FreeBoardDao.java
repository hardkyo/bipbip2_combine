package com.kitri.freeboard.dao;

import java.util.List;
import java.util.Map;

import com.kitri.freeboard.model.BoardDto;
import com.kitri.freeboard.model.FreeBoardDto;

public interface FreeBoardDao {
	int freeBoardWrite (FreeBoardDto dto);

//	페이지 불러올 때 pg가 아닌 int start, int end로 
	List<FreeBoardDto> listArticle(Map<String, String> map);
	int replyArticle(FreeBoardDto dto);
	
	int modifyArticle(BoardDto dto);
	int deleteArticle(int seq);
	
	List<FreeBoardDto> getHotList();
}
