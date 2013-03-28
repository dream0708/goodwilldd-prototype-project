package kr.co.pdca.security.controller;

import kr.co.pdca.core.util.UtilFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sec")
public class SecurityController {

	private static final Log logger = LogFactory
			.getLog(SecurityController.class);

	@Autowired
	UtilFactory utilFactory;

	/**
	 * 로그인 페이지
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(Model model) {
		logger.info(utilFactory.commonPropertiesUtil().getString("test"));
		return "tiles/login";
	}
}
