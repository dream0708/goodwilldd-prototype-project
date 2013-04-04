package kr.co.pdca.example;

import kr.co.pdca.example.service.ExampleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/exam")
public class ExampleController {
	
	@Autowired
	ExampleService exampleService;
	
	@RequestMapping("/allTest")
	public String allTest() {
		exampleService.findAll();
		exampleService.findAllByMyBatis();
		return "tiles/home";
	}
}
