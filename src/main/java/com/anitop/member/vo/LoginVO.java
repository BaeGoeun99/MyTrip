package com.anitop.member.vo;

import javax.servlet.http.HttpServletRequest;
import lombok.Data;

/** @author 배고은 **/

@Data
public class LoginVO {
	
	// 아이디, 비밀번호, 이름, 등급번호, 등급명, 사진
	private String id;
	private String pw;
	private String name;
	private String photo;
	private String gradeName;
	private int gradeNo;
	
	// request에서 로그인한 정보 중에서 아이디를 꺼내서 전달해 주는 메서드
	public static String getId(HttpServletRequest request) throws Exception{
		LoginVO vo = (LoginVO)request.getSession().getAttribute("login");
		// 로그인이 되지 않은 경우
		if (vo == null) return null;
		return vo.getId();
	}
}
