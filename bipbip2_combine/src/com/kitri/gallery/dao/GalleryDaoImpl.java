package com.kitri.gallery.dao;

import java.sql.*;
import java.util.*;

import com.kitri.gallery.model.GalleryDto;
import com.kitri.gallery.dao.GalleryDao;
import com.kitri.gallery.dao.GalleryDaoImpl;

import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class GalleryDaoImpl implements GalleryDao {

	private static GalleryDao galleryDao;

	static {
		galleryDao = new GalleryDaoImpl();
	}

	private GalleryDaoImpl() {
	}

	public static GalleryDao getGalleryDao() {
		return galleryDao;
	}
	@Override
	public int writeArticle(GalleryDto galleryDto) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert all \n");
			sql.append("	into board (seq, name, id, email, subject, content, hit, logtime, bcode) \n");
			sql.append("	values(?, ?, ?, ?, ?, ?, 0, sysdate, 4) \n");
			sql.append("	into album (aseq, seq, orign_picture, save_picture, savefolder, type) \n");
			sql.append("	values (album_aseq.nextval, ?, ?, ?, ?, 0) \n");
			sql.append("select * from dual \n");
			
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setInt(++idx, galleryDto.getSeq());
			pstmt.setString(++idx, galleryDto.getName());
			pstmt.setString(++idx, galleryDto.getId());
			pstmt.setString(++idx, galleryDto.getEmail());
			pstmt.setString(++idx, galleryDto.getSubject());
			pstmt.setString(++idx, galleryDto.getContent());
//			pstmt.setInt(++idx, galleryDto.getBcode());
//			pstmt.setInt(++idx, galleryDto.getHit());
			pstmt.setInt(++idx, galleryDto.getSeq());
			pstmt.setString(++idx, galleryDto.getOrignPicture());
			pstmt.setString(++idx, galleryDto.getSavePicture());
			pstmt.setString(++idx, galleryDto.getSaveFolder());
			cnt = pstmt.executeUpdate();
			System.out.println("cnt =" + cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}
	@Override
	public GalleryDto getArticle(int seq) {
		GalleryDto galleryDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select 	b.seq, b.id, b.name, b.email, b.subject, \n");
			sql.append("		b.content, b.hit, b.logtime, b.bcode, \n");
			sql.append("		r.aseq, r.orign_picture, r.save_picture, r.savefolder, r.type \n");
			sql.append("from board b, album r \n");
			sql.append("where b.seq = r.seq \n");
			sql.append("and b.seq = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, seq);
//			System.out.println("찾는 글번호 : " + seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				galleryDto = new GalleryDto();
				galleryDto.setSeq(rs.getInt("seq"));
				galleryDto.setId(rs.getString("id"));
				galleryDto.setName(rs.getString("name"));
				galleryDto.setEmail(rs.getString("email"));
				galleryDto.setSubject(rs.getString("subject"));
				galleryDto.setContent(rs.getString("content"));
				galleryDto.setHit(rs.getInt("hit"));
				galleryDto.setLogtime(rs.getString("logtime"));
				galleryDto.setBcode(rs.getInt("bcode"));
				galleryDto.setAseq(rs.getInt("aseq"));
				galleryDto.setOrignPicture(rs.getString("orign_picture"));
				galleryDto.setSavePicture(rs.getString("save_picture"));
				galleryDto.setSaveFolder(rs.getString("savefolder"));
				galleryDto.setType(rs.getInt("type"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return galleryDto;
	
	
	
	}

	@Override
	public List<GalleryDto> listArticle(Map<String, String> map) {
		List<GalleryDto> list = new ArrayList<GalleryDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select b.* \n");
			sql.append("from ( \n");
			sql.append("	select rownum rn, a.* \n");
			sql.append("	from ( \n");
			sql.append("		select 	b.seq, b.id, b.name, b.email, b.subject,  \n");
			sql.append("				b.content, b.hit, b.bcode, \n");
			sql.append("				decode(to_char(logtime, 'yymmdd'),  \n");
			sql.append("							to_char(sysdate, 'yymmdd'), to_char(logtime, 'hh24:mi:ss'),  \n");
			sql.append("							to_char(logtime, 'yy.mm.dd')) logtime, \n");
			sql.append("				r.aseq, r.orign_picture, r.save_picture, r.savefolder, r.type  \n");
			sql.append("		from board b, album r \n");
			sql.append("		where b.seq = r.seq \n");
			sql.append("		and b.bcode = ? \n");
			
			String key = map.get("key");
			String word = map.get("word");
			if(!key.isEmpty() && !word.isEmpty()) {
//				제목 >> like, 
//				글쓴이, 글번호 >> =
				if(key.equals("subject"))
					sql.append("		and subject like '%'||?||'%' \n");
				else
					sql.append("		and b." + key + " = ? \n");
			}
			
			sql.append("		order by b.seq desc \n");
			sql.append("		) a \n");
			sql.append("	where rownum <= ? \n");
			sql.append("	) b \n");
			sql.append("where b.rn > ? \n");
			System.out.println(sql.toString());
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, map.get("bcode"));
			if(!key.isEmpty() && !word.isEmpty()) 
				pstmt.setString(++idx, word);
			pstmt.setString(++idx, map.get("end"));
			pstmt.setString(++idx, map.get("start"));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GalleryDto galleryDto = new GalleryDto();
				galleryDto.setSeq(rs.getInt("seq"));
				galleryDto.setId(rs.getString("id"));
				galleryDto.setName(rs.getString("name"));
				galleryDto.setEmail(rs.getString("email"));
				galleryDto.setSubject(rs.getString("subject"));
				galleryDto.setContent(rs.getString("content"));
				galleryDto.setHit(rs.getInt("hit"));
				galleryDto.setLogtime(rs.getString("logtime"));
				galleryDto.setBcode(rs.getInt("bcode"));
				galleryDto.setAseq(rs.getInt("aseq"));
				galleryDto.setOrignPicture(rs.getString("orign_picture"));
				galleryDto.setSavePicture(rs.getString("save_picture"));
				galleryDto.setSaveFolder(rs.getString("savefolder"));
				galleryDto.setType(rs.getInt("type"));
				list.add(galleryDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		//System.out.println(list.get(0).getSavePicture());
		return list;
	}

	@Override
	public int modifyArticle(GalleryDto galleryDto) {
		int cnt = 0;
		StringBuffer sql= new StringBuffer();;
		StringBuffer sql1= new StringBuffer();;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			sql.append("update board \n");
			sql.append("	set content = ?, subject = ? \n");
			sql.append("where seq = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, galleryDto.getContent());
			pstmt.setString(2, galleryDto.getSubject());
			pstmt.setInt(3, galleryDto.getSeq());
			pstmt.close();
			
			sql1.append("update album \n");
			sql1.append("set save_picture = ? \n");
			sql1.append("where seq = ? \n");
			pstmt = conn.prepareStatement(sql1.toString());
			pstmt.setString(1, galleryDto.getSavePicture());
			pstmt.setInt(2, galleryDto.getSeq());
			cnt = pstmt.executeUpdate();
			
			System.out.println("cnt=" + cnt);
			if(cnt != 0){
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		
		return cnt;
	}

	@Override
	public int deleteArticle(int seq) {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer deleteAlbum = new StringBuffer();
			deleteAlbum.append(" \n");
			deleteAlbum.append("delete album \n");
			deleteAlbum.append("where seq = ? \n");
			pstmt = conn.prepareStatement(deleteAlbum.toString());
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
			
					
			conn.setAutoCommit(true);
			cnt = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				cnt = 0;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}
}
