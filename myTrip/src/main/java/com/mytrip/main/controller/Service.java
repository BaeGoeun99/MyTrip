package com.mytrip.main.controller;

/**
 * 서비스들을 실행하기 위한 인터페이스<br>
 * @author EZEN
 *
 */
public interface Service {

	// 서비스를 실행하기 위한 메서드 선언
	public Object service(Object obj) throws Exception;
	
	// dao를 셋팅하는 setter를 만들어 준다.
	public void setDao(Object dao) ;
}
