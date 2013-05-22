package kr.co.insoft.board.controller;

import javax.validation.Valid;

import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.entity.DefaultListEntity;
import kr.co.insoft.board.service.ICommonBoard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/b/{boardName}/{page}")
public class BoardController {

	private static final Logger logger = LoggerFactory
			.getLogger(BoardController.class);
	final String LIST = "tiles/board/list";
	final String SAVE = "tiles/board/save";

	class Redirect {
		final String PREFIX = "redirect:/b/";

		public String list(String boardName, Integer page) {
			return PREFIX + boardName + "/" + page + "/list.htm";
		}

		public String save(String boardName, Integer page) {
			return PREFIX + boardName + "/" + page + "/save.htm";
		}

		public String read(String boardName, Integer page, Integer seq) {
			return PREFIX + boardName + "/" + page + "/read/" + seq + ".htm";
		}
	};

	Redirect redirectUrl = new Redirect();

	@Autowired
	ICommonBoard<DefaultDetailEntity> commonBoard;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// binder.setValidator(new DefaultDetailEntityValidator());
	}

	@RequestMapping(value = "list")
	public String list(Model model, @PathVariable String boardName,
			@PathVariable Integer page,
			DefaultListEntity<DefaultDetailEntity> entity) {
		entity.setBoardName(boardName);
		entity.setCurrentPageIndex(page);
		model.addAttribute("list", commonBoard.getListWithPaging(entity));
		return LIST;
	}

	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String saveForm(Model model, @PathVariable String boardName,
			@PathVariable Integer page, DefaultDetailEntity entity) {
		return SAVE;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid DefaultDetailEntity entity,
			@PathVariable String boardName, @PathVariable Integer page,
			RedirectAttributes attr, BindingResult result) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				logger.error("{} : {}", error.getCode(),
						error.getDefaultMessage());
			}
			attr.addFlashAttribute(entity);
			return SAVE;
		}
		entity.setBoardName(boardName);
		commonBoard.doSave(entity);
		return redirectUrl.list(boardName, page);
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
