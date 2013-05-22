package kr.co.insoft.board.controller;

import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.entity.DefaultListEntity;
import kr.co.insoft.board.service.ICommonBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/b/{boardName}/{page}")
public class BoardController {
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
	public String save(Model model, @PathVariable String boardName,
			@PathVariable Integer page, DefaultDetailEntity entity, RedirectAttributes attr) {
		entity.setBoardName(boardName);
		if ( commonBoard.doSave(entity) ) {
			attr.addFlashAttribute(entity);
			return redirectUrl.save(boardName, page);
		}
		return  redirectUrl.list(boardName, page);
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
