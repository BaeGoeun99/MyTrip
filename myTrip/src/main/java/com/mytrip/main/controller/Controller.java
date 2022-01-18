package com.mytrip.main.controller;

import javax.servlet.http.HttpServletRequest;

public interface Controller {

	// 접근 제한자 - public
	// 결과에 대한 데이터 리터 타입 - String
	// 메서드 이름 - execute()
	// 넘겨지는 데이터 - request : 데이터 수집. 처리된 결과를 저장
	// 예외처리 - DispacherServlet - throw 시킨다.
	public String execute(HttpServletRequest request) throws Exception;
	
}
