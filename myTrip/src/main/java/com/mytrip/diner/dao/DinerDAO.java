package com.mytrip.diner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mytrip.diner.vo.DinerVO;
import com.mytrip.util.db.DAO;
import com.mytrip.util.db.DBInfo;
import com.mytrip.util.pageobject.PageObject;

public class DinerDAO extends DAO{
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. 전체 데이터 가져오기
	public long getTotalRow(PageObject pageObject) throws Exception {
		long cnt = 0;
		// searchCondition : true = 검색조건이 있다, false = 검색 조건이 없다
		boolean searchCondition = pageObject.getWord() != null && !pageObject.getWord().equals("");
		
		// word로 검색한 조건이 없으면 area 조건 확인
		if(searchCondition == false) { searchCondition = pageObject.getArea() != 0;	}
		// word, area로 검색한 조건이 없으면 kind 조건 확인
		if(searchCondition == false) { searchCondition = pageObject.getKind() != 0;	}

		
		System.out.println(searchCondition);
		System.out.println("word : "+pageObject.getWord());
		System.out.println("area : "+pageObject.getArea());
		System.out.println("kind : "+pageObject.getKind());
		try {
			con = DBInfo.getConnection();
			String sql = "SELECT count(*) FROM diner ";
			if(searchCondition) {
				// 검색 조건이 있으면 WHERE을 붙이고
				sql += " WHERE ";
				// 조건이 있는 항목의 데이터를 검색조건에 추가
				if(searchCondition) { sql += search(pageObject, ""); }
			}
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			idx = searchSetData(pageObject, pstmt, idx);
			rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				cnt = rs.getLong(1);
				System.out.println("맛집 리스트 전체 글의 개수 : "+cnt);
			}			
			else System.out.println("데이터가 존재하지 않음 - DinerDAO.getTotalRow()");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 리스트 리스트 - 전체 데이터 가져오기 DB 오류");
		} finally {
			try {
				DBInfo.close(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();				
			}
		}
		return cnt;
	}

	
	public List<DinerVO> list(PageObject pageObject) throws Exception {
		List<DinerVO> list = null;
		

		// searchCondition : true = 검색조건이 있다, false = 검색 조건이 없다
		boolean searchCondition = pageObject.getWord() != null && !pageObject.getWord().equals("");
		
		// word로 검색한 조건이 없으면 area 조건 확인
		if(searchCondition == false) { searchCondition = pageObject.getArea() != 0;	}
		// word, area로 검색한 조건이 없으면 kind 조건 확인
		if(searchCondition == false) { searchCondition = pageObject.getKind() != 0;	}
		
		System.out.println(searchCondition);
		System.out.println("area : "+pageObject.getArea());
		System.out.println("kind : "+pageObject.getKind());
		try {
			con = DBInfo.getConnection();
			String sql = "SELECT d.no, da.areaName, dk.kindName, d.name, d.introduce, d.mainImage, d.area, d.kind"
					+ " FROM diner d, dinerArea da, dinerKind dk ";
			if(searchCondition) { sql += " WHERE "; } // 검색 조건이 있으면 where절을 붙여준다.
			sql += 	search(pageObject, "d.") 
					+ ((searchCondition)?" AND d.area = da.no AND d.kind = dk.no ":" WHERE d.area = da.no AND d.kind = dk.no")
					+ " ORDER BY no DESC ";
			sql = " SELECT ROWNUM rnum, no, areaName, kindName, name, introduce, mainImage, area, kind FROM("+sql+")";
			sql = "SELECT rnum, no, areaName, kindName, name, introduce, mainImage, area, kind FROM( "+sql+" ) WHERE rnum BETWEEN ? AND ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			// 조건에 해당되는 pstmt 세팅
			idx = searchSetData(pageObject, pstmt, idx);
			pstmt.setLong(idx++, pageObject.getStartRow());
			pstmt.setLong(idx++, pageObject.getEndRow());
			rs = pstmt.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					if(list == null) list = new ArrayList<DinerVO>();
					DinerVO vo = new DinerVO();
					vo.setNo(rs.getLong("no"));
					vo.setAreaName(rs.getString("areaName"));
					vo.setKindName(rs.getString("kindName"));
					vo.setName(rs.getString("name"));
					vo.setIntroduce(rs.getString("introduce"));
					vo.setMainImage(rs.getString("mainImage"));
					vo.setArea(rs.getInt("area"));
					vo.setKind(rs.getInt("kind"));
					//vo에 다 담고 list에 추가
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 리스트 리스트 데이터 가져오는 중 DB 오류");
		} finally {
			try {
				DBInfo.close(con, pstmt);			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	private String search(PageObject pageObject, String alias) {
		String condition = "";		

		if(pageObject.getWord() != null && !pageObject.getWord().equals("")) {
			condition += " ( 1 = 0 ";
			if(pageObject.getKey().indexOf("n") != -1) { condition += " or "+ alias +"name like ? ) "; }
			if(pageObject.getKey().indexOf("a") != -1) { condition += " or "+ alias +"address like ? ) "; }
		}
		if(pageObject.getArea() != 0) {
			// 검색어가 있으면 AND를 붙여서 검색
			if(pageObject.getWord() != null && !pageObject.getWord().equals("")) { condition += " AND " + alias + "area = ? "; } 
			else { condition += alias + "area = ? "; }		
		}		
		if(pageObject.getKind() != 0) {
			if(pageObject.getWord() != null && !pageObject.getWord().equals("")) { condition += " AND " + alias + "kind = ? "; }
			// area도 조건에 있으면 AND를 붙여서 검색
			else if(pageObject.getArea() != 0) { condition += " AND " + alias + "kind = ? "; } 
			else { condition += alias + "kind = ? "; }
		}
		return condition;
	}	
	private int searchSetData(PageObject pageObject, PreparedStatement pstmt, int idx) throws SQLException{	
		
		String word = pageObject.getWord();
		if(pageObject.getWord() != null && !word.equals("")) { 
			if(pageObject.getKey().indexOf("n") != -1) { pstmt.setString(idx++, "%" + word + "%" ); }
			if(pageObject.getKey().indexOf("a") != -1) { pstmt.setString(idx++, "%" + word + "%" ); }
		}
		// area 혹은 kind가 설정되었다면 데이터 세팅할 때 데이터를 추가
		if(pageObject.getArea() != 0) { pstmt.setInt(idx++, pageObject.getArea() ); }
		if(pageObject.getKind() != 0) { pstmt.setInt(idx++, pageObject.getKind() ); }
		return idx;
	}
	
	// 2. 글보기
	public DinerVO view(DinerVO inVo) throws Exception {
		DinerVO vo = null;	
		try {
			con = DBInfo.getConnection();
			String sql = "SELECT d.no, da.areaName, dk.kindName, d.name, d.introduce, d.mainImage, d.address, d.tel, d.content, d.subImage, d.tag, d.openTime, d.restDay, d.area, d.kind,"
					+ " (SELECT 'LIKED' FROM dinerLike WHERE no = ? AND id = ? ) myLiked, "
					+ " (SELECT count(*) FROM dinerLike WHERE no = ?) likeCnt, "
					+ " (SELECT 'FAV' FROM dinerFav WHERE no = ? AND id = ? ) myFav, "
					+ " (SELECT count(*) FROM dinerFav WHERE no = ?) favCnt "
					+ " FROM diner d, dinerArea da, dinerKind dk "
					+ " WHERE (d.no = ?) AND (d.area = da.no AND d.kind = dk.no) ";
			System.out.println(sql);
			// 4. 실행객체 && 데이터
			pstmt = con.prepareStatement(sql);
			int idx=1;
			pstmt.setLong(idx++, inVo.getNo());
			pstmt.setString(idx++, inVo.getId());
			pstmt.setLong(idx++, inVo.getNo());
			pstmt.setLong(idx++, inVo.getNo());
			pstmt.setString(idx++, inVo.getId());
			pstmt.setLong(idx++, inVo.getNo());
			pstmt.setLong(idx++, inVo.getNo());
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 표시 / 데이터 담기
			if(rs != null && rs.next()) {
				vo = new DinerVO();
				vo.setNo(rs.getLong("no"));
				vo.setAreaName(rs.getString("areaName"));
				vo.setKindName(rs.getString("kindName"));
				vo.setName(rs.getString("name"));
				vo.setIntroduce(rs.getString("introduce"));
				vo.setMainImage(rs.getString("mainImage"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setContent(rs.getString("content"));
				vo.setSubImage(rs.getString("subImage"));
				vo.setTag(rs.getString("tag"));
				vo.setOpenTime(rs.getString("openTime"));
				vo.setRestDay(rs.getString("restDay"));
				vo.setArea(rs.getInt("area"));
				vo.setKind(rs.getInt("kind"));
				vo.setLikeCnt(rs.getInt("likeCnt"));
				vo.setFavCnt(rs.getInt("favCnt"));
				vo.setMyLiked(rs.getString("myLiked"));
				vo.setMyFav(rs.getString("myFav"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 글보기 - 데이터 가져오는 중 DB 오류");
		} finally {
			try {
				DBInfo.close(con, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	
	// 3. 글쓰기	
	
	public int write(DinerVO vo) throws Exception {
		int result = 0;	

		try {
			con = DBInfo.getConnection();
			String sql = "INSERT INTO diner(no, area, kind, name, address, tel, mainImage, introduce, content, subImage, tag, openTime, restDay)"
					+ "VALUES(diner_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			pstmt.setInt(idx++, vo.getArea());
			pstmt.setInt(idx++, vo.getKind());
			pstmt.setString(idx++, vo.getName());
			pstmt.setString(idx++, vo.getAddress());
			pstmt.setString(idx++, vo.getTel());
			pstmt.setString(idx++, vo.getMainImage());
			pstmt.setString(idx++, vo.getIntroduce());
			pstmt.setString(idx++, vo.getContent());
			pstmt.setString(idx++, vo.getSubImage());
			pstmt.setString(idx++, vo.getTag());
			pstmt.setString(idx++, vo.getOpenTime());
			pstmt.setString(idx++, vo.getRestDay());			
			result = pstmt.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 글 쓰기 - 글 작성 중 DB 오류");
		} finally {
			try {
				//7. 닫기
				DBInfo.close(con, pstmt);				
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		
		return result;
	}
	
	
	// 4. 이미지 수정 (제목과 내용)

	public int update(DinerVO vo) throws Exception {
		System.out.println(vo + " - DinerDAO.update().vo");
		int result = 0;
		try {
			con = DBInfo.getConnection();
			String sql = "UPDATE diner SET area = ?, kind = ?, name = ?, address = ?, tel = ?, mainImage = ?, introduce = ?, content = ?, subImage = ?, tag = ?, openTime = ?, restDay = ?"
					+ " WHERE no = ?";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			pstmt.setInt(idx++, vo.getArea());
			pstmt.setInt(idx++, vo.getKind());
			pstmt.setString(idx++, vo.getName());
			pstmt.setString(idx++, vo.getAddress());
			pstmt.setString(idx++, vo.getTel());
			pstmt.setString(idx++, vo.getMainImage());
			pstmt.setString(idx++, vo.getIntroduce());
			pstmt.setString(idx++, vo.getContent());
			pstmt.setString(idx++, vo.getSubImage());
			pstmt.setString(idx++, vo.getTag());
			pstmt.setString(idx++, vo.getOpenTime());
			pstmt.setString(idx++, vo.getRestDay());
			pstmt.setLong(idx++, vo.getNo());
			result = pstmt.executeUpdate();
			if(result == 0) throw new Exception("수정할 데이터가 존재하지 않습니다.");
			else System.out.println("수정 완료.");

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 리스트 글 수정 - 정보 수정 중 DB 오류 혹은 작성자가 아닙니다.");
		} finally {
			try {
				DBInfo.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	// 5. 삭제
	public int delete(DinerVO vo) throws Exception{
		int result = 0;
		try {
			con = DBInfo.getConnection();
			String sql = "DELETE FROM diner WHERE no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			//**4. 관리자 계정만 가능한 조건문
			result = pstmt.executeUpdate();
			if(result == 0) throw new Exception("삭제할 데이터가 존재하지 않습니다.");
			else System.out.println("삭제완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 삭제 - 글 삭제 중 DB 오류 또는 관리자가 아닙니다.");
		} finally {
			try {
				DBInfo.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return result;
	}
	
	// 좋아요 처리
	public int like(DinerVO vo) throws Exception{
		int result = 0;
		try {
			con = DBInfo.getConnection();
			String sql = "INSERT INTO dinerLike(no,id) VALUES(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			//**5. 관리자
			result = pstmt.executeUpdate();
			if(result == 0) throw new Exception("이미 좋아요 처리가 된 글입니다.");
			else System.out.println("좋아요 처리 완료.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 좋아요 처리 - 좋아요 처리중 DB 오류 또는 로그인 정보가 일치하지 않습니다.");
		} finally {
			try {
				DBInfo.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return result;
	}

	// 좋아요 취소 처리
	public int likeCancel(DinerVO vo) throws Exception{
		int result = 0;
		try {
			con = DBInfo.getConnection();
			String sql = "DELETE FROM dinerLike WHERE no = ? AND id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			result = pstmt.executeUpdate();
			if(result == 0) throw new Exception("정보를 다시 확인해주세요.");
			else System.out.println("좋아요 취소처리 완료.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 리스트 좋아요 취소 - 좋아요 취소 처리중 DB오류 또는 정보를 다시 확인해주세요.");
		} finally {
			try {
				DBInfo.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return result;
	}
	// 찜하기 처리
	public int fav(DinerVO vo) throws Exception{
		int result = 0;
		try {
			con = DBInfo.getConnection();
			String sql = "INSERT INTO dinerFav(no,id) VALUES(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			//**5. 관리자
			result = pstmt.executeUpdate();
			if(result == 0) throw new Exception("이미 좋아요 처리가 된 글입니다.");
			else System.out.println("좋아요 처리 완료.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 찜하기 처리 - 찜하기 처리중 DB 오류 또는 로그인 정보가 일치하지 않습니다.");
		} finally {
			try {
				DBInfo.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return result;
	}
	// 찜하기 취소 처리
	public int favCancel(DinerVO vo) throws Exception{
		int result = 0;
		try {
			con = DBInfo.getConnection();
			String sql = "DELETE FROM dinerFav WHERE no = ? AND id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			result = pstmt.executeUpdate();
			if(result == 0) throw new Exception("정보를 다시 확인해주세요.");
			else System.out.println("찜하기 취소처리 완료.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("맛집 찜하기 취소 - 찜하기 취소 처리 중 DB오류 또는 정보를 다시 확인해주세요.");
		} finally {
			try {
				DBInfo.close(con, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return result;
	}

}
