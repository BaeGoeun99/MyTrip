package com.anitop.member.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

/** @author 배고은 **/

@Data
public class MemberVO {
	
	private String id;
	private String pw;
	private String name;
	private String gender;
	// 날짜 형식의 데이터를 입력하는 곳에 어떤 형식의 날짜 문자열이 들어오는지 정해주는 어노테이션
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date birth;
	private String tel;
	private String email;
	private Date regDate;
	private Date conDate;
	private String status;
	private String photo;
	private String gradeName; // grade table에서 join
	private int gradeNo;
	
	// 이미지 데이터를 받는 변수 선언
	private MultipartFile imageFile;
	
	// 이미지를 수정하거나 삭제 시 지워질 파일 정보
	private String deleteFileName;
}
