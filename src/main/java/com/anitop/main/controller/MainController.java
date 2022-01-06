package com.anitop.main.controller;

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
import com.anitop.main.service.MainService;
import com.anitop.main.vo.MainVO;
import com.webjjang.util.PageObject;
import com.webjjang.util.file.FileUtil;
import lombok.extern.log4j.Log4j;

/** @author 배고은 **/

@Log4j
@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	@Qualifier("ms")
	private MainService service;
	
	private final String MODULE = "main";
	
	@GetMapping("/list.do")
	public String list(PageObject pageObject, Model model) {
		log.info("MainController.list() = " + service);
		pageObject.setPerPageNum(20);
		model.addAttribute("list", service.list(pageObject));
		model.addAttribute("pageObject", pageObject);
		return MODULE + "/" + "list";
	}
	
	@GetMapping("/view.do")
	public String view(Long no, Model model, @ModelAttribute PageObject pageObject) {
		log.info("MainController.view() = " + no);
		MainVO vo = service.view(no);
		vo.setContent(vo.getContent().replaceAll("\n", "<br>"));
		model.addAttribute("vo", vo);
		return MODULE + "/" + "view";
	}
	
	@GetMapping("/write.do")
	public String writeForm() {
		return MODULE + "/" + "write";
	}
	
	@PostMapping("/write.do")
	public String write(MainVO vo, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		vo.setId("admin");
		vo.setFileName(FileUtil.upload("/upload/" + MODULE, vo.getImageFile(), request));		
		log.info("MainController.write() = " + vo);
		service.write(vo);
		rttr.addFlashAttribute("msg", "컨텐츠 등록이 완료되었습니다.");
		return "redirect:list.do";
	}
	
	@GetMapping("/update.do")
	public String updateForm(Long no, Model model, @ModelAttribute PageObject pageObject) {
		model.addAttribute("vo", service.view(no));
		return MODULE + "/" + "update";
	}
	
	@PostMapping("/update.do")
	public String update(MainVO vo, RedirectAttributes rttr, PageObject pageObject) throws Exception {
		log.info("MainController.update() = " + vo);
		service.update(vo);
		rttr.addFlashAttribute("msg", "컨텐츠 정보가 수정되었습니다.");
		return "redirect:view.do?no=" + vo.getNo() + "&page=" + pageObject.getPage() + "&prePageNum=" + pageObject.getPerPageNum();
	}
	
	@PostMapping("/updateImage.do")
	public String updateImage(MainVO vo, HttpServletRequest request, RedirectAttributes rttr, PageObject pageObject) throws Exception {
		log.info("MainController.updateImage() = " + vo);
		log.info("MainController.updateImage() = " + pageObject);
		vo.setFileName(FileUtil.upload("/upload/" + MODULE, vo.getImageFile(), request));
		service.updateImage(vo);
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteFileName(), request));
		rttr.addFlashAttribute("msg", "이미지 정보가 수정되었습니다.");
		return "redirect:view.do?no=" + vo.getNo() + "&page=" + pageObject.getPage() + "&prePageNum=" + pageObject.getPerPageNum();
	}
	
	@GetMapping("/delete.do")
	public String delete(MainVO vo, RedirectAttributes rttr, HttpServletRequest request, PageObject pageObject) throws Exception {
		log.info("MainController.delete() = " + vo);
		service.delete(vo);
		FileUtil.remove(FileUtil.getRealPath("", vo.getDeleteFileName(), request));
		rttr.addFlashAttribute("msg", "컨텐츠가 삭제 되었습니다.");
		return "redirect:list.do?perPageNum="+pageObject.getPerPageNum();
	}
	
}//MainController--------
