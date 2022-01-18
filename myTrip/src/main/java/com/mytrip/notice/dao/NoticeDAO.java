package com.mytrip.notice.dao;

import java.util.ArrayList;
import java.util.List;


import com.mytrip.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;
import com.mytrip.util.db.DAO;
import com.mytrip.util.db.DBInfo;

public class NoticeDAO extends DAO {

	// 1. 전체 데이터 가져오기
	public long getTotalRow(PageObject pageObject) throws Exception {
		
		System.out.println(this.getClass().getSimpleName() + "." 
		+ new Object(){}.getClass().getEnclosingMethod().getName() + "()");
		System.out.println(this.getClass().getEnclosingMethod());
		System.out.println(new Object(){}.getClass().getEnclosingMethod());
		long cnt = 0;
		
		try { 
			con = DBInfo.getConnection();

			String sql = "select count(*) from notice";
			System.out.println("NoticeDAO.getTotalRow().sql - " + sql);

			pstmt = con.prepareStatement(sql);
			// 5. 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 표시 또는 저장
			if(rs != null && rs.next()) {
				cnt = rs.getLong(1);
				System.out.println("공지사항의 전체 글의 개수 : " + cnt);
			} else System.out.println("데이터가 존재하지 않습니다.");
		}catch (Exception e) { 
			e.printStackTrace(); 
			throw new Exception("공지사항 리스트 - 전체 데이터 개수 가져오기 DB 오류");
		} finally {
			
			DBInfo.close(con, pstmt, rs);
		} 
		
		return cnt;
	}
	
	// 1-1.리스트 - MainController - NoticeController - NoticeListService - NoticeDAO
	public List<NoticeVO> list(PageObject pageObject) throws Exception{
		System.out.println("NoticeDAO.list()");
		List<NoticeVO> list = null;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = " select no, title, writeDate, hit, open from notice "
					+ "order by no desc  ";
			sql = " select rownum rnum, no, title, writeDate, hit, open from( " + sql + ") ";
			sql = " select rnum, no, title, "
					+ " to_char(writeDate, 'yyyy.mm.dd') writeDate, hit, open from (" + sql
					+ ") where rnum between ? and ?";
			System.out.println("NoticeDAO.list().sql - " + sql);
			// 4. 실행 객체 & 데이터 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, pageObject.getStartRow()); 
			pstmt.setLong(2, pageObject.getEndRow()); 
			
			rs = pstmt.executeQuery();
			
			if(rs != null) {
			
				while(rs.next()) {
				
					if(list == null) list = new ArrayList<NoticeVO>();
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					vo.setOpen(rs.getString("open"));
					
					
					list.add(vo);
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
			throw new Exception("공지사항 리스트 데이터 가져오는 중 DB 오류");
		} finally {
			
			DBInfo.close(con, pstmt, rs);
		}
	
		System.out.println("NoticeDAO.list().list - " + list);
		
		return list;
	
	
	
	
		
		
	}
	// 조회수 증가
	public int increase(long no) throws Exception {
		
		int result = 0;
		
		
		try {
			
			con = DBInfo.getConnection();
			
			String sql = "update notice set hit = hit + 1 where no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);

			result = pstmt.executeUpdate();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); // 오류 출력
			throw new Exception("공지사항 글보기 - 조회수 1증가 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt);
		}
		
		
		return result;
	}
	
	// 글보기
	public NoticeVO view(long no) throws Exception {
		NoticeVO vo = null;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "select no, title, content, to_char(writeDate, 'yyyy.mm.dd') writeDate, "
					+ " hit, open from notice where no = ? ";
			
			System.out.println("NoticeDAO.view().sql - " + sql);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				vo = new NoticeVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
				vo.setOpen(rs.getString("open"));
			}
		
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지사항 글보기 - 데이터 가져오는 중 DB오류");
		}finally {
			DBInfo.close(con, pstmt, rs);
			
		}
		return vo;
	}

	// 글쓰기
	public int write(NoticeVO vo) throws Exception {
		int result = 0;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "insert into notice(no, title, content, open) "
					+ "values(notice_seq.nextval, ?, ?, ? )";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getOpen());
			
			result = pstmt.executeUpdate();
			
			System.out.println("공지사항 글등록이 완료되었습니다");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("공지사항 글쓰기 - 글쓰기 중 DB 오류");
		}finally {
			DBInfo.close(con, pstmt);
			
		}
		
		
		return result;
	}

	// 글수정
	public int update(NoticeVO vo) throws Exception {
		int result = 0;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "update notice set title =?, content = ?, open = ? where no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getOpen());
			pstmt.setLong(4, vo.getNo());
			
			result = pstmt.executeUpdate();
			
			if(result == 0) System.out.println("수정할 데이터가 존재하지 않습니다");
			else System.out.println("데이터 수정이 완료되었습니다");
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception("공지사항 글수정 - 글수정 중 DB 오류");
		}finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
	}

	
	// 글삭제
	public int delete(long no) throws Exception{
		int result = 0;
		
		try {
			con = DBInfo.getConnection();
			
			String sql = "delete from notice where no = ?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			result = pstmt.executeUpdate();
			
			if(result == 0) System.out.println("정보를 확인해 주세요");
			else System.out.println("데이터가 삭제 되었습니다");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("게시판 글삭제 - 글삭제 중 DB오류");
		}finally {
			DBInfo.close(con, pstmt);
		}
		
		return result;
		
	}
	
	
}
