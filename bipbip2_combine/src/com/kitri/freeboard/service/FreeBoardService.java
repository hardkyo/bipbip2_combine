package com.kitri.freeboard.service;

import java.util.List;

import com.kitri.freeboard.model.BoardDto;
import com.kitri.freeboard.model.FreeBoardDto;

public interface FreeBoardService {
	int freeBoardWrite (FreeBoardDto dto);
	
	List<FreeBoardDto> listArticle(int bcode, int pg, String key, String word);
	int replyArticle(FreeBoardDto dto);
	
	int modifyArticle(BoardDto dto);
	int deleteArticle(int seq);
	
	List<FreeBoardDto> getHotList();
}
