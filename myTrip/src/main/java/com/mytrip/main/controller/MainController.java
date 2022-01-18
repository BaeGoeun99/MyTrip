package com.mytrip.main.controller;

import javax.servlet.http.HttpServletRequest;


import com.mytrip.util.bean.Beans;
import com.webjjang.util.PageObject;

public class MainController implements Controller {

	// 실행에 필요한 service 객체 선언.
	Service service = null;
	
	private void setService(String url) {
		// Init.init()에서 관련 URL로 찾아본다. : /board/list.do -> BoardListService
		service = Beans.getService(url);
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		// list, view, write, update, delete ??? -> request에서 URL 가져온다.
		String url = request.getServletPath();
		
		// Service에 전달을 해야할 객체 - main을 list만 있어서 pageObject를 넘긴다.
		Object data = null;
		
		// request에 담을 데이터의 key - jsp에서 데이터를 찾아가는 key
		String key = "";
		
		// 보여줄 JSP나 이동할 페이지의 정보 저장 변수
		String jsp = "";
		
		// 페이지 처리를 위한 객체
		PageObject pageObject = null;
		com.mytrip.util.pageobject.PageObject pageObject2 = null;
		
		switch (url) {
		
		case "/main/main.do":
			
			jsp = "main/main";
			
			pageObject = PageObject.getInstance(request);
			pageObject2 = com.mytrip.util.pageobject.PageObject.getInstance(request);
			
			// 공지사항
			pageObject.setPerPageNum(5);
			
			data = pageObject;
			
			setService("/notice/list.do");
			
			key = "noticeList";
			
			if(service != null)
				request.setAttribute(key, ExecuteService.execute(service, data));
			
		
			//맛집게시판 
			pageObject2.setPerPageNum(4);
			 
			setService("/diner/list.do");
			
			data = pageObject2;
			 
			key = "dinerList";
			 
			if(service != null) request.setAttribute(key, ExecuteService.execute(service, data));
			 
			
			// 축제게시판을 가져오는 처리 
			pageObject.setPerPageNum(4);
			
			setService("/festa/list.do");
			
			data = pageObject;
			
			key = "festaList";

			if(service != null)
				request.setAttribute(key, ExecuteService.execute(service, data));
			
			// 관광지
			pageObject.setPerPageNum(4);
			
			setService("/tourist/list.do");
			
			key = "touristList";

			if(service != null)
				request.setAttribute(key, ExecuteService.execute(service, data));
			
			break;


		default:
			System.out.println("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
			throw new Exception("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
		}
		
		return jsp;
	}

}
