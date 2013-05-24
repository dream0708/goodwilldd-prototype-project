package kr.co.insoft.board.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.entity.DefaultListEntity;
import kr.co.insoft.board.exception.NullResultException;
import kr.co.insoft.board.exception.SaveException;
import kr.co.insoft.board.service.ICommonBoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 공통적으로 사용하는 게시판
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 * 
 */
@Controller
@RequestMapping(value = "/b/{boardName}/{page}")
public class BoardController {

	private static final Logger logger = LoggerFactory
			.getLogger(BoardController.class);
	final String LIST = "tiles/board/list";
	final String SAVE = "tiles/board/save";
	final String READ = "tiles/board/read";

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
	ICommonBoardService<DefaultDetailEntity> commonBoard;

	@RequestMapping(value = "list")
	public String list(Model model, @PathVariable String boardName,
			@PathVariable Integer page,
			DefaultListEntity<DefaultDetailEntity> entity) {
		entity.setBoardName(boardName);
		entity.setCurrentPageIndex(page);
		model.addAttribute("list", commonBoard.getListWithPaging(entity));
		return LIST;
	}

	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String saveForm(Model model) {
		model.addAttribute("defaultDetailEntity", new DefaultDetailEntity());
		return SAVE;
	}

	@RequestMapping(value = "replyForm", method = RequestMethod.GET)
	public String replyForm(Model model,
			@RequestParam(value = "seq") Integer seq) {
		DefaultDetailEntity entity = new DefaultDetailEntity();
		entity.setBseq(seq);
		model.addAttribute("defaultDetailEntity", entity);
		return SAVE;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Model model, @Valid @ModelAttribute("defaultDetailEntity") DefaultDetailEntity entity,
			BindingResult result,
			@PathVariable(value = "boardName") String boardName,
			@PathVariable(value = "page") Integer page) {
		if (result.hasErrors()) {
			if (logger.isDebugEnabled()) {
				for (ObjectError error : result.getAllErrors()) {
					logger.info("{} : {}", error.getCode(),
							error.getDefaultMessage());
				}
				logger.info("result hasErrors ? {}", result.hasErrors());
			}
			return SAVE;
		}

		try {
			entity.setBoardName(boardName);
			logger.info("entity.getBseq() {}", entity.getBseq());
			if (entity.getBseq() != 0)
				commonBoard.doReply(entity);
			else
				commonBoard.doSave(entity);
		} catch (SQLException e1) {
			return SAVE;
		} catch (SaveException e) {
			return SAVE;
		}

		return redirectUrl.list(boardName, page);
	}

	@RequestMapping(value = "update/{seq}")
	public String update(Model model,
			@PathVariable(value = "boardName") String boardName,
			@PathVariable(value = "page") Integer page,
			@PathVariable(value = "seq") Integer seq) {
		return LIST;
	}

	@RequestMapping(value = "read/{seq}")
	public String read(Model model,
			@PathVariable(value = "boardName") String boardName,
			@PathVariable(value = "page") Integer page,
			@PathVariable(value = "seq") Integer seq) {
		model.addAttribute("details", commonBoard.getDetails(seq));
		return READ;
	}

	@RequestMapping(value = "delete/{seq}")
	public String delete(Model model,
			@PathVariable(value = "boardName") String boardName,
			@PathVariable(value = "page") Integer page,
			@PathVariable(value = "seq") Integer seq) {
		return LIST;
	}

}
