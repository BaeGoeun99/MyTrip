package com.mytrip.member.dao;

import com.mytrip.member.vo.LoginVO;
import com.mytrip.util.db.DAO;
import com.mytrip.util.db.DBInfo;

public class LoginDAO extends DAO {

	public LoginVO login(LoginVO inVO) throws Exception{
		LoginVO vo = null;
		
		//DB에서 데이터를 꺼내서 vo에 채운다.
		try {
			// 1. 2. DBInfo
			con = DBInfo.getConnection();
			// 3. sql - 아이디,이름, 등급번호, 등급명, 사진
			String sql = "select m.id, m.name, m.gradeNo, g.gradeName, m.photo "
					+ " from member m, grade g "
					+ " where (id = ? and pw = ?) and (g.gradeNo = m.gradeNo) ";
			// 4. 실행객체 & 데이터
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inVO.getId());
			pstmt.setString(2, inVO.getPw());
			
			// 5.  실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 담기
			if(rs != null && rs.next()) {
				vo = new LoginVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
				vo.setPhoto(rs.getString("photo"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//7.닫기
			DBInfo.close(con, pstmt, rs);
		}
		
		if(vo == null) // 데이터를 못가져왔다. = 아이디나 비밀번호가 틀렸다.
			throw new Exception("500::로그인 정보를 확인하고 다시 실행해 주세요.");
		
		return vo;
	}
	
}
