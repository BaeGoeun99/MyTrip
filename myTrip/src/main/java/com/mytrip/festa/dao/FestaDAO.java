package com.mytrip.festa.dao;

import java.util.ArrayList;
import java.util.List;

import com.mytrip.festa.vo.FestaRepVO;
import com.mytrip.festa.vo.FestaVO;
import com.mytrip.util.db.DAO;
import com.mytrip.util.db.DBInfo;
import com.webjjang.util.PageObject;

/** 축제 DAO @author GOEUN **/

public class FestaDAO extends DAO{

// 축제 전체 데이터 가져오기 -----------------------------------------------------------------------------
	public long getTotalRow(PageObject pageObject) throws Exception {
		
		long cnt = 0;
		
		try {
			
			con = DBInfo.getConnection();
			System.out.println("FestaDAO.getTotalRow() 드라이버 연결 완료" );
			
			String sql = "select count(*) from festa ";			
			System.out.println("FestaDAO.getTotalRow().SQL : " + sql);
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs != null && rs.next()) {
				cnt = rs.getLong(1);
				System.out.println("[ 축제 데이터 전체 개수 = " + cnt + "개 ]");
			} else
				System.out.println("+데이터가 존재하지 않습니다+");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 리스트 전체 데이터 개수 가져오기 중 DB 오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
		}	
		return cnt;
	}//getTotalRow끝
	
// 축제 리스트 -------------------------------------------------------------------------------
	public List<FestaVO> list(PageObject pageObject) throws Exception {
		List<FestaVO> list = null;
		
		try {
			con = DBInfo.getConnection();
			System.out.println("FestaDAO.list() 드라이버 연결 완료" );
			
			String sql = "select no, title, photo, content, address, rgn, startDate, endDate from festa order by no desc";
			sql = "select rownum rnum, no, title, photo, content, address, rgn, startDate, endDate from( " + sql + ") ";
			sql = "select rnum, no, title, photo, content, address, rgn, to_char(startDate, 'yyyy.mm.dd') startDate, to_char(endDate, 'yyyy.mm.dd') endDate from( " + sql + ") where rnum between ? and ?";
			System.out.println("FestaDAO.list().SQL : " + sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, pageObject.getStartRow());
			pstmt.setLong(2, pageObject.getEndRow());
			
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					if (list == null) list = new ArrayList<>();
					FestaVO vo = new FestaVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setPhoto(rs.getString("photo"));
					vo.setContent(rs.getString("content"));
					vo.setAddress(rs.getString("address"));
					vo.setRgn(rs.getString("rgn"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					list.add(vo);
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 리스트 전체 데이터 가져오기 중 DB 오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
		}
		return list;
	}//list끝

// 조회수 증가 -----------------------------------------------------------------------------
//	public int increase(long no) {
//		int result = 0;
//		
//		try {
//			con = DBInfo.getConnection();
//			
//			String sql = "update festa set hit = hit + 1 where no = ?";
//			
//			pstmt = con.prepareStatement(sql);
//			pstmt.setLong(1, no);
//
//			result = pstmt.executeUpdate();	
//			
//			System.out.println("[조회수가 업데이트 되었습니다]");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//				DBInfo.close(con, pstmt);
//		}
//		return result;
//	}//increase끝	
	
// 축제 상세보기 -----------------------------------------------------------------------------
	public FestaVO view(FestaVO fvo) throws Exception {
		FestaVO vo = null;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "select no, title, content, photo, address, rgn, tel, website, host, "
					   + "(SELECT 'LIKED' from festa_like where no = ? and id = ? ) myLiked, "
//					   + "(SELECT 'REPLY' from festa_rep where no = ? and id = ? ) reply, "
					   + "to_char(startDate, 'yyyy.mm.dd') startDate, "
					   + "to_char(endDate, 'yyyy.mm.dd') endDate, "
					   + "to_char(writeDate, 'yyyy.mm.dd') writeDate, "
					   + "to_char(updateDate, 'yyyy.mm.dd') updateDate, hit "
					   + "from festa "
					   + "where no = ?";
			
			System.out.println("상세보기 : " + sql);
			
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			pstmt.setLong(idx++, fvo.getNo());
			pstmt.setString(idx++, fvo.getId());
//			pstmt.setLong(idx++, fvo.getNo());
//			pstmt.setString(idx++, fvo.getId());
			pstmt.setLong(idx++, fvo.getNo());

			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				vo = new FestaVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setPhoto(rs.getString("photo"));
				vo.setAddress(rs.getString("address"));
				vo.setRgn(rs.getString("rgn"));
				vo.setTel(rs.getString("tel"));
				vo.setWebsite(rs.getString("website"));
				vo.setHost(rs.getString("host"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setUpdateDate(rs.getString("updateDate"));
				vo.setHit(rs.getLong("hit"));
				vo.setMylike(rs.getString("myLiked"));
//				vo.setReply(rs.getString("reply"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 정보 상세보기 중 DB 오류");
			}finally {
				 DBInfo.close(con, pstmt, rs);
			}
		return vo;
	}//view끝
	
// 축제 등록 --------------------------------------------------------------------------------
	public int write(FestaVO vo) throws Exception {
		int result = 0;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "insert into festa(no, title, content, photo, address, rgn, tel, website, host, startDate, endDate) "
					   + "values(festa_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPhoto());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getRgn());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getWebsite());
			pstmt.setString(8, vo.getHost());
			pstmt.setString(9, vo.getStartDate());
			pstmt.setString(10, vo.getEndDate());			
			
			pstmt.executeUpdate();

			System.out.println("[축제 정보 등록 완료]");
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 정보 등록 중 DB 오류");
			}finally {
				 DBInfo.close(con, pstmt);
			}
		return result;
	}//write끝
	
// 축제 수정 --------------------------------------------------------------------------------
	public int update(FestaVO vo) throws Exception {
		int result = 0;

		try {
			con = DBInfo.getConnection();
			
			String sql = "update festa set title = ?, content = ?, address = ?, rgn = ?, tel = ?, website = ?, host = ?, startDate = ?, endDate = ? where no = ?";

			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getRgn());
			pstmt.setString(5, vo.getTel());
			pstmt.setString(6, vo.getWebsite());
			pstmt.setString(7, vo.getHost());
			pstmt.setString(8, vo.getStartDate());
			pstmt.setString(9, vo.getEndDate());
			pstmt.setLong(10, vo.getNo());
			
			result = pstmt.executeUpdate();

			if(result == 0) { System.out.println("[수정할 데이터가 존재하지 않습니다]");
			}else {System.out.println("[수정이 완료되었습니다]");	}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 정보 수정 중 DB 오류");
			}finally {
				 DBInfo.close(con, pstmt);
			}
		return result;
	}//update끝
	
// 축제 이미지 단독 수정 ---------------------------------------------------------------------------
	public Integer updatePhoto(FestaVO vo) throws Exception {
		int result = 0;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "update festa set photo = ? where no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getPhoto());
			pstmt.setLong(2, vo.getNo());
			
			result = pstmt.executeUpdate();
			
			if(result == 0) { System.out.println("[수정할 데이터가 존재하지 않습니다]");
			}else {System.out.println("[수정이 완료되었습니다]");	}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 이미지 정보 수정 중 DB 오류");
		}finally {
			DBInfo.close(con, pstmt);
		}
		return result;
	}//updatePhoto끝
	
