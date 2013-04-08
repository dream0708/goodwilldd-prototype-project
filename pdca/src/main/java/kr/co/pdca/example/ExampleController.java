package kr.co.pdca.example;

import java.util.List;

import kr.co.pdca.example.entity.Example;
import kr.co.pdca.example.service.ExampleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping("/json")
	@ResponseBody
	public List<Example> getJson() {
		return exampleService.findAll();
	}

	@RequestMapping("/xml")
	@ResponseBody
	public List<Example> getXml() {
		return exampleService.findAllByMyBatis();
	}
}
