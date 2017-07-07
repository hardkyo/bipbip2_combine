package com.kitri.memo.dao;

import java.util.List;

import com.kitri.memo.model.MemoDto;


public interface MemoDao {
	int writeMemo(MemoDto memoDto);
	List<MemoDto> listMemo(int seq);
	
	void modifyMemo(MemoDto memoDto);
	void deleteMemo(int mseq);
}
