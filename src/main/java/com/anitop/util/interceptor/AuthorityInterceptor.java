package com.anitop.util.interceptor;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.anitop.member.vo.LoginVO;
import lombok.extern.log4j.Log4j;

/** @author 배고은 **/

@Log4j
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	// url에 대한 권한 정보를 저장하는 Map<url, gradeNo>
	private static Map<String, Integer> authMap = new HashMap<>();
	private String url = null;
	// 페이지에 대한 등급 정보를 저장하는 메서드
	// 데이터를 넣는 방법 : static 초기화 블록
	static {
		// 메인 - 등록, 수정, 삭제 - 회원 : 1 | 관리자 : 9
		authMap.put("/main/write.do", 9);
		authMap.put("/main/update.do", 9);
		authMap.put("/main/updateImage.do", 9);
		authMap.put("/main/delete.do", 9);
		// 메인 - 등록, 수정, 삭제 - 회원 : 1 | 관리자 : 9
		authMap.put("/notice/write.do", 9);
		authMap.put("/notice/update.do", 9);
		authMap.put("/notice/delete.do", 9);
		// 회원관리 - 리스트
		authMap.put("/member/list.do", 9);
	}
	
	// 핸들러 ------------------------------------------------
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("preHandle()...........");
		url = request.getServletPath();
		log.info("preHandle().url : " + url);

		// 로그인 객체 꺼내기 : 로그인 정보는 session에 있음 > request에서 꺼내기
		HttpSession session = request.getSession();
		LoginVO vo = (LoginVO) session.getAttribute("login");

		// 권한이 없는 경우의 처리
		if (!checkAuthority(vo)) {
			// 오류 페이지로 이동시킵니다.
			response.sendRedirect(request.getContextPath() + "/error/authority.do");
			// 호출한 쪽으로 리턴 -> 아니면 계속 실행
			return false;
		}
		// 요청한 내용을 계속 진행
		return super.preHandle(request, response, handler);
	}//preHandle끝

	// 권한 체크 ------------------------------------------------
	private boolean checkAuthority(LoginVO vo) {
		// url 정보가 authMap 있는지 확인 한다.
		// 데이터가 없으면(null) 권한 체크가 필요 없는 페이지 요청입니다.
		Integer pageGradeNo = authMap.get(url);
		if (pageGradeNo == null) {
			log.info("checkAuthority() - 권한이 필요 없는 페이지 입니다.");
			return true;
		}
		if (vo == null) {
			log.info("checkAuthority() - 로그인이 필요한 서비스입니다.");
			return false;
		}
		log.info("checkAuthority().pageGradeNo : " + pageGradeNo);
		log.info("checkAuthority().userGradeNo : " + vo.getGradeNo());

		// 권한이 없는 페이지 요청에 대한 처리
		if (pageGradeNo > vo.getGradeNo()) {
			log.info("AuthorityInterceptor.checkAuthority() - 권한이 없습니다.");
			return false;
		} 
		log.info("checkAuthority() - 권한이 있습니다.");
		return true;
	}
}
