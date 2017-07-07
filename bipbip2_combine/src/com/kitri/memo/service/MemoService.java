package com.kitri.memo.service;

import java.util.List;

import com.kitri.memo.model.MemoDto;


public interface MemoService {
	int writeMemo(MemoDto memoDto);
	List<MemoDto> listMemo(int seq);
	
	void modifyMemo(MemoDto memoDto);
	void deleteMemo(int mseq);
}
