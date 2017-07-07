package com.kitri.maproute.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kitri.map.model.MapDto;
import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class MapRouteDaoImpl implements MapRouteDao {
	
	public static MapRouteDao getMapRouteDao() {
		return mapRouteDao;
	}


	private static MapRouteDao mapRouteDao;
	
	static{
		mapRouteDao = new MapRouteDaoImpl();
	}

	private MapRouteDaoImpl() {}
	
	
	@Override
	public List<MapDto> listMapArticle(int bcode, int pg, String key, String word) {
		// TODO 글 목록 검색
		List<MapDto> list = new ArrayList<MapDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("\n");
			sql.append("select\n");
			sql.append("loc1,loc2, m.seq, m.memo,b.logtime, \n");
			sql.append("id, name, hit, m.loc1x x1, m.loc1y, m.loc2x x2, m.loc2y \n");
			sql.append("from map m, board b\n"); // 조인할 테이블 가져올 것
			sql.append("where m.seq=b.seq\n");
			//sql.append("and map_seq = ?\n");
			// 수정해야합니다.
			
//			String key = map.get("key");
//			String word = map.get("word");
//			if(!key.isEmpty() && !word.isEmpty()){
//				// 제목(like), 글쓴이(=), 번호(=)
//				if (key.equals("subject")){
//					sql.append("		and subject like '%'||?||'%'\n");
//				} else {
//					sql.append("		and b."+key+"=?\n");
//				}
//
//			}			
//			sql.append("	order by m.seq  desc\n");
//			sql.append(") a\n");
//			sql.append("where rownum <= ?\n");
//			sql.append(") b \n");
//			sql.append("where b.rn >?\n");
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql.toString());
//			int idx = 0;
//			pstmt.setString(++idx, map.get("bcode"));
//			if(!key.isEmpty() && !word.isEmpty()) 
//			pstmt.setString(++idx, word);
//			pstmt.setString(++idx, map.get("end"));
//			pstmt.setString(++idx, map.get("start"));
			rs = pstmt.executeQuery();
			while (rs.next()){
				MapDto mapDto = new MapDto();
				////// map 테이블에서는 제목만 가져온댜.
				
				mapDto.setLoc1(rs.getString("loc1"));
				mapDto.setLoc2(rs.getString("loc2"));
				mapDto.setSeq(rs.getInt("seq"));
				mapDto.setMemo(rs.getString("memo"));
				mapDto.setLogtime(rs.getString("logtime"));
				mapDto.setId(rs.getString("id"));
				mapDto.setName(rs.getString("name"));
				mapDto.setHit(rs.getInt("hit"));
				mapDto.setLoc1X(rs.getDouble("x1"));
				mapDto.setLoc1Y(rs.getDouble("loc1y"));
				mapDto.setLoc2X(rs.getDouble("x2"));
				mapDto.setLoc2Y(rs.getDouble("loc2y"));
				
				list.add(mapDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt,rs);
		}

		return list;

	}

}
