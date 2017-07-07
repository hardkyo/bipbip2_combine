package com.kitri.map.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kitri.map.model.MapDto;
import com.kitri.util.db.DBClose;
import com.kitri.util.db.DBConnection;

public class MapDaoImpl implements MapDao {
	
	private static MapDao mapDao;
	
	public static MapDao getMapDao() {
		return mapDao;
	}

	static {
		mapDao = new MapDaoImpl();
	}

	@Override
	public int writeMapArticle(MapDto mapDto) {
		// TODO 경로 DB 등록

		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("insert all\n");
			
			sql.append("into board (id, name, email, seq, hit, bcode, subject)\n");
			sql.append("values (?,?,?, ?, 0,2,' ')\n");
			
			sql.append("into map (loc1X,loc1Y,loc2X,loc2Y,\n");
			sql.append("sec1X,sec1Y,sec2X,sec2Y,sec3X,sec3Y,\n");
			sql.append("loc1,loc2,sec1,sec2,sec3,\n");
			sql.append("memo, mapseq, seq)\n");

			sql.append("values (?,?,?,?,\n");
			sql.append("?,?,?,?,?,?,\n");
			sql.append("?,?,?,?,?,\n");
			sql.append("?, map_mapseq.nextval, ?)\n");

			sql.append("SELECT * FROM DUAL\n");

		pstmt = conn.prepareStatement(sql.toString());

			int idx = 0;

			pstmt.setString(++idx, mapDto.getId());
			pstmt.setString(++idx, mapDto.getName());
			pstmt.setString(++idx, mapDto.getEmail());
			pstmt.setInt(++idx, mapDto.getSeq());
			
			
			pstmt.setDouble(++idx, mapDto.getLoc1X());
			pstmt.setDouble(++idx, mapDto.getLoc1Y());
			pstmt.setDouble(++idx, mapDto.getLoc2X());
			pstmt.setDouble(++idx, mapDto.getLoc2Y());
			pstmt.setDouble(++idx, mapDto.getSec1X());
			pstmt.setDouble(++idx, mapDto.getSec1Y());
			pstmt.setDouble(++idx, mapDto.getSec2X());
			pstmt.setDouble(++idx, mapDto.getSec2Y());
			pstmt.setDouble(++idx, mapDto.getSec3X());
			pstmt.setDouble(++idx, mapDto.getSec3Y());
			pstmt.setString(++idx, mapDto.getLoc1());
			pstmt.setString(++idx, mapDto.getLoc2());
			pstmt.setString(++idx, mapDto.getSec1());
			pstmt.setString(++idx, mapDto.getSec2());
			pstmt.setString(++idx, mapDto.getSec3());
			pstmt.setString(++idx, mapDto.getMemo());
			//pstmt.setInt(++idx, mapDto.getMapHit());
			//pstmt.setInt(++idx, mapDto.getMap_seq());
			pstmt.setInt(++idx, mapDto.getSeq());


			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return cnt;
	}

