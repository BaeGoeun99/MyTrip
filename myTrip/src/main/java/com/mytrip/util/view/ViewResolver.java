package com.mytrip.util.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {

	// 넘어오는 데이터의 구성 -> /board/list => 완성 : /WEB-INF/views + /board/list + .jsp
	private static final String PREFIX = "/WEB-INF/views/";
	private static final String SUFFIX = ".jsp";
	
	// JSP나 URL로 이동되게 하는 메서드 - request, response, JSP 대한 정보 문자열
	public static void forward(HttpServletRequest request, HttpServletResponse response, 
			String jsp) throws IOException, ServletException {
		// 맨앞에 "redirect:"이 붙어 있으면 "redirect:"을 지우고 페이지 이동을 시킨다.
		// jsp.indexOf("redirect:") -> 0 이면 맨앞에 있다. 0이 아니면 맨앞에 없다.
		if(jsp.indexOf("redirect:") == 0) {
			String url = jsp.substring("redirect:".length());
			response.sendRedirect(url);
			return;
		}
		// "redirect:"이 맨앞에 없는 경우. -> jsp선택. JSP를 이용한 메서드 호출해서 HTML을 만들도록 한다.
		// /WEB-INF/view/ + board/list + .jsp
		request.getRequestDispatcher(PREFIX + jsp + SUFFIX).forward(request, response);
	}
	
}
