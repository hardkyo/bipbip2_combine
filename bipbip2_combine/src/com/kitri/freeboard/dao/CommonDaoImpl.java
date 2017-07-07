package com.kitri.freeboard.dao;

import java.sql.*;
import java.util.Map;

import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.member.model.MemberDto;
import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class CommonDaoImpl implements CommonDao {
	private static CommonDao commonDao;
	
	static {
		commonDao = new CommonDaoImpl();
	}
	
	private CommonDaoImpl () {}
	
	public static final CommonDao getCommonDao() {
		return commonDao;
	}

	@Override
	public int getNextSeq() {
		int seq = 0;
		
		String sql = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		conn = DBConnection.getConnection();
		sql += "";
		sql += "select board_seq.nextval \n";
		sql += "from dual \n";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		rs.next();
		seq = rs.getInt(1);
		System.out.println(seq);
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		
		return seq;
	}

	@Override
	public FreeBoardDto getArticle(int seq) {
		FreeBoardDto dto = null;

		StringBuffer sql = new StringBuffer();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			sql.append("select\n");
			sql.append("	b.seq,\n");
			sql.append("	b.id,\n");
			sql.append("	b.name,\n");
			sql.append("	b.email,\n");
			sql.append("	b.subject,\n");
			sql.append("	b.content,\n");
			sql.append("	b.hit,\n");
			sql.append("	to_char(b.logtime, 'yyyy/mm/dd hh24:mi') logtime, \n");
			sql.append("	b.bcode,\n");
			sql.append("	r.rseq,\n");
			sql.append("	r.ref,\n");
			sql.append("	r.lev,\n");
			sql.append("	r.step,\n");
			sql.append("	r.pseq,\n");
			sql.append("	r.reply, \n");
			sql.append("	b.up, \n");
			sql.append("	b.down \n");
			sql.append("from board b, reboard r\n");
			sql.append("where\n");
			sql.append("	b.seq = r.seq and\n");
			sql.append("	b.seq = ?\n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new FreeBoardDto();
				dto.setSeq(rs.getInt("seq"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setHit(rs.getInt("hit"));
				dto.setLogtime(rs.getString("logtime"));
				dto.setBcode(rs.getInt("bcode"));
				dto.setRseq(rs.getInt("rseq"));
				dto.setRef(rs.getInt("ref"));
				dto.setLev(rs.getInt("lev"));
				dto.setStep(rs.getInt("step"));
				dto.setPseq(rs.getInt("pseq"));
				dto.setReply(rs.getInt("reply"));
				dto.setUp(rs.getInt("up"));
				dto.setDown(rs.getInt("down"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return dto;
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
	
	@Override
	public void updateHit(int seq) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
		conn = DBConnection.getConnection();
		
		StringBuffer updateHit = new StringBuffer();
		updateHit.append("update board \n");
		updateHit.append("set hit = hit + 1 \n");
		updateHit.append("where seq = ? \n");
		pstmt = conn.prepareStatement(updateHit.toString());
		pstmt.setInt(1, seq);
		pstmt.executeUpdate();		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
	}
	
	
	
	
	
	
	

	@Override
	public void updateHit(int seq, MemberDto loginInfo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
		conn = DBConnection.getConnection();
//		conn.setAutoCommit(false);
		
		StringBuffer updateHit = new StringBuffer();
		updateHit.append("update board \n");
		updateHit.append("set hit = hit + 1 \n");
		updateHit.append("where seq = ? \n");
		pstmt = conn.prepareStatement(updateHit.toString());
		pstmt.setInt(1, seq);
		pstmt.executeUpdate();		
//		pstmt.close();
//		
//		
//		StringBuffer updateCheckHit = new StringBuffer();
//		updateCheckHit.append("update member \n");
//		updateCheckHit.append("set checkhit = 1 \n");
//		updateCheckHit.append("where id = ?");
//		pstmt = conn.prepareStatement(updateCheckHit.toString());
//		pstmt.setString(1, loginInfo.getId());
//		pstmt.executeUpdate();
//		
//		conn.setAutoCommit(true);	
		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
	}

	@Override
	public MemberDto updateLoginInfo(MemberDto loginInfo) {
		
		
		MemberDto newLoginInfo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		conn = DBConnection.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select id, pass, name, email, addr1, addr2, phone, checkup, checkdown \n");
		sql.append("from member \n");
		sql.append("where id = ? \n");
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1, loginInfo.getId());
		rs = pstmt.executeQuery();
		rs.next();
		
		newLoginInfo = new MemberDto();
		newLoginInfo.setId(rs.getString("id"));
		newLoginInfo.setPass(rs.getString("pass"));
		newLoginInfo.setName(rs.getString("name"));
		newLoginInfo.setEmail(rs.getString("email"));
		newLoginInfo.setAddr1(rs.getString("addr1"));
		newLoginInfo.setAddr2(rs.getString("addr2"));
		newLoginInfo.setPhone(rs.getString("phone"));
		newLoginInfo.setChecklike(rs.getInt("checkup"));
		newLoginInfo.setCheckbad(rs.getInt("checkdown"));
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return newLoginInfo;
	}

	@Override
	public int plusUp(int seq) {
		int up = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		conn = DBConnection.getConnection();
		
		StringBuffer updateUp = new StringBuffer();
		updateUp.append("update board \n");
		updateUp.append("set up = up + 1 \n");
		updateUp.append("where seq = ? \n");
		pstmt = conn.prepareStatement(updateUp.toString());
		pstmt.setInt(1, seq);
		pstmt.executeUpdate();
		pstmt.close();
		
		StringBuffer getUp = new StringBuffer();
		getUp.append("select up \n");
		getUp.append("from board \n");
		getUp.append("where seq = ? \n");
		pstmt = conn.prepareStatement(getUp.toString());
		pstmt.setInt(1, seq);
		rs = pstmt.executeQuery();
		
		rs.next();
		up = rs.getInt("up");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return up;
	}

	@Override
	public int plusdown(int seq) {
		int down = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		conn = DBConnection.getConnection();
		
		StringBuffer updateDown = new StringBuffer();
		updateDown.append("update board \n");
		updateDown.append("set down = down + 1 \n");
		updateDown.append("where seq = ? \n");
		pstmt = conn.prepareStatement(updateDown.toString());
		pstmt.setInt(1, seq);
		pstmt.executeUpdate();		
		pstmt.close();
		
		StringBuffer getDown = new StringBuffer();
		getDown.append("select down \n");
		getDown.append("from board \n");
		getDown.append("where seq = ? \n");
		pstmt = conn.prepareStatement(getDown.toString());
		pstmt.setInt(1, seq);
		rs = pstmt.executeQuery();
		
		rs.next();
		down = rs.getInt("down");
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return down;

	}


}
