package kr.co.pdca.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	private static final Log logger = LogFactory
			.getLog(CustomLoginSuccessHandler.class);

	/**
	 * 로그인 이후 > 로그인 관련 로그 및 계정작업 등등등을 수행한다.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		response.sendRedirect(request.getContextPath() + "/");
	}

}