// 축제 삭제 --------------------------------------------------------------------------------
	public int delete(FestaVO vo) throws Exception {
		int result = 0;

			try {
				con = DBInfo.getConnection();
				
				String sql = "delete from festa where no = ?";

				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, vo.getNo());
				
				result = pstmt.executeUpdate();

				if(result == 0) { System.out.println("[삭제할 번호가 존재하지 않습니다]");
				}else {System.out.println("[삭제가 완료되었습니다]");	}
					
				}catch (Exception e) {
					e.printStackTrace();
					throw new Exception("축제 정보 삭제 중 DB 오류");
					}finally {
						DBInfo.close(con, pstmt);						
					}		 	
		return result;
	}//delete끝
	
// 축제 북마크 ------------------------------------------------------------------------------
	public int like(FestaVO vo) throws Exception{
		int result = 0;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "insert into festa_like(no, id) values(?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			
			result = pstmt.executeUpdate();

			if (result == 0) throw new Exception("[북마크 처리가 완료된 데이터입니다]");
			else System.out.println("[북마크 처리가 완료되었습니다]");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 북마크 처리 중 DB 오류 or 이미 처리된 데이터");
			}finally {
				DBInfo.close(con, pstmt);						
			}		
		return result;
	}//like끝

// 축제 북마크 취소 ------------------------------------------------------------------------------
	public int likeCancel(FestaVO vo) throws Exception{
		int result = 0;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "delete from festa_like where no = ? and id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			
			result = pstmt.executeUpdate();

			if (result == 0) throw new Exception("[정보를 다시 확인해주세요]");
			else System.out.println("[북마크 취소가 완료되었습니다]");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 북마크 취소 처리 중 DB 오류 or 정보를 다시 확인해주세요");
			}finally {
				DBInfo.close(con, pstmt);						
			}		
		return result;
	}//likeCancel끝

