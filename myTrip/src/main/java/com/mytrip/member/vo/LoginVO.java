package com.mytrip.member.vo;

import javax.servlet.http.HttpServletRequest;

/**
 * 로그인 정보를 저장하는 객체
 * 아이디, 비밀번호, 이름, 등급번호, 등급명, 사진
 */
public class LoginVO {

	// 필드선언 = 변수
	private String id;
	private String pw;
	private String name;
	private int gradeNo;
	private String gradeName;
	private String photo;
	
	// getter, setter - 데이터 읽기 쓰기
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	// request에서 로그인한 정보 중에서 아이디를 꺼내서 전달해 주는 static 메서드
	public static String getId(HttpServletRequest request) throws Exception{
		LoginVO vo = (LoginVO)request.getSession().getAttribute("login");
		// 로그인이 되지 않은 경우
//		if (vo == null) throw new Exception("로그인이 되어 있지 않습니다.");
		if (vo == null) return null;
		return vo.getId();
	}
	
	
	// 데이터 확인용
	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", pw=" + pw + ", name=" + name + ", gradeNo=" + gradeNo + ", gradeName="
				+ gradeName + ", photo=" + photo + "]";
	}
	
}
