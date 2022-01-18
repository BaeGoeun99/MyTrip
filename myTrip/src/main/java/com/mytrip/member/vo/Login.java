package com.mytrip.member.vo;

public class Login {
	
	public static LoginVO login;
	
	// 로그인 정보 가져가기
	public static LoginVO getLogin() {
		return login;
	}
	
	// 로그인 정보 저장하기
	public static boolean setLogin(LoginVO login) {
		// login이 되어 있는 상태이면(null이 아니면) 다시 실행하지 않는다.
		if(Login.login == null) {
			Login.login = login; // 로그인이 되어 있는 않는 상태 -> 로그인 처리를 한다.
			return true; // 로그인이 진행됐다.
		}
		else return false; // 로그인이 된 상태 -> 로그인이 진행되지 않음.
	}
	
	// 로그인 확인하기	
	public static boolean isLogin() {		
		//System.out.println("Login.isLogin().login :" + login);
		if(login == null) return false; // 비로그인임
		return true; //로그인 되어있음
	}
	
	// 아이디 받아가기 - 메세지를 작성할 때 작성자는 로그인한 아이디여야함
	// 사용법 : Login.getID()
	public static String getID() {
		return login.getId();
	}
	
	// 등급 받아가기
	public static int getGradeNo() {
		return login.getGradeNo();
	}
	
	// 이름 받아가기
	public static String getName() {
		return login.getName();
	}
	// 로그아웃 처리를 하는 데이터 지우기
	public static void logout() {
		login = null;
	}
}
