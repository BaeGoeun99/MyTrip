package com.anitop.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** @author 배고은 **/

@Controller
@RequestMapping("/error")
public class ErrorController {
	
	@GetMapping("/authority.do")
	public String authority() {
		
		return "/error/authority";
	}

}
