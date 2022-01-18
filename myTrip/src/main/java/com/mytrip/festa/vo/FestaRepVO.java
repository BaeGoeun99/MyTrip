package com.mytrip.festa.vo;

/** <h2>축제 리뷰 정보 VO</h2> 
 * 리뷰번호, 축제번호, 내용, 아이디, 작성일
 * @author GOEUN
**/
public class FestaRepVO {
	
	private long rno;
	private long no;
	private String content, id, writeDate;
	
	public long getRno() {
		return rno;
	}
	public void setRno(long rno) {
		this.rno = rno;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
//	public static String getId(HttpServletRequest request) throws Exception {
//		FestaRepVO vo = (FestaRepVO)request.getSession().getAttribute("festaRepId");
//		if(vo == null) return null;
//		return vo.getId();
//	}
	
	
	@Override
	public String toString() {
		return "FestaRepVO [rno=" + rno + ", no=" + no + ", content=" + content + ", id=" + id + ", writeDate="
				+ writeDate + "]";
	}	
	
}
