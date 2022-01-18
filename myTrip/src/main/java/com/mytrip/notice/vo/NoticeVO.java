package com.mytrip.notice.vo;

/**
 * <h2>게시판 데이터를 저장하는 객체</h2>
 * 
 * 번호, 제목, 내용, 작성자, 작성일, 조회수<p>
 * 데이터를 가져가는 getter()<br>
 * 데이터를 저장하는 setter()<br>
 * 
 * 데이터 내용을 확인하는 toString()<br>
 * 
 * @author EZEN
 *
 */

public class NoticeVO {

	private long no;
	private String title, content, writeDate, open;
	private long  hit;
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
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "NoticeVO [no=" + no + ", title=" + title + ", content=" + content + ", writeDate=" + writeDate
				+ ", open=" + open + ", hit=" + hit + "]";
	}
	
	
}