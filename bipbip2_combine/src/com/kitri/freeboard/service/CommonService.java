package com.kitri.freeboard.service;

import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.member.model.MemberDto;
import com.kitri.util.PageNavigation;

public interface CommonService {
	int getNextSeq();
	PageNavigation makePageNavigation(int bcode, int pg, String key, String word);
	
	FreeBoardDto getArticle(int seq);
	void updateHit (int seq);
	
	int plusUp(int seq);
	int plusdown(int seq);

	
	
	void callCommonDaoUpdateHit (int seq, MemberDto loginInfo);
	MemberDto updateLoginInfo(MemberDto loginInfo);
	
}
