package com.kitri.memo.dao;

import java.sql.*;
import java.util.*;

import com.kitri.memo.model.MemoDto;
import com.kitri.util.db.*;

public class MemoDaoImpl implements MemoDao {
	private static MemoDao memoDaoImpl;
	
	static {
		memoDaoImpl = new MemoDaoImpl();
	}
	
	private  MemoDaoImpl() {
	}
	
	public static MemoDao getMemoDaoImpl() {
		return memoDaoImpl;
	}

	@Override
	public int writeMemo(MemoDto memoDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into memo (mseq, seq, id, name, mcontent, mtime) \n");
			sql.append("values (memo_mseq.nextval, ?, ?, ?, ?, sysdate ) \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, memoDto.getSeq());
			pstmt.setString(2, memoDto.getId());
			pstmt.setString(3, memoDto.getName());
			pstmt.setString(4, memoDto.getMcontent());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}

	@Override
	public List<MemoDto> listMemo(int seq) {
		List<MemoDto> list = new ArrayList<MemoDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select mseq, seq, id, mcontent, mtime \n");
			sql.append("from memo \n");
			sql.append("where seq = ? \n");
			sql.append("order by mseq \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemoDto memoDto = new MemoDto();
				memoDto.setMseq(rs.getInt("mseq"));
				memoDto.setSeq(rs.getInt("seq"));
				memoDto.setId(rs.getString("id"));
				memoDto.setMcontent(rs.getString("mcontent"));
				memoDto.setMtime(rs.getString("mtime"));

				list.add(memoDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
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
