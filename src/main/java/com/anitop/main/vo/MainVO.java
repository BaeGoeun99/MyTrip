package com.anitop.main.vo;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

/** @author 배고은 **/

@Data
public class MainVO {
	
	private long no;
	private String title;
	private String content;
	private String id;
	private String name; // member table에서 조인해서 가져오기
	private String fileName;
	private String vod;
	private String launchDate;
	private String company;
	private String author;
	private Date writeDate;
	
	// 이미지 데이터를 받는 변수 선언
	private MultipartFile imageFile;
	
	// 이미지를 수정하거나 삭제 시 지워질 파일 정보
	private String deleteFileName;
}