// 축제 리뷰 전체 데이터 가져오기 -----------------------------------------------------------------------------
	public long getRepTotalRow(Long no) throws Exception {
		
		long cnt = 0;
		
		try {
			
			con = DBInfo.getConnection();
			
			String sql = "select count(*) from festa_rep where no = ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs != null && rs.next()) {
				cnt = rs.getLong(1);
				System.out.println("[ 축제 리뷰 데이터 전체 개수 = " + cnt + "개 ]");
			} else
				System.out.println("데이터가 존재하지 않습니다");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 리뷰 리스트 전체 데이터 개수 가져오기 중 DB 오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
		}	
		return cnt;
	}//getRepTotalRow끝	
	
// 축제 리뷰 리스트 -------------------------------------------------------------------------------
	public List<FestaRepVO> replist(Long no, PageObject pageObject) throws Exception {
		System.out.println("FestaRepDAO.list().no + pageobject = " + " [ " +  no + " ] " + pageObject);
		List<FestaRepVO> replist = null;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "select fr.rno, fr.no, fr.content, fr.id, fr.writeDate from festa_rep fr, festa f where (fr.no = ?) and (fr.no = f.no) order by rno desc";
			sql = "select rownum rnum, rno, no, content, id, writeDate from( " + sql + ") ";
			sql = "select rnum, rno, no, content, id, to_char(writeDate, 'yyyy.mm.dd') writeDate from( " + sql + ") where rnum between ? and ?";
			
			System.out.println("FestaDAO.replist().SQL = " + sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.setLong(2, 1);
			pstmt.setLong(3, 10);
			
			rs = pstmt.executeQuery();
			
			if(rs != null) {
				while(rs.next()) {
					if (replist == null) replist = new ArrayList<>();
					FestaRepVO vo = new FestaRepVO();
					vo.setRno(rs.getLong("rno"));
					vo.setNo(rs.getLong("no"));
					vo.setContent(rs.getString("content"));
					vo.setId(rs.getString("id"));
					vo.setWriteDate(rs.getString("writeDate"));
					replist.add(vo);
//				System.out.println("FestaDAO.replist().vo = " + vo);
				}
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 리뷰 리스트 데이터 가져오기 중 DB 오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
		}
		return replist;
	}//replist끝

// 축제 리뷰 등록 --------------------------------------------------------------------------------
	public int repwrite(FestaRepVO vo) throws Exception {
		int result = 0;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "insert into festa_rep(rno, no, content, id) "
					   + "values(festa_seq.nextval, ?, ?, ?) ";
			
			System.out.println("FestaDAO.repwrite().sql = " + sql);
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			
			pstmt.executeUpdate();

			System.out.println("[축제 리뷰 등록 완료]");
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("축제 정보 등록 중 DB 오류");
			}finally {
				 DBInfo.close(con, pstmt);
			}
		return result;
	}//repwrite끝
	
// 축제 리뷰 수정 --------------------------------------------------------------------------------
	public int repupdate(FestaRepVO vo) throws Exception {
		int result = 0;
//
//		try {
//			con = DBInfo.getConnection();
//			
//			String sql = "update festa_rep set content = ?, writeDate = ? where rno = ?";
//
//			pstmt = con.prepareStatement(sql);
//			
//			pstmt.setString(1, vo.getContent());
//			pstmt.setString(2, vo.getWriteDate());
//			pstmt.setLong(3, vo.getRno());
//			
//			result = pstmt.executeUpdate();
//
//			if(result == 0) { System.out.println("[수정할 데이터가 존재하지 않습니다]");
//			}else {System.out.println("[수정이 완료되었습니다]");	}
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception("축제 리뷰 수정 중 DB 오류");
//			}finally {
//				 DBInfo.close(con, pstmt);
//			}
		return result;
	}//repupdate끝
	
// 축제 리뷰 삭제 --------------------------------------------------------------------------------
	public int repdelete(FestaRepVO vo) throws Exception {
		int result = 0;

			try {
				con = DBInfo.getConnection();
				
				String sql = "delete from festa_rep where rno = ? and no = ? and id = ? ";

				pstmt = con.prepareStatement(sql);
				pstmt.setLong(1, vo.getRno());
				pstmt.setLong(2, vo.getNo());
				pstmt.setString(3, vo.getId());
				
				result = pstmt.executeUpdate();

				if(result == 0) { System.out.println("[삭제할 리뷰 번호가 존재하지 않습니다]");
				}else {System.out.println("[리뷰 삭제가 완료되었습니다]");	}
					
				}catch (Exception e) {
					e.printStackTrace();
					throw new Exception("축제 리뷰 삭제 중 DB 오류");
					}finally {
						DBInfo.close(con, pstmt);						
					}		 	
		return result;
	}//repdelete끝
	
	
}// 전체 끝
