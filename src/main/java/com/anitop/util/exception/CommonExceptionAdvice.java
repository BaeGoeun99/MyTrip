package com.anitop.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import lombok.extern.log4j.Log4j;

/** @author 배고은 **/

@Log4j
@ControllerAdvice
public class CommonExceptionAdvice {
	
	// 404에러에 대한 예외 클래스 파일을 넘기고 생성해서 사용하도록 지정
	@ExceptionHandler(NoHandlerFoundException.class)
	// 요청에 대한 응답 시 상태 코드값을 넘겨준다 > 정상 : 200, 페이지 없음 : 404
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		log.error("handle404() - 요청하신 페이지가 존재하지 않습니다.");
		return "error/custom404";
	}
	
	// 500에러에 대한 예외 클래스 파일을 넘기고 생성해서 사용하도록 지정
	@ExceptionHandler(Exception.class)
	public String exception(Exception ex, Model model) {
		log.error("Exception... - " + ex.getMessage());
		// jsp에서 exception 객체를 EL 객체에서 사용할 수 있도록 Model에 담는다
		model.addAttribute("exception", ex);
		log.error(model);
		return "error/error_page";
	}

}
