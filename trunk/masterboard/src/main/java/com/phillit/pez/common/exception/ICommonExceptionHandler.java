package com.phillit.pez.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public interface ICommonExceptionHandler {

	@ExceptionHandler({ Exception.class })
	public ModelAndView exceptionHandler(Exception ex,
			HttpServletRequest request) throws Exception;
}
