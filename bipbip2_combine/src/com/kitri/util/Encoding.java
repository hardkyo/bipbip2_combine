package com.kitri.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encoding {
	
	public static String nullToBlank(String tmp) {
		return tmp == null ? "" : tmp; //널포인터 없애기 위함.
	}

	public static String isoToEuc(String tmp) {
		String euc = "";
		try {
			if(tmp != null)
				euc = new String(tmp.getBytes("ISO-8859-1"), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return euc;
	}
	
	public static String isoToUtf(String tmp) {
		String utf = "";
		try {
			if(tmp != null)
				utf = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return utf;
	}
	
	
	public static String urlFormat(String tmp){
		String url = "";
		try {
			if (url != null) {				
				url = URLEncoder.encode(tmp, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}
	
}
