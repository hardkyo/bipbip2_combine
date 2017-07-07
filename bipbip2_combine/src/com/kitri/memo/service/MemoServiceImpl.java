package com.kitri.memo.service;

import java.util.List;

import com.kitri.memo.dao.MemoDaoImpl;
import com.kitri.memo.model.MemoDto;


public class MemoServiceImpl implements MemoService {
	private static MemoService memoServiceImpl;
	
	static {
		memoServiceImpl = new MemoServiceImpl();
	}
	private MemoServiceImpl() {}

	public static MemoService getMemoServiceImpl() {
		return memoServiceImpl;
	}

	@Override
	public int writeMemo(MemoDto memoDto) {
		int count = 0;
		count = MemoDaoImpl.getMemoDaoImpl().writeMemo(memoDto);
		return count;
	}

	@Override
	public List<MemoDto> listMemo(int seq) {
		List<MemoDto> list = MemoDaoImpl.getMemoDaoImpl().listMemo(seq);
		return list;
	}

	@Override
	public void modifyMemo(MemoDto memoDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMemo(int mseq) {
		// TODO Auto-generated method stub
		
	}

}
