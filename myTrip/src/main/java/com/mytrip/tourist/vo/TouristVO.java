package com.mytrip.tourist.vo;

/**
 * <h2>이미지 게시판 데이터를 저장하는 객체</h2>
 * 
 * 번호, 제목, 내용, 작성자 ID, 작성자 이름, 작성일, 파일명, 내 좋아요, 좋아요 개수<p>
 * 데이터를 가져가는 getter()<br>
 * 데이터를 저장하는 setter()<br>
 * 
 * 데이터 내용을 확인하는 toString()<br>
 * 
 * @author EZEN
 *
 */

public class TouristVO {

	private long no;
	private String name, address, tel, website, mapImage, touristImage, information, report, serviceHours, fee, characteristics,
	objective, requiredTime, difficultyLevel, amenity;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getMapImage() {
		return mapImage;
	}
	public void setMapImage(String mapImage) {
		this.mapImage = mapImage;
	}
	public String getTouristImage() {
		return touristImage;
	}
	public void setTouristImage(String touristImage) {
		this.touristImage = touristImage;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getServiceHours() {
		return serviceHours;
	}
	public void setServiceHours(String serviceHours) {
		this.serviceHours = serviceHours;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getRequiredTime() {
		return requiredTime;
	}
	public void setRequiredTime(String requiredTime) {
		this.requiredTime = requiredTime;
	}
	public String getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	public String getAmenity() {
		return amenity;
	}
	public void setAmenity(String amenity) {
		this.amenity = amenity;
	}
	@Override
	public String toString() {
		return "TouristVO [no=" + no + ", name=" + name + ", address=" + address + ", tel=" + tel + ", website="
				+ website + ", mapImage=" + mapImage + ", touristImage=" + touristImage + ", information=" + information
				+ ", report=" + report + ", serviceHours=" + serviceHours + ", fee=" + fee + ", characteristics="
				+ characteristics + ", objective=" + objective + ", requiredTime=" + requiredTime + ", difficultyLevel="
				+ difficultyLevel + ", amenity=" + amenity + "]";
	}
	

}


