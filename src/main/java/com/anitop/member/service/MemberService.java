package com.anitop.member.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.anitop.member.vo.LoginVO;
import com.anitop.member.vo.MemberVO;
import com.webjjang.util.PageObject;

/** @author 배고은 **/

@Service
public interface MemberService {
	
	// 리스트 - 관리자
	public List<MemberVO> list(PageObject pageObject);
	
	// 회원 정보보기
	public MemberVO view(MemberVO vo);
	
	// 회원가입 처리*
	public int write(MemberVO vo);
	
	// 정보수정 처리
	public int update(MemberVO vo);
	
	// 회원 사진 단독 수정 처리
	public int updatePhoto(MemberVO vo);
	
	// 회원탈퇴
	public int delete(MemberVO vo);
	
	// 회원 상태 변경 처리 - 관리자(회원 등급 폼 - 회원 리스트)
	public int statusUpdate(MemberVO vo);
	
	// 로그인 처리* - session을 이용
	public LoginVO login(LoginVO vo);
	// 비밀번호 찾기
}
