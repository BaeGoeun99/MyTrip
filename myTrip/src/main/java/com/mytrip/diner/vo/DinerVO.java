package com.mytrip.diner.vo;

public class DinerVO {
	
	private long no;
	private int area, kind;
	private String name, address, tel, mainImage, introduce, content, subImage, tag, openTime, restDay;
	private String areaName, kindName, id, myLiked, myFav;
	private int LikeCnt, FavCnt;
	
	public String getMyLiked() {
		return myLiked;
	}
	public void setMyLiked(String myLiked) {
		this.myLiked = myLiked;
	}
	public String getMyFav() {
		return myFav;
	}
	public void setMyFav(String myFav) {
		this.myFav = myFav;
	}
	public int getLikeCnt() {
		return LikeCnt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setLikeCnt(int likeCnt) {
		LikeCnt = likeCnt;
	}
	public int getFavCnt() {
		return FavCnt;
	}
	public void setFavCnt(int favCnt) {
		FavCnt = favCnt;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getKindName() {
		return kindName;
	}
	public void setKindName(String kindName) {
		this.kindName = kindName;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubImage() {
		return subImage;
	}
	public void setSubImage(String subImage) {
		this.subImage = subImage;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getRestDay() {
		return restDay;
	}
	public void setRestDay(String restDay) {
		this.restDay = restDay;
	}
	@Override
	public String toString() {
		return "DinerVO [no=" + no + ", area=" + area + ", kind=" + kind + ", name=" + name + ", address=" + address
				+ ", tel=" + tel + ", mainImage=" + mainImage + ", introduce=" + introduce + ", content=" + content
				+ ", subImage=" + subImage + ", tag=" + tag + ", openTime=" + openTime + ", restDay=" + restDay
				+ ", areaName=" + areaName + ", kindName=" + kindName + ", id=" + id + ", myLiked=" + myLiked
				+ ", myFav=" + myFav + ", LikeCnt=" + LikeCnt + ", FavCnt=" + FavCnt + "]";
	}
	


}	