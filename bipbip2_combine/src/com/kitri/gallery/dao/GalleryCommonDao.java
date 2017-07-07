package com.kitri.gallery.dao;

import java.util.Map;

public interface GalleryCommonDao {
	int getNextSeq();
	void updateHit(int seq);
	int newArticleCount (int bcode);
	int totalArticleCount(Map<String, String> map);
}
