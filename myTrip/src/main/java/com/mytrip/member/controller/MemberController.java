package com.mytrip.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mytrip.main.controller.Controller;
import com.mytrip.main.controller.ExecuteService;
import com.mytrip.main.controller.Service;
import com.mytrip.member.vo.LoginVO;
import com.mytrip.member.vo.MemberVO;
import com.mytrip.util.bean.Beans;
import com.mytrip.util.file.FileUtil;
import com.oreilly.servlet.MultipartRequest;
import com.webjjang.util.PageObject;

public class MemberController implements Controller {

	// 모듈이름
	private final String MODULE = "member";

	// 실행에 필요한 service 객체 선언.
	Service service = null;

	private void setService(String url) {
		// Init.init()에서 관련 URL로 찾아본다. : /member/list.do -> MemberListService
		service = Beans.getService(url);
	}

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub

		// 삭제해야할 파일 - 나중에 회원 정보 삭제시 사용
		String deleteFile = null;

		// 처리 결과를 담기 위해서 session을 request에서 꺼낸다.
		HttpSession session = request.getSession();

		// list, view, write, update, delete ??? -> request에서 URL 가져온다.
		String url = request.getServletPath();

		// Service에 전달을 해야할 객체
		Object data = null;

		// request에 담을 데이터의 key - jsp에서 데이터를 찾아가는 key
		String key = "";

		// 보여줄 JSP나 이동할 페이지의 정보 저장 변수
		String jsp = "";

		// 페이지 처리를 위한 객체
		PageObject pageObject = null;

		// 실행전 실행할 서비스 셋팅
		setService(url);

		// 미리 생성
		MemberVO vo = new MemberVO();
		String id = LoginVO.getId(request);

