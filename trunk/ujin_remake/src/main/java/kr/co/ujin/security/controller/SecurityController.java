package kr.co.ujin.security.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sec")
public class SecurityController {

	private static final Log logger = LogFactory
			.getLog(SecurityController.class);

	/**
	 * 로그인 페이지
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(Model model) {
		return "tiles/login";
	}
}