	@Override
	public MapDto getMapArticle(int seq) {
		MapDto mapDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("\n");
			sql.append("select loc1X,loc1Y,loc2X,loc2Y,\n");
			sql.append("sec1X,sec1Y,sec2X,sec2Y,sec3X,sec3Y,\n");
			sql.append("loc1,loc2,sec1,sec2,sec3,\n");
			sql.append("memo,\n");
			sql.append("id, name, b.hit,  b.seq\n");
			sql.append("from map m, board b\n"); // 조인할 테이블 가져올 것
			sql.append("where m.seq=b.seq\n");
			sql.append("and m.seq = ?\n");
			// 수정해야합니다.
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();

			rs.next();
			mapDto = new MapDto();
			mapDto.setLoc1X(rs.getDouble("loc1X"));
			mapDto.setLoc1Y(rs.getDouble("loc1Y"));
			mapDto.setLoc2X(rs.getDouble("loc2X"));
			mapDto.setLoc2Y(rs.getDouble("loc2Y"));
			mapDto.setSec1X(rs.getDouble("sec1X"));
			mapDto.setSec1Y(rs.getDouble("sec1Y"));
			mapDto.setSec2X(rs.getDouble("sec2X"));
			mapDto.setSec2Y(rs.getDouble("sec2Y"));
			mapDto.setSec3X(rs.getDouble("sec3X"));
			mapDto.setSec3Y(rs.getDouble("sec3Y"));
			
			mapDto.setLoc1(rs.getString("loc1"));
			mapDto.setLoc2(rs.getString("loc2"));
			mapDto.setSec1(rs.getString("sec1"));
			mapDto.setSec2(rs.getString("sec2"));
			mapDto.setSec3(rs.getString("sec3"));
			
			mapDto.setMemo(rs.getString("memo"));
			mapDto.setHit(rs.getInt("hit"));
			mapDto.setSeq(rs.getInt("seq"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return mapDto;


	}

	@Override
	public List<MapDto> listMapArticle(Map<String, String> map) {

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
					sql.append("loc1,loc2, m.seq,\n");
					sql.append("id, name, hit \n");
					sql.append("from map m, board b\n"); // 조인할 테이블 가져올 것
					sql.append("where m.seq=b.seq\n");
					//sql.append("and map_seq = ?\n");
					// 수정해야합니다.
					
//					String key = map.get("key");
//					String word = map.get("word");
//					if(!key.isEmpty() && !word.isEmpty()){
//						// 제목(like), 글쓴이(=), 번호(=)
//						if (key.equals("subject")){
//							sql.append("		and subject like '%'||?||'%'\n");
//						} else {
//							sql.append("		and b."+key+"=?\n");
//						}
//
//					}			
					sql.append("	order by m.seq  desc\n");
//					sql.append(") a\n");
//					sql.append("where rownum <= ?\n");
//					sql.append(") b \n");
//					sql.append("where b.rn >?\n");

					pstmt = conn.prepareStatement(sql.toString());
//					int idx = 0;
//					pstmt.setString(++idx, map.get("bcode"));
//					if(!key.isEmpty() && !word.isEmpty()) 
//					pstmt.setString(++idx, word);
//					pstmt.setString(++idx, map.get("end"));
//					pstmt.setString(++idx, map.get("start"));
					rs = pstmt.executeQuery();
					while (rs.next()){
						MapDto mapDto = new MapDto();
						////// map 테이블에서는 제목만 가져온댜.	
						mapDto.setLoc1(rs.getString("loc1"));
						mapDto.setLoc2(rs.getString("loc2"));
						mapDto.setSeq(rs.getInt("seq"));
						
						mapDto.setId(rs.getString("id"));
						mapDto.setName(rs.getString("name"));
						mapDto.setHit(rs.getInt("hit"));
						
						list.add(mapDto);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					DBClose.close(conn, pstmt,rs);
				}

				return list;

	}

	@Override
	public int modifyArticle(MapDto mapDto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteArticle(int seq) {
		// TODO 경로 삭제
		System.out.println("자 " +seq + " 삭제 들어갑니다~");
		int check = 0;
		int check2 = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			StringBuffer mapsql = new StringBuffer();
			mapsql.append("delete \n");
			mapsql.append("from map\n");
			mapsql.append("where seq=?\n");
			pstmt = conn.prepareStatement(mapsql.toString());
			pstmt.setInt(1, seq);
			check = pstmt.executeUpdate();
			pstmt.close();
			if(check==0){
				conn.rollback();
				System.out.println("data delete has failed at 1st try");
			} else {
				check2 = check;
				check = 0;
			}
			StringBuffer boardsql = new StringBuffer();
			boardsql.append("delete \n");
			boardsql.append("from board\n");
			boardsql.append("where seq=?\n");

			pstmt = conn.prepareStatement(boardsql.toString());
			pstmt.setInt(1, seq);
			check = pstmt.executeUpdate();
			if(check==0){
				conn.rollback();
				System.out.println("data delete has failed at 2nd try");
			}			
			
			if(check!=0)
			
			System.out.println(check+ check2+" of data has deleted");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return check;
	}

}
