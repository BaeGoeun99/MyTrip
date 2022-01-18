package com.mytrip.festa.vo;

/** <h2>축제 정보 VO</h2> 
 * 번호, 제목, 내용, 사진, 주소, 지역, 연락처, 홈페이지, 주최, 시작일, 종료일, 수정일, 작성일, 조회수, 북마크
 * @author GOEUN
**/


public class FestaVO {
	
	private long no;
	private String title, content, photo, address, rgn, tel, website, host, startDate, endDate, updateDate, writeDate, id, mylike, reply;
	private long hit, likecnt;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRgn() {
		return rgn;
	}
	public void setRgn(String rgn) {
		this.rgn = rgn;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMylike() {
		return mylike;
	}
	public void setMylike(String mylike) {
		this.mylike = mylike;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	public long getLikecnt() {
		return likecnt;
	}
	public void setLikecnt(long likecnt) {
		this.likecnt = likecnt;
	}	
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	@Override
	public String toString() {
		return "FestaVO [no=" + no + ", title=" + title + ", content=" + content + ", photo=" + photo + ", address="
				+ address + ", rgn=" + rgn + ", tel=" + tel + ", website=" + website + ", host=" + host + ", startDate="
				+ startDate + ", endDate=" + endDate + ", updateDate=" + updateDate + ", writeDate=" + writeDate
				+ ", id=" + id + ", mylike=" + mylike + ", reply=" + reply + ", hit=" + hit + ", likecnt=" + likecnt
				+ "]";
	}
	
}//끝
