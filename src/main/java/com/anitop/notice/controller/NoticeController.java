package com.anitop.notice.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.anitop.member.vo.LoginVO;
import com.anitop.notice.service.NoticeService;
import com.anitop.notice.vo.NoticeVO;
import com.webjjang.util.PageObject;
import lombok.extern.log4j.Log4j;

/** @author 배고은 **/

@Log4j
@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	@Qualifier("ns")
	private NoticeService service;
	
	private final String MODULE = "notice";
	
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model) {
		log.info("NoticeController.list() = " + service);
		pageObject.setPerPageNum(10);
		model.addAttribute("list", service.list(pageObject));
		model.addAttribute("pageObject", pageObject);
		return MODULE + "/" + "list";
	}
	
	@GetMapping("/view.do")
	public String view(Long no, Model model, @ModelAttribute PageObject pageObject) {
		log.info("NoticeController.view() = " + no);
		NoticeVO vo = service.view(no);
		vo.setContent(vo.getContent().replaceAll("\n", "<br>"));
		model.addAttribute("vo", vo);
		return MODULE + "/" + "view";
	}
	
	@GetMapping("/write.do")
	public String writeForm() {
		return MODULE + "/" + "write";
	}
	
	@PostMapping("/write.do")
	public String write(NoticeVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		vo.setId(LoginVO.getId(request));
		log.info("NoticeController.write() = " + vo);
		service.write(vo);
		rttr.addFlashAttribute("msg", "공지사항 등록이 완료되었습니다.");
		return "redirect:list.do";
	}
	
	@GetMapping("/update.do")
	public String updateForm(Long no, Model model, @ModelAttribute PageObject pageObject) {
		model.addAttribute("vo", service.view(no));
		return MODULE + "/" + "update";
	}
	
	@PostMapping("/update.do")
	public String update(NoticeVO vo, RedirectAttributes rttr, PageObject pageObject) throws Exception {
		log.info("NoticeController.update() = " + vo);
		service.update(vo);
		rttr.addFlashAttribute("msg", "공지사항 정보가 수정되었습니다.");
		return "redirect:view.do?no=" + vo.getNo() + "&page=" + pageObject.getPage() + "&prePageNum=" + pageObject.getPerPageNum();
	}
	
	@GetMapping("/delete.do")
	public String delete(NoticeVO vo, RedirectAttributes rttr, HttpServletRequest request, PageObject pageObject) throws Exception {
		log.info("NoticeController.delete() = " + vo);
		service.delete(vo);
		rttr.addFlashAttribute("msg", "공지사항가 삭제 되었습니다.");
		return "redirect:list.do?perPageNum="+pageObject.getPerPageNum();
	}
	
}//NoticeController--------
