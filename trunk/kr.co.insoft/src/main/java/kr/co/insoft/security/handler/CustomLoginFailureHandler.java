package kr.co.insoft.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	private static final Log logger = LogFactory
			.getLog(CustomLoginFailureHandler.class);

	public static enum LoginFailureCause {
		maxsession, badcredentials, locked, disabled, defaultFalse
	};

	/**
	 * 로그인 실패에 대한 로그 기록 및 작업
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		try {
			LoginFailureCause msg = LoginFailureCause.defaultFalse;
			if (auth instanceof SessionAuthenticationException) {
				msg = msg.maxsession;
			} else if (auth instanceof BadCredentialsException) {
				msg = msg.badcredentials;
			} else if (auth instanceof LockedException) {
				msg = msg.locked;
			} else if (auth instanceof DisabledException) {
				msg = msg.disabled;
			}
			logger.error(auth);
			response.sendRedirect(request.getContextPath()
					+ "/sec/login.htm?lr=" + msg.name());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
