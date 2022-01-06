package com.anitop.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.anitop.member.service.MemberService;
import com.anitop.member.vo.LoginVO;
import com.anitop.member.vo.MemberVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;
import lombok.extern.log4j.Log4j;

/** @author 배고은 **/
/* 자동으로 생성이 되게 하는 어노테이션 */
//@Controller - url과 관련 | @Service - 처리 | @Repository - DAO | @Component - 구성 | @RestController - url과 관련된 순수데이터

@Log4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	@Qualifier("mes")
	private MemberService service;
	
	private final String MODULE = "member";
	
	// 리스트 - 관리자
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model) {
		log.info("MemberController.write().service = " + service);
		model.addAttribute("list", service.list(pageObject));
		model.addAttribute("pageObject", pageObject);
		return MODULE + "/" + "list";
	}
	
	// 회원 정보보기
	@GetMapping("/view.do")
	public String view(MemberVO vo, Model model, @ModelAttribute PageObject pageObject, HttpServletRequest request) throws Exception {
		log.info("MemberController.view().vo = " + vo);
		vo = new MemberVO();
		String id = LoginVO.getId(request);
		id = request.getParameter("id");
		vo.setId(id);
		model.addAttribute("vo", service.view(vo));
		return MODULE + "/" + "view";
	}
	
	// 회원가입 폼*
	@GetMapping("/write.do")
	public String writeForm() {
		return MODULE + "/write";
	}
	
	// 회원가입 처리*
	@PostMapping("/write.do")
	public String write(MemberVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		log.info("MemberController.write().vo = " + vo);
		vo.setPhoto(FileUtil.upload("/upload/" + MODULE, vo.getImageFile(), request));
		service.write(vo);
		rttr.addFlashAttribute("msg", "환영합니다! AniTop에 회원가입이 되었습니다.");
		return "redirect:/main/list.do";
	}
	
	// 정보수정 폼
	@GetMapping("/update.do")
	public String updateForm(MemberVO vo, Model model, @ModelAttribute PageObject pageObject) {
		model.addAttribute("vo", service.view(vo));
		return MODULE + "/" + "update";
	}
	
	// 정보수정 처리
	@PostMapping("/update.do")
	public String update(MemberVO vo, RedirectAttributes rttr, PageObject pageObject) {
		log.info("MemberController.update().vo = " + vo);
		service.update(vo);
		rttr.addFlashAttribute("msg", "회원 정보가 수정되었습니다.");
		return "redirect:view.do?id=" + vo.getId() + "&page=" + pageObject.getPage() + "&prePageNum=" + pageObject.getPerPageNum();
	}
	
	// 회원 사진 단독 수정 처리
	@PostMapping("/updatePhoto.do")
	public String updatePhoto(MemberVO vo, RedirectAttributes rttr, PageObject pageObject, HttpServletRequest request) throws Exception {
		log.info("MemberController.updatePhoto().vo = " + vo);
		log.info("MemberController.updatePhoto().pageObject = " + pageObject);
		vo.setPhoto(FileUtil.upload("/upload/"+MODULE, vo.getImageFile(), request));
		service.updatePhoto(vo);
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteFileName(), request));
		rttr.addFlashAttribute("msg", "회원 사진이 수정되었습니다.");
		return "redirect:view.do?id=" + vo.getId() + "&page=" + pageObject.getPage() + "&prePageNum=" + pageObject.getPerPageNum();
	}
	
	// 회원탈퇴
	@GetMapping("/delete.do")
	public String delete(MemberVO vo, RedirectAttributes rttr, PageObject pageObject, HttpServletRequest request, HttpSession session) throws Exception {
		log.info("MemberController.delete().vo = " + vo);
		service.delete(vo);
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteFileName(), request));
		session.removeAttribute("login");
		rttr.addFlashAttribute("msg", "회원 탈퇴가 정상적으로 되었습니다.");
		return "redirect:/main/list.do?perPageNum="+pageObject.getPerPageNum();
	}
	
	// 회원 상태 변경 처리 - 관리자(회원 등급 폼 - 회원 리스트)
	@GetMapping("/statusUpdate.do")
	public String statusUpdateForm(MemberVO vo) {
		return MODULE + "/statusUpdate";
	}
	@PostMapping("/statusUpdate.do")
	public String statusUpdate(MemberVO vo, PageObject pageObject, RedirectAttributes rttr) {
		log.info("MemberController.statusUpdate().vo = " + vo);
		service.statusUpdate(vo);
		rttr.addFlashAttribute("msg", "회원 상태가 ["+ vo.getStatus() + "] 으/로 수정되었습니다.");
		return "redirect:view.do?id=" + vo.getId() + "&page=" + pageObject.getPage() + "&prePageNum=" + pageObject.getPerPageNum();
	}
	
	// 로그인 폼* - ID, PW
	@GetMapping("/login.do")
	public String loginForm() {
		return MODULE + "/login";
	}
	
	// 로그인 처리* - session을 이용
	@PostMapping("/login.do")
	public String login(LoginVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		log.info("MemberController.login().vo = " + vo);
		LoginVO loginVO = service.login(vo);
		log.info("login().loginVO = " + loginVO);
		if(loginVO == null) throw new Exception("아이디 또는 비밀번호를 다시 확인하세요.");
		session.setAttribute("login", loginVO);
		rttr.addFlashAttribute("msg", "어서오세요! 로그인되었습니다.");
		return "redirect:/main/list.do";
	}
	
	// 로그아웃*
	@GetMapping("/logout.do")
	public String logout(HttpSession session, RedirectAttributes rttr) {
		session.removeAttribute("login");
		rttr.addFlashAttribute("msg", "정상적으로 로그아웃되었습니다.");
		return "redirect:/main/list.do";
	}
	
}//MemberController끝
