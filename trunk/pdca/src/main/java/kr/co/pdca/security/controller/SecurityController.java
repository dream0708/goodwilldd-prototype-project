package kr.co.pdca.security.controller;

import kr.co.pdca.security.mapper.normal.SecurityMapper;

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
	SecurityMapper securityMapper;
	/**
	 * 로그인 페이지
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(Model model) {
		securityMapper.getUserList();
		return "tiles/login";
	}
}
