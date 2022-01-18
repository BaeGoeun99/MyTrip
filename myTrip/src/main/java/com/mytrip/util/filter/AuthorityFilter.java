package com.mytrip.util.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthorityFilter
 */
public class AuthorityFilter implements Filter {

	// key - value -> map
	// URL(String) - gradeNo(Integer)
	Map<String, Integer> pageGradeMap = new HashMap<String, Integer>();
	
    /**
     * Default constructor. 
     */
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getServletPath();
		
		// 권한 처리를 한다.
		// 사용자 권한이 session에 있다.
		HttpSession session = req.getSession();
//		LoginVO vo = (LoginVO) session.getAttribute("login");
		int gradeNo = 0;
//		if(vo != null) gradeNo = vo.getGradeNo();
		
		// 페이지 권한 가져오기
		int pageGradeNo = 0;
		Integer tempPageGradeNo = pageGradeMap.get(url);
		// tempPageGradeNo == null : 없다. 모든 사용자가 다 볼 수 있는 페이지이다.
		// tempPageGradeNo != null : 권한이 있어야 볼 수 있는 페이지이다.
		if(tempPageGradeNo != null) pageGradeNo = tempPageGradeNo;
		
		// 페이지 등급이 1 이상이면 로그인이 필요한 페이지 입니다. 사용자 등급이 1 이상이여야하고 
		// 사용자 등급이 0이면 로그인을 안했으므로 오류 페이지로 이동을 하면서 로그인이 필요하다고 표시해 준다.
//		if(pageGradeNo >= 1 && gradeNo == 0) {
//			throw new ServletException("500::로그인이 필요한 페이지 입니다.");
//		}
		if(pageGradeNo >= 1 && gradeNo == 0) {
			//1. URL과 쿼리를 session 저장한다. ajax url은 제외 시킨다. ajax는 페이지 이동이 안되도록 만듬.
			if(url.indexOf("/ajax") != 0) {
				session.setAttribute("goURL", req.getServletPath()
					+ ((req.getQueryString() == null)?"":("?" + req.getQueryString())));
				session.setAttribute("msg", "로그인이 필요한 페이지입니다. 로그인해 주세요.");
				System.out.println("AuthorityFilter.doFilter() - 로그인이 필요한 페이지 처리");
				//2. /member/loginForm.do로 페이지 이동 시킨다.
				((HttpServletResponse)response).sendRedirect("/member/loginForm.do");
			}
			return;
		}
		
		
		// 페이지 등급이 9이면 관리자로 로그인 해야만 한다. 사용자 등급이 9 이여야 한다.
		if(pageGradeNo == 9 && gradeNo < 9)
			throw new ServletException("500::페이지에 접근할 권한이 없습니다.");
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println(getClass().getSimpleName()+ ".init()");
	}

}
