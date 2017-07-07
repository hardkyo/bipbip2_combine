package com.kitri.gallery.dao;

import java.sql.*;
import java.util.Map;

import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class GalleryCommonDaoImpl implements GalleryCommonDao {

	private static GalleryCommonDao commonDao;
	
	static{
		commonDao = new GalleryCommonDaoImpl();
	}
	private  GalleryCommonDaoImpl() {}
	
	
	public static GalleryCommonDao getCommonDao() {
		return commonDao;
	}



	@Override
	public int getNextSeq() {
		int seq = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			String sql = "select board_seq.nextval from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			seq = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return seq;
	}

	@Override
	public void updateHit(int seq) {
		
		StringBuffer sql = new StringBuffer();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
		conn = DBConnection.getConnection();
		sql.append("update board \n");
		sql.append("set hit = hit + 1 \n");
		sql.append("where seq = ? \n");
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setInt(1, seq);
		pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}		
	}

	@Override
	public int newArticleCount(int bcode) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(seq) \n");
			sql.append("from board \n");
			sql.append("where bcode = ? \n");
			sql.append("and to_char(logtime, 'yymmdd') = to_char(sysdate, 'yymmdd')");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, bcode);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return count;
	}

	@Override
	public int totalArticleCount(Map<String, String> map) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(seq) \n");
			sql.append("from board \n");
			sql.append("where bcode = ? \n");
			String key = map.get("key");
			String word = map.get("word");
			if (!key.isEmpty() && !word.isEmpty()) {
				if (key.equals("subject")){
					sql.append("		and subject like '%'||?||'%' \n");
				}
				else
					sql.append("		and " + key + " = ? \n");
			}
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, map.get("bcode"));
			if (!key.isEmpty() && !word.isEmpty()) {
//				pstmt.setString(2, map.get("word"));
				pstmt.setString(2, map.get("word"));
			} 
//			else {
//				pstmt.setString(2, map.get("key"));
//			}
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return count;
	}

}