package kr.pe.goodwilldd;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import kr.pe.goodwilldd.example.entity.Order;
import kr.pe.goodwilldd.example.service.ExampleService;

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
	ExampleService exampleService;

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}
	
	@RequestMapping(value = "/test")
	@ResponseBody public Order doTest(Model model) throws Exception {
		return exampleService.jpaTest();
	}
	
	@RequestMapping(value = "/test2")
	@ResponseBody public Order doTest2(Model model) throws Exception {
		return exampleService.jpaTest();
	}
}
