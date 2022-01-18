package com.mytrip.member.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mytrip.member.vo.MemberVO;
import com.mytrip.util.db.DAO;
import com.mytrip.util.db.DBInfo;
import com.webjjang.util.PageObject;

public class MemberDAO extends DAO {

	// 1. 전체 데이터 가져오기
	public long getTotalRow(PageObject pageObject) throws Exception {

		System.out.println(this.getClass().getSimpleName() + "." + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
		System.out.println(this.getClass().getEnclosingMethod());
		System.out.println(new Object() {
		}.getClass().getEnclosingMethod());
		long cnt = 0;

		try {

			con = DBInfo.getConnection();

			String sql = "select count(*) from member ";
			if (pageObject.getWord() != null && !pageObject.getWord().equals(""))
				sql += search(pageObject);
			System.out.println("MemberDAO.getTotalRow().sql - " + sql);

			pstmt = con.prepareStatement(sql);
			int idx = 1;
			idx = searchSetData(pageObject, pstmt, idx);

			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				cnt = rs.getLong(1);
				System.out.println("멤버 리스트의 전채 개수 : " + cnt);
			} else
				System.out.println("데이터가 존재하지 않습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("500::멤버 리스트 가져오기 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}

		return cnt;
	}

	// 1-1.리스트 DAO
	public List<MemberVO> list(PageObject pageObject) throws Exception {
//			System.out.println("MemberDAO.list()");
		List<MemberVO> list = null;

		try {// 정상처리
			con = DBInfo.getConnection();
			String sql = " select id, name, gender, birth, region, status, gradeNo from member " + search(pageObject)
					+ " order by id asc ";
			sql = " select rownum rnum, id, name, gender, birth, region, status, gradeNo from( " + sql + ") ";
			sql = " select rnum, id, name, gender, to_char(birth,'yyyy-mm-dd') birth, "
					+ " region, status, gradeNo from (" + sql + " ) where rnum between ? and ?";
			System.out.println("MemberDAO.list().sql - " + sql);
			pstmt = con.prepareStatement(sql);
			int idx = 1;
			idx = searchSetData(pageObject, pstmt, idx);
			pstmt.setLong(idx++, pageObject.getStartRow());
			pstmt.setLong(idx++, pageObject.getEndRow());
			System.out.println("MemberDAO.list() - 실행 객체 생성 완료.");
			rs = pstmt.executeQuery();
			System.out.println("MemberDAO.list() - 실행 완료.");
			if (rs != null) {
				while (rs.next()) {
					if (list == null)
						list = new ArrayList<MemberVO>();
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setBirth(rs.getString("birth"));
					vo.setRegion(rs.getString("region"));
					vo.setStatus(rs.getString("status"));
					vo.setGradeNo(rs.getLong("gradeNo"));

					list.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("멤버 리스트 가져오는 중 DB 오류");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}

		return list;
	}

//

	// id 중복 체크 메서드
	public String idCheck(String id) throws Exception {
		String ck = null;

		try {
			con = DBInfo.getConnection();
			String sql = " select id from member where id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs != null && rs.next())
				ck = rs.getString("id");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("아이디 중복 체크 db 오류 발생");
		} finally {
			// 7. 닫기
			DBInfo.close(con, pstmt, rs);
		}

		return ck;
	}

	// 회원 가입
	public int write(MemberVO vo) throws Exception {
		System.out.println("MemberDAO.write().vo : " + vo);
		Integer result = 0;

		try {
			con = DBInfo.getConnection();
			String sql = " insert into member(id, pw, name, gender, birth, tel, email, region, photo) "
					+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getRegion());
			pstmt.setString(9, vo.getPhoto());
			result = pstmt.executeUpdate();
			System.out.println("회원가입이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("회원 가입 DB 처리 오류");
		} finally {
			DBInfo.close(con, pstmt);
		}

		return result;
	}

	// 나중에 위에서부터 바꿔서 넣을것 7-2 BoardDAO 참고
	// 1-2. 검색에 대한 문자열을 붙이는 메서드 -- 만약에 word가 있는 경우만 조건을 붙인다.
	private String search(PageObject pageObject) {
		String condition = "";

		if (pageObject.getWord() != null && !pageObject.getWord().equals("")) {
			condition += " where 1 = 0 ";
			if (pageObject.getKey().indexOf("i") != -1)
				condition += " or id like ? ";
			if (pageObject.getKey().indexOf("n") != -1)
				condition += " or name like ? ";
			if (pageObject.getKey().indexOf("g") != -1)
				condition += " or gender like ? ";
			if (pageObject.getKey().indexOf("b") != -1)
				condition += " or birth like ? ";
			if (pageObject.getKey().indexOf("r") != -1)
				condition += " or region like ? ";
			if (pageObject.getKey().indexOf("st") != -1)
				condition += " or status like ? ";
			if (pageObject.getKey().indexOf("gn") != -1)
				condition += " or gradeNo like ? ";
		}

		return condition;
	}

	// 1-2. 검색에 대한 문자열을 붙이는 메서드 -- 만약에 word가 있는 경우만 조건을 붙인다.
	private int searchSetData(PageObject pageObject, PreparedStatement pstmt, int idx) throws SQLException {

		String word = pageObject.getWord();

		if (word != null && !word.equals("")) {
			if (pageObject.getKey().indexOf("i") != -1)
				pstmt.setString(idx++, "%" + word + "%");
			if (pageObject.getKey().indexOf("n") != -1)
				pstmt.setString(idx++, "%" + word + "%");
			if (pageObject.getKey().indexOf("g") != -1)
				pstmt.setString(idx++, "%" + word + "%");
			if (pageObject.getKey().indexOf("b") != -1)
				pstmt.setString(idx++, "%" + word + "%");
			if (pageObject.getKey().indexOf("r") != -1)
				pstmt.setString(idx++, "%" + word + "%");
			if (pageObject.getKey().indexOf("st") != -1)
				pstmt.setString(idx++, "%" + word + "%");
			if (pageObject.getKey().indexOf("gn") != -1)
				pstmt.setString(idx++, "%" + word + "%");
		}

		return idx;
	}

	// 회원 정보보기 메서드
	public MemberVO view(MemberVO id) throws Exception {
		MemberVO vo = null;

		System.out.println("뷰 서비스 DAO 출력");
		try {
			con = DBInfo.getConnection();
			String sql = " select m.id, m.name, m.gender, m.birth, m.tel, " 
					+ " m.email, m.region, m.photo, "
					+ " to_char(m.regDate,'yyyy.mm.dd') regDate, " 
					+ " to_char(m.conDate,'yyyy.mm.dd') conDate, " 
					+ " m.status, m.gradeNo, g.gradeName " 
					+ " from member m, grade g " 
					+ " where id = ?";
			
//			System.out.println("뷰 sql"+sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id.getId());

//			System.out.println("뷰 pstmt");
			rs = pstmt.executeQuery();

//			System.out.println("뷰 쿼리 실행");
			if (rs != null && rs.next()) {
				vo = new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setRegion(rs.getString("region"));
				vo.setPhoto(rs.getString("photo"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setConDate(rs.getString("conDate"));
				vo.setStatus(rs.getString("status"));
				vo.setGradeNo(rs.getLong("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));

				System.out.println("dao 끝");
				System.out.println("dao 끝"+vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("회원 정보 보기 처리 중 오류 발생");
		} finally {
			DBInfo.close(con, pstmt, rs);
		}

		return vo;
	}
	// 회원 정보보기 메서드
		public MemberVO myView(MemberVO id) throws Exception {
			MemberVO vo = null;

			System.out.println("뷰 서비스 DAO 출력");
			try {
				con = DBInfo.getConnection();
				String sql = " select m.id, m.name, m.gender, m.birth, m.tel, " 
						+ " m.email, m.region, m.photo, "
						+ " to_char(m.regDate,'yyyy.mm.dd') regDate, "
						+ " to_char(m.conDate,'yyyy.mm.dd') conDate, " 
						+ " m.status, m.gradeNo, g.gradeName " 
						+ " from member m, grade g "
						+ " where (id = ?) and (m.gradeNo = g.gradeNo) "; 
						
//				System.out.println("뷰 sql"+sql);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id.getId());

//				System.out.println("뷰 pstmt");
				rs = pstmt.executeQuery();

//				System.out.println("뷰 쿼리 실행");
				if (rs != null && rs.next()) {
					vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setGender(rs.getString("gender"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setEmail(rs.getString("email"));
					vo.setRegion(rs.getString("region"));
					vo.setPhoto(rs.getString("photo"));
					vo.setRegDate(rs.getString("regDate"));
					vo.setConDate(rs.getString("conDate"));
					vo.setStatus(rs.getString("status"));
					vo.setGradeNo(rs.getLong("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));

					System.out.println("dao 끝");
					System.out.println("dao 끝"+vo);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("회원 정보 보기 처리 중 오류 발생");
			} finally {
				DBInfo.close(con, pstmt, rs);
			}

			return vo;
		}
		// 회원 탈퇴 - 자신이 직접
		public int delete(MemberVO vo) throws Exception {
			int result = 0;

			try {
				con = DBInfo.getConnection();

				String sql = " delete from member where id = ? ";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getId());

				result = pstmt.executeUpdate();

				if (result == 0)
					throw new Exception("정보를 확인해주세요");
				else
					System.out.println("회원 탈퇴 완료");

			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("회원 삭제 중 오류 발생");
			} finally {
				DBInfo.close(con, pstmt);
			}

			return result;
		}

		// 회원 탈퇴 - 관리자가 삭제
		public int Admindelete(MemberVO vo) throws Exception {
			int result = 0;

			try {
				con = DBInfo.getConnection();

				String sql = " delete from member where (id = ?) and (gradeNo=9)";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getId());

				result = pstmt.executeUpdate();

				if (result == 0)
					throw new Exception("정보를 확인해주세요");
				else
					System.out.println("회원 탈퇴 완료");

			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("회원 삭제 중 오류 발생");
			} finally {
				DBInfo.close(con, pstmt);
			}

			return result;
		}

		// 회원정보 수정
		public int update(MemberVO vo) throws Exception {
			System.out.println("MemberDAO.update().vo - " + vo);
			int result = 0;
			try {
				con = DBInfo.getConnection();
				String sql = "update member set pw = ? ,tel = ? , email =? , region = ? where id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getPw());
				pstmt.setString(2, vo.getTel());
				pstmt.setString(3, vo.getEmail());
				pstmt.setString(4, vo.getRegion());
				pstmt.setString(5, vo.getId());
				result = pstmt.executeUpdate();
				if (result == 0)
					throw new Exception("회원 정보 수정을 실패하였습니다.");
				else
					System.out.println("회원 정보 수정이 완료되었습니다.");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("회원 정보 수정 - 정보 수정 중 DB 오류");
			} finally {
				// 7. 닫기
				DBInfo.close(con, pstmt);
			}

			return result;
		}
		
	// 회원 리스트
//	public List<MemberVO> list() throws Exception {
//		System.out.println("MemberDAO.list()");
//		List<MemberVO> list = null;
//
//		try {
//			// 1. 2.
//			con = DBInfo.getConnection();
//			// 3. sql
//			String sql = " select m.id, m.name, m.gender, m.birth, m.tel, m.gradeNo, g.gradeName, m.status "
//					+ " from member m, grade g " + " where g.gradeNo = m.gradeNo" + " order by id asc ";
//			sql = "select rownum rnum,  id, name, gender, birth, tel, gradeNo, gradeName, status from( " + sql + ")";
//			sql = "select rnum,  id, name, gender, to_char(birth,'yyyy-mm-dd')birth, tel, gradeNo, gradeName, status from( "
//					+ sql + ") where rnum between ? and ? ";
//			// 4. 실행객체 & 데이터 셋팅
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, 1);
//			pstmt.setInt(2, 10);
//			// 5. 실행
//			rs = pstmt.executeQuery();
//			// 6. 데이터 저장
//			if (rs != null) {
//
//				while (rs.next()) {
//					if (list == null)
//						list = new ArrayList<MemberVO>();
//					MemberVO vo = new MemberVO();
//					vo.setId(rs.getString("id"));
//					vo.setName(rs.getString("name"));
//					vo.setGender(rs.getString("gender"));
//					vo.setBirth(rs.getString("birth"));
//					vo.setTel(rs.getString("tel"));
//					vo.setGradeNo(rs.getInt("gradeNo"));
//					vo.setGradeName(rs.getString("gradeName"));
//					vo.setStatus(rs.getString("status"));
//
//					list.add(vo);
//				}
//
//			} // if의 끝
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			throw new Exception("회원 리스트 DB 처리 오류");
//		} finally {
//			DBInfo.close(con, pstmt, rs);
//		}
//
//		return list;
//	}

}
