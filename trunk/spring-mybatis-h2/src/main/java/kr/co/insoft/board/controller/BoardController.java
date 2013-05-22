package kr.co.insoft.board.controller;

import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.service.ICommonBoard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/b/{boardName}/{page}")
public class BoardController {
	final String LIST = "tiles/board/list";

	private static final Logger logger = LoggerFactory
			.getLogger(BoardController.class);

	@Autowired
	ICommonBoard<DefaultDetailEntity> commonBoard;

	@RequestMapping(value = "list")
	public String list(Model model, @PathVariable String boardName,
			@PathVariable Integer page) {
		logger.debug("BoardName {} / Page {}", boardName, page);
		model.addAttribute("list", commonBoard.getListWithPaging());
		return LIST;
	}

	@RequestMapping(value = "save")
	public String save(Model model, @PathVariable String boardName,
			@PathVariable Integer page) {
		return LIST;
	}

	@RequestMapping(value = "update/{seq}")
	public String update(Model model, @PathVariable String boardName,
			@PathVariable Integer page) {
		return LIST;
	}

	@RequestMapping(value = "read/{seq}")
	public String read(Model model, @PathVariable String boardName,
			@PathVariable Integer page) {
		return LIST;
	}

	@RequestMapping(value = "delete/{seq}")
	public String delete(Model model, @PathVariable String boardName,
			@PathVariable Integer page) {
		return LIST;
	}

}