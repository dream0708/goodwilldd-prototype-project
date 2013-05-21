package kr.co.insoft.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/b/{theme}.{version}/{boardName}/{page}")
public class BoardController {
	@RequestMapping(value = "list")
	public String list(Model model, @PathVariable String theme,
			@PathVariable String version, @PathVariable String boardName,
			@PathVariable Integer page) {
		return null;
	}

	@RequestMapping(value = "save")
	public String save(Model model, @PathVariable String theme,
			@PathVariable String version, @PathVariable String boardName,
			@PathVariable Integer page) {
		return null;
	}
	
	@RequestMapping(value = "update/{seq}")
	public String update(Model model, @PathVariable String theme,
			@PathVariable String version, @PathVariable String boardName,
			@PathVariable Integer page) {
		return null;
	}

	@RequestMapping(value = "read/{seq}")
	public String read(Model model, @PathVariable String theme,
			@PathVariable String version, @PathVariable String boardName,
			@PathVariable Integer page) {
		return null;
	}

	@RequestMapping(value = "delete/{seq}")
	public String delete(Model model, @PathVariable String theme,
			@PathVariable String version, @PathVariable String boardName,
			@PathVariable Integer page) {
		return null;
	}

}
