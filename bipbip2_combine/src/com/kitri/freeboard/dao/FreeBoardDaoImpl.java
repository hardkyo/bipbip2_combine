package com.kitri.freeboard.dao;

import java.sql.*;
import java.util.*;

import com.kitri.freeboard.model.BoardDto;
import com.kitri.freeboard.model.FreeBoardDto;
import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class FreeBoardDaoImpl implements FreeBoardDao {
	private static FreeBoardDao dao;
	
	static {
		dao = new FreeBoardDaoImpl();
	}
	
	private FreeBoardDaoImpl() {}
	
	public static FreeBoardDao getDao() {
		return dao;
	}

	@Override
	public int freeBoardWrite(FreeBoardDto dto) {
		int count = 0;

		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
//		System.out.println("freeboardwrite dao >>>>>>>>>>>  "  + dto.getSeq());
		try {
			conn = DBConnection.getConnection();
			sql.append("");
			sql.append("insert all\n");
			sql.append("	into board (seq, name, id, email, subject, content, hit, logtime, bcode)\n");
			sql.append("	values (?, ?, ?, ?, ?, ?, 0, sysdate, ?)\n");
			sql.append("	into reboard (rseq, seq, ref, lev, step, pseq, reply)\n");
			sql.append("	values (reboard_rseq.nextval, ?, ?, 0, 0, 0, 0)\n");
			sql.append("select * from dual\n");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setInt(++idx, dto.getSeq());
			pstmt.setString(++idx, dto.getName());
			pstmt.setString(++idx, dto.getId());
			pstmt.setString(++idx, dto.getEmail());
			pstmt.setString(++idx, dto.getSubject());
			pstmt.setString(++idx, dto.getContent());
			pstmt.setInt(++idx, dto.getBcode());
			pstmt.setInt(++idx, dto.getSeq());
			pstmt.setInt(++idx, dto.getRef());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return count;
	}



	@Override
	public List<FreeBoardDto> listArticle(Map<String, String> map) {
		List<FreeBoardDto> list = new ArrayList<FreeBoardDto>();

		StringBuffer sql = new StringBuffer();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			sql.append("select b.* \n");
			sql.append("from ( \n");
			sql.append("	select rownum rn, a.* \n");
			sql.append("	from ( \n");
			sql.append("		select 	b.seq, b.id, b.name, b.email, b.subject,  \n");
			sql.append("				b.content, b.hit, b.bcode, \n");
			sql.append("				decode(to_char(logtime, 'yymmdd'),  \n");
			sql.append("							to_char(sysdate, 'yymmdd'), to_char(logtime, 'hh24:mi:ss'),  \n");
			sql.append("							to_char(logtime, 'yy.mm.dd')) logtime, b.up up, b.down down, \n");
			sql.append("				r.rseq, r.ref, r.lev, r.step, r.pseq, r.reply  \n");
			sql.append("		from board b, reboard r \n");
			sql.append("		where b.seq = r.seq \n");
			sql.append("		and b.bcode = ? \n");

			String key = map.get("key");
			String word = map.get("word");
			if (!key.isEmpty() && !word.isEmpty()) {
				if ("subject".equals(key)) {
					sql.append("		and subject like '%'||?||'%' \n");
					// System.out.println("subject 들어오나?");
					// System.out.println(key);
				} else {
					sql.append("		and b." + key + " = ? \n");
					// System.out.println("key와 word는 들어오나?");
					// System.out.println(key.equals("seq") ? "\tseq":"\tname"
					// );
				}
			}
			sql.append("		order by r.ref desc, r.step asc \n");
			sql.append("		) a \n");
			sql.append("	where rownum <= ? \n");
			sql.append("	) b \n");
			sql.append("where b.rn > ? \n");
//			System.out.println(sql);
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, map.get("bcode"));
			if (!key.isEmpty() && !word.isEmpty()) {
				pstmt.setString(++idx, map.get("word"));
			}
			pstmt.setString(++idx, map.get("end"));
			pstmt.setString(++idx, map.get("start"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				FreeBoardDto dto = new FreeBoardDto();
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
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public int replyArticle(FreeBoardDto dto) {
		int count = 0;

		FreeBoardDto parentDto = CommonDaoImpl.getCommonDao().getArticle(dto.getPseq());
		if (parentDto != null) {

			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = DBConnection.getConnection();
				//####
				conn.setAutoCommit(false);

				StringBuffer update_step = new StringBuffer();
				update_step.append("update reboard \n");
				update_step.append("set step = step + 1 \n");
				update_step.append("where ref = ? and  step > ? \n");
				pstmt = conn.prepareStatement(update_step.toString());
				pstmt.setInt(1, parentDto.getRef()); //원글의 ref
				pstmt.setInt(2, parentDto.getStep()); //원글의 step
				pstmt.executeUpdate();
				pstmt.close();

				StringBuffer insert_reply = new StringBuffer();
				insert_reply.append("");
				insert_reply.append("insert all\n");
				insert_reply.append("	into board (seq, name, id, email, subject, content, hit, logtime, bcode)\n");
				insert_reply.append("	values (?, ?, ?, ?, ?, ?, 0, sysdate, ?)\n");
				insert_reply.append("	into reboard (rseq, seq, ref, lev, step, pseq, reply)\n");
				insert_reply.append("	values (reboard_rseq.nextval, ?, ?, ?, ?, ?, 0)\n");
				insert_reply.append("select * from dual \n");
				pstmt = conn.prepareStatement(insert_reply.toString());
				int idx = 0;
				//#### 답글
				pstmt.setInt(++idx, dto.getSeq());
				pstmt.setString(++idx, dto.getName());
				pstmt.setString(++idx, dto.getId());
				pstmt.setString(++idx, dto.getEmail());
				pstmt.setString(++idx, dto.getSubject());
				pstmt.setString(++idx, dto.getContent());
				pstmt.setInt(++idx, dto.getBcode());
				pstmt.setInt(++idx, dto.getSeq());
				//원글
				pstmt.setInt(++idx, parentDto.getRef());
				pstmt.setInt(++idx, parentDto.getLev() + 1);
				pstmt.setInt(++idx, parentDto.getStep() + 1);
				pstmt.setInt(++idx, parentDto.getSeq());
				pstmt.executeUpdate();
				pstmt.close();

				StringBuffer update_reply = new StringBuffer();
				update_reply.append("update reboard \n");
				update_reply.append("set reply = reply + 1 \n");
				update_reply.append("where seq = ? \n");
				pstmt = conn.prepareStatement(update_reply.toString());
				pstmt.setInt(1, dto.getPseq()); // dto.getPseq() == parentDto.getSeq()
				pstmt.executeUpdate();
				
				conn.commit();
				count = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
					count = 0;
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} finally {
				DBClose.close(conn, pstmt);
			}
		}
		return count;
	}

	@Override
	public int modifyArticle(BoardDto boardDto) {
		int count = 0;
		StringBuffer sql = new StringBuffer();;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			sql.append(" \n");
			sql.append("update board \n");
			sql.append("	set content = ?, subject = ? \n");
			sql.append("where seq = ? \n");
		
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getContent());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setInt(3, boardDto.getSeq());
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return count;
	}

	@Override
	public int deleteArticle(int seq) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer deleteReboard = new StringBuffer();
			deleteReboard.append(" \n");
			deleteReboard.append("delete reboard \n");
			deleteReboard.append("where seq = ? \n");
			pstmt = conn.prepareStatement(deleteReboard.toString());
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			StringBuffer deleteBoard = new StringBuffer();
			deleteBoard.append(" \n");
			deleteBoard.append("delete board \n");
			deleteBoard.append("where seq = ? \n");
			pstmt = conn.prepareStatement(deleteBoard.toString());
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			pstmt.close();
			
			StringBuffer update_ref_reply = new StringBuffer();
			update_ref_reply.append("update reboard \n");
			update_ref_reply.append("set reply = reply - 1 \n");
			update_ref_reply.append("where seq = ? \n");
			
			int pseq = selectDelete(seq);
			
			pstmt = conn.prepareStatement(update_ref_reply.toString());
			pstmt.setInt(1, pseq);
			pstmt.executeUpdate();
			
			conn.setAutoCommit(true);
			count = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				count = 0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBClose.close(conn, pstmt);
		}
		return count;
	}
	
	private int selectDelete(int seq) {
		int pseq = 0;
		StringBuffer sql = new StringBuffer();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			sql.append("select \n");
			sql.append("	pseq \n");
			sql.append("from reboard \n");
			sql.append("where seq = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			rs.next();
			pseq = rs.getInt("pseq");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return pseq;
	}

	
	
	
	
	//인기글 hotlist select
	@Override
	public List<FreeBoardDto> getHotList() {
		List<FreeBoardDto> list = new ArrayList<FreeBoardDto>();
		FreeBoardDto dto = null;

		StringBuffer sql = new StringBuffer();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			sql.append("select seq, subject, id, hit, up, reply \n");
			sql.append("from  \n");
			sql.append("	( \n");
			sql.append("	select b.subject, b.id, b.seq, b.hit, b.up, r.reply  \n");
			sql.append("	from board b, reboard r  \n");
			sql.append("	where  \n");
			sql.append("		b.seq = r.seq and \n");
			sql.append("		hit + up*10 - down*10 > 10 \n");
			sql.append("	order by b.logtime desc, b.hit desc \n");
			sql.append("	) c \n");
			sql.append("where rownum <=5 \n");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new FreeBoardDto();
				dto.setSubject(rs.getString("subject"));
				dto.setId(rs.getString("id"));
				dto.setHit(rs.getInt("hit"));
				dto.setSeq(rs.getInt("seq"));
				dto.setReply(rs.getInt("reply"));
				dto.setUp(rs.getInt("up"));

//				System.out.println(dto.getSubject() + "  <<<<suject");
//				System.out.println(dto.getSeq() + "  <<<<seq");
//				System.out.println("------------------------------------");
				list.add(dto);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return list;
	}
}
