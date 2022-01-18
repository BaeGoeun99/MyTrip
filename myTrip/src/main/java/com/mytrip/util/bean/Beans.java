package com.mytrip.util.bean;

import java.util.HashMap;
import java.util.Map;

import com.mytrip.main.controller.Controller;
import com.mytrip.main.controller.Service;

public class Beans {

	private static Map<String, Controller> controllerMap = new HashMap<>();
	private static Map<String, Service> serviceMap = new HashMap<>();
	private static Map<String, Object> daoMap = new HashMap<>();
	
	// 생성된 객체를 저장하는 메서드 - put()
	// Controller를 저장하는 메서드
	// key - /board : 게시판
	public static void put(String key, Controller controller) {
		controllerMap.put(key, controller);
	}
	
	// Service를 저장하는 메서드
	public static void put(String key, Service service) {
		serviceMap.put(key, service);
	}
	
	// DAO를 Object로 저장하는 메서드
	public static void put(String key, Object dao) {
		daoMap.put(key, dao);
	}
	
	// 객체를 꺼내는 메서드 - get()
	// Controller 꺼내기
	public static Controller getController(String key) {
		return controllerMap.get(key);
	}
	
	// Service 꺼내기
	public static Service getService(String key) {
		return serviceMap.get(key);
	}
	
	// dao 꺼내기 - Object로 꺼내기 때문에 형변환(casting)해서 사용하셔야만 합니다.
	public static Object getDAO(String key) {
		return daoMap.get(key);
	}
	
}
