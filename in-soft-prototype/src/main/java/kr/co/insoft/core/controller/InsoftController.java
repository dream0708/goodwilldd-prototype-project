package kr.co.insoft.core.controller;

import javax.servlet.http.HttpServletResponse;

import kr.co.insoft.core.exception.InsoftExceptionResolver;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Exception 처리 로직은 구상 중.
 * 
 * @author sylee
 *
 */
public interface InsoftController {

	@ExceptionHandler(InsoftExceptionResolver.class)
	public @ResponseBody String handleException(Exception e, HttpServletResponse response);
}
