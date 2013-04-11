package kr.co.insoft.example.controller;

import kr.co.insoft.example.entity.Example;
import kr.co.insoft.example.service.ExampleService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/exam")
public class ExampleController {

	private static final Log logger = LogFactory
			.getLog(ExampleController.class);

	@Autowired
	ExampleService exampleService;

	@RequestMapping("/main")
	public String examMain(Model model) {
		model.addAttribute(new Example());
		return "tiles/example/home";
	}

	@RequestMapping("/m")
	public String m() {
		return "tiles/example/home";
	}

	@RequestMapping("/test2")
	public String test2(Model model, Example exam) {
		exampleService.doInsertExample(exam);
		return "forward:main.htm";
	}

	@RequestMapping("/json2/{seq}")
	@ResponseBody
	public Example json2(Model model, @PathVariable long seq) {
		return exampleService.getExam2(seq);
	}

}
