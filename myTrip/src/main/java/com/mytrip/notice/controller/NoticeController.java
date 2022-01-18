package com.mytrip.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mytrip.main.controller.Controller;
import com.mytrip.main.controller.ExecuteService;
import com.mytrip.main.controller.Service;
import com.mytrip.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;
import com.mytrip.util.bean.Beans;

public class NoticeController implements Controller {

	// 모듈명을 저장하는 변수
	private final String MODULE = "notice"; 
	
	Service service = null;
	
	private void setService(String url) {

		service = Beans.getService(url);
	
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String url = request.getServletPath();
		
		// Service에 전달을 해야할 객체
		Object data = null;
		
		// request에 담을 데이터의 key - jsp에서 데이터를 찾아가는 key
		String key = "";
		
		// 보여줄 JSP나 이동할 페이지의 정보 저장 변수
		String jsp = "";
		
		PageObject pageObject = null;
		
		// 실행전 실행할 서비스 셋팅
		setService(url);
		
		switch (url) {
		
		// 게시판 리스트 처리
		case "/"+ MODULE +"/list.do":
			
			jsp = MODULE + "/list";
		
			key = "list";
			
			pageObject = PageObject.getInstance(request);
			
			data = pageObject;
			
			break;
		//글보기
		case "/"+ MODULE +"/view.do":
			
			pageObject = PageObject.getInstance(request);
			
			String noStr = request.getParameter("no");
			long no = Long.parseLong(noStr); 
			String incStr = request.getParameter("inc");
			int inc = Integer.parseInt(incStr);
			
			data = new Object[] {no, inc};
			
			key = "vo";
			
			jsp = MODULE + "/view";
			
			
			
			break;
		
		//글쓰기 폼
		case "/"+ MODULE +"/writeForm.do":
			jsp = MODULE + "/writeForm";
			break;
		
		//글쓰기 처리
		case "/"+ MODULE +"/write.do":
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String open = request.getParameter("open");
			
			
			
			NoticeVO vo = new NoticeVO();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setOpen(open);
			
			data = vo;
			
			jsp = "redirect:list.do";
			
			session.setAttribute("msg", "성공적으로 글 등록이 되었습니다. ");
			
			break;
			
		//글수정 폼
		case "/"+ MODULE +"/updateForm.do":
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			data = new Object[] {no, 0};
			
			key = "vo"; 
			
			jsp = MODULE + "/updateForm";
			
			break;
			
		// 글수정 처리
		case "/"+ MODULE +"/update.do":
			
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			title = request.getParameter("title");
			content = request.getParameter("content");
			open = request.getParameter("open");
			
			
			vo = new NoticeVO();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setOpen(open);
			
			data = vo;
			
			jsp = "redirect:view.do?no=" + no + "&inc=0";
			
			session.setAttribute("msg", "성공적으로 글 수정이 되었습니다. ");
		
			break;

		case "/"+ MODULE +"/delete.do":
			
			noStr = request.getParameter("no");
			no = Long.parseLong(noStr);
			
			data = no;
			
			jsp = "redirect:list.do";
			
			session.setAttribute("msg", "성공적으로 글 삭제가 처리되었습니다. ");
			
			break;
			
			
		}
		
		// service가 null이 아닌 경우만 실행하자.
		if(service != null)
			request.setAttribute(key, ExecuteService.execute(service, data));
		
		request.setAttribute("pageObject", pageObject);
		
		return jsp;
	}
	}



