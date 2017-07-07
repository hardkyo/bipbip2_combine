package com.kitri.gallery.service;

import com.kitri.util.PageNavigation;

public interface GalleryCommonService {
	int getNextSeq();
	void updateHit(int seq);
	PageNavigation makePageNavigation(int bcode, int pg, String key, String word);
}
