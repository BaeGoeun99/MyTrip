package com.mytrip.member.vo;
public class MemberVO {

	private String id;
	private String pw;
	private String name;
	private String gender;
	private String birth;
	private String tel;
	private String email;
	private String region;
	private String regDate;
	private String conDate;
	private String status;
	private String photo;
	private Long gradeNo;
	private String gradeName;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getConDate() {
		return conDate;
	}
	public void setConDate(String conDate) {
		this.conDate = conDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Long getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(Long gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", birth=" + birth
				+ ", tel=" + tel + ", email=" + email + ", region=" + region + ", regDate=" + regDate + ", conDate="
				+ conDate + ", status=" + status + ", photo=" + photo + ", gradeNo=" + gradeNo + ", gradeName="
				+ gradeName + "]";
	}
	
	
	
	
}
