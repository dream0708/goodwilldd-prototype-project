package com.phillit.pez.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CommonException extends SimpleMappingExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		request.setAttribute("messageCode", ex.getMessage());
		return super.resolveException(request, response, handler, ex);
	}

	/**
	 * CommonException 이외의 각 Controller에서 처리할 경우 아래 메소드를 활용하여 리턴하여야 한다.
	 * @param ex
	 * @return
	 */
	public static ModelAndView errorModelAndView(Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("messages/alertMessage");
		modelAndView.addObject("messageCode", ex.getMessage());
		modelAndView.addObject("exceptionMsg", ex);
		return modelAndView;
	}
}
