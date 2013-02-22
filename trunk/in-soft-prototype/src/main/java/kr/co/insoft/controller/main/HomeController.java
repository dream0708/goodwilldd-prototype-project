package kr.co.insoft.controller.main;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.XmlRootElement;

import kr.co.insoft.mybatis.example.model.ExampleModel;
import kr.co.insoft.mybatis.example.service.ExampleServiceIF;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	ExampleServiceIF exampleService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info(exampleService.getUserName("svary"));

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		//model.addAttribute("serverTime", formattedDate);
		model.addAttribute("example", new ExampleModel(1, "goodwilldd hello!"));

		return "home";
	}
	
	/**
	 * json 이나 xml로 값을 반환하기 위해서는 반드시 ResponseBody Annotation을 선언하고
	 * 변환하여 반환할 대상 객체에 XmlRootElement, JsonAutoDetect Annotation을 선언하여야 한다.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/test")
	@ResponseBody
	public ExampleModel doTest() {
		return new ExampleModel(1, "goodwilldd hello!");
	}
	
	@RequestMapping(value = "/login")
	public String login(Locale locale, Model model) {
		return "home";
	}

}