		switch (url) {
		// 회원 리스트 - 관리자만
		case "/" + MODULE + "/list.do":

			jsp = MODULE + "/list";

			key = "list";

			pageObject = PageObject.getInstance(request);

			data = pageObject;

			break;

		// 회원 정보 보기 처리
		case "/" + MODULE + "/view.do":

			id = request.getParameter("id");

			vo = new MemberVO();
			vo.setId(id);

			data = vo;

			key = "vo";

			jsp = MODULE + "/view";
			break;

		// 내 정보 보기 처리
		case "/" + MODULE + "/myPage.do":

			id = LoginVO.getId(request);

			vo = new MemberVO();
			vo.setId(id);

			data = vo;

			key = "vo";

			jsp = MODULE + "/myPage";
			break;

		// 아이디 중복
		case "/" + MODULE + "/idCheck.do":
			System.out.println("아이디 중복 체크"+request.getParameter("id"));
			id = request.getParameter("id");
			request.setAttribute("id", ExecuteService.execute(Beans.getService("/member/idCheck.do"), id));
			jsp = MODULE + "/idCheck";

			break;

		// 회원가입 폼
		case "/" + MODULE + "/writeForm.do":
			jsp = MODULE + "/writeForm";
			break;

		// 회원가입 처리
		case "/member/write.do":

			MultipartRequest multi = FileUtil.upload(MODULE, request);

			id = multi.getParameter("id");
			String pw = multi.getParameter("pw");
			String name = multi.getParameter("name");
			String gender = multi.getParameter("gender");
			String birth = multi.getParameter("birth");
			String tel = multi.getParameter("tel");
			String email = multi.getParameter("email");
			String region = multi.getParameter("region");

			String fileName = multi.getFilesystemName("imageFile");
			vo = new MemberVO();
			vo.setId(id);
			vo.setPw(pw);
			vo.setName(name);
			vo.setGender(gender);
			vo.setBirth(birth);
			vo.setTel(tel);
			vo.setEmail(email);
			vo.setRegion(region);
			vo.setPhoto(FileUtil.getPath() + fileName);

			data = vo;

			jsp = "redirect:/";

			session.setAttribute("msg", "성공적으로 계정 등록이 되었습니다. ");

			break;

		// 회원 정보 수정 폼
		case "/" + MODULE + "/updateForm.do":

			id = request.getParameter("id");

			vo = new MemberVO();
			vo.setId(id);
			
			data = vo;
			
			key="vo";
			
			jsp= MODULE +"/updateForm";

			break;

		// 회원 정보 수정 처리
		case "/" + MODULE + "/update.do":

			//데이터
			id =LoginVO.getId(request);
//			id = request.getParameter("id");
			pw = request.getParameter("pw");
			name =request.getParameter("name");
			gender = request.getParameter("gender");
			birth = request.getParameter("birth");
			tel = request.getParameter("tel");
			email = request.getParameter("email");
			region = request.getParameter("region");
//			
			vo= new MemberVO();
			vo.setId(id);
			vo.setPw(pw);
			vo.setName(name);
			vo.setGender(gender);
			vo.setBirth(birth);
			vo.setTel(tel);
			vo.setEmail(email);
			vo.setRegion(region);
			
			System.out.println("데이터 확인용"+vo);
			data=vo;
			
			jsp="redirect:myPage.do";
			
			session.setAttribute("msg","정상적으로 정보수정이 완료되었습니다.");
			
			
			break;

		// 회원 사진 변경 처리
		case "/" + MODULE + "/updatePhoto.do":

			break;

		// 회원 탈퇴 -> 회원 상태만 "탈퇴"로 수정한다. - 로그인 할 때 상태가 "정상"으로 되어 있는 회원만 가능
		case "/" + MODULE + "/delete.do":

			id = LoginVO.getId(request);

			vo = new MemberVO();
			vo.setId(id);
			data = vo;

			deleteFile = request.getParameter("del");

			session.removeAttribute("login");
			session.removeAttribute("goURL");
			jsp = "redirect:/";

			session.setAttribute("msg", "성공적으로 회원 탈퇴 처리가 되었습니다.");

			break;

		// 회원 탈퇴 -> 회원 상태만 "탈퇴"로 수정한다. - 로그인 할 때 상태가 "정상"으로 되어 있는 회원만 가능
		case "/" + MODULE + "/Admindelete.do":

			id = request.getParameter("id");

			vo = new MemberVO();
			vo.setId(id);
			data = vo;

			deleteFile = request.getParameter("del");

			jsp = "redirect:/";

			session.setAttribute("msg", "성공적으로 회원 삭제 처리가 되었습니다.");

			break;

		// 로그인 폼
		case "/" + MODULE + "/loginForm.do":

			jsp = MODULE + "/loginForm";

			break;
		// 약관동의 폼
		case "/" + MODULE + "/clouse.do":

			jsp = MODULE + "/clouse";

			break;

		// 로그인 처리
		case "/" + MODULE + "/login.do":

			// 데이터 수집
			id = request.getParameter("id");
			pw = request.getParameter("pw");

			// 전달할 객체 생성
			LoginVO loginVo = new LoginVO();
			loginVo.setId(id);
			loginVo.setPw(pw);

			data = loginVo;

			key = "login";

			String goURL = (String) session.getAttribute("goURL");

			jsp = "redirect:" + ((goURL == null) ? "/" : goURL);

			session.removeAttribute("goURL");
			break;

		// 로그아웃 처리 - session을 지운다.
		case "/" + MODULE + "/logout.do":

			session.removeAttribute("login");
			session.removeAttribute("goURL");
			session.setAttribute("msg", "로그아웃되었습니다.");

			jsp = "redirect:/";

			break;

		default:
			System.out.println("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
			throw new Exception("404 오류 - 존재하지 않는 페이지를 요청하셨습니다.");
		}

		if (service != null) {
			if (url.equals("/member/login.do")) {
				session.setAttribute(key, ExecuteService.execute(service, data));
				session.setAttribute("msg", "정상적으로 로그인이 되었습니다. 즐거운 시간되세요.");
			} else {
				request.setAttribute(key, ExecuteService.execute(service, data));
			}
		}

		request.setAttribute("pageObject", pageObject);

		return jsp;
	}

}
