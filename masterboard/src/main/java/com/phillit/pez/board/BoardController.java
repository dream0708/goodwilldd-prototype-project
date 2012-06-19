package com.phillit.pez.board;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;

@Controller
@RequestMapping(value = "/b")
public class BoardController {
	private static final Logger log = LoggerFactory
			.getLogger(BoardController.class);

	/**
	 * 쓰기, 수정, 답글에 대한 폼 처리
	 * 
	 * @param board
	 * @return
	 */
	@RequestMapping(value = { "/write_form", "/edit_form", "/reply_form",
			"/modify_form" }, method = { RequestMethod.GET })
	public String boardFormProcess(Model model) {
		model.addAttribute("boardDataModel", new BoardDataModel("1", "sylee"));
		return "/board/write_form";
	}

	/**
	 * 글쓰기, 수정하기, 답글쓰기에 대한 처리
	 * 
	 * @param board
	 * @return
	 */
	@RequestMapping(value = { "/write", "/edit", "/reply", "/modify" }, method = { RequestMethod.POST })
	public String boardActionProcess(Model model,
			@ModelAttribute("boardDataModel") @Valid BoardDataModel board,
			BindingResult result, SessionStatus status) {
		log.debug(board.toString());

		if (result.hasErrors()) {
			model.addAttribute("boardDataModel", board);
			return "/board/write_form";
		} else {
			return "redirect:list";
		}
	}

	/**
	 * 목록에 대한 처리
	 * 
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String boardListProcessGet(Model model) throws Exception {
		// model.addAttribute("boardListModel", new BoardListModel());
		throw new NullPointerException("domain.AppException");
		// return "/board/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public String boardListProcessPost(
			@ModelAttribute("boardListModel") BoardListModel board,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {

		} else {

		}
		return "/board/list";
	}
}
