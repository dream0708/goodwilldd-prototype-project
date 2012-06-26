package com.phillit.pez.board;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;
import com.phillit.pez.board.service.IBoardService;
import com.phillit.pez.common.exception.CommonException;
import com.phillit.pez.common.exception.ICommonExceptionHandler;

@Controller
@RequestMapping(value = "/b")
public class BoardController implements ICommonExceptionHandler {
	private static final Logger log = LoggerFactory
			.getLogger(BoardController.class);

	@Autowired
	IBoardService boardService;

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
	 * @param data
	 * @return
	 */
	@RequestMapping(value = { "/write", "/edit", "/reply", "/modify" }, method = { RequestMethod.POST })
	public String boardActionProcess(Model model,
			@ModelAttribute("boardDataModel") @Valid BoardDataModel data,
			BindingResult result, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("boardDataModel", data);
			return "/board/write_form";
		} else {
			if (boardService.write(data)) {
				return "redirect:list";
			} else {
				throw new Exception("alert.write.error");
			}
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
		BoardListModel list = new BoardListModel();
		// TODO 게시판 이름을 설정하는 방법
		list.setBoardName("1");
		list.setList(boardService.getList(list));
		model.addAttribute("boardListModel", list);
		return "/board/list";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public String boardListProcessPost(
			@ModelAttribute("boardListModel") BoardListModel board,
			BindingResult result, SessionStatus status) throws Exception {
		log.debug(board.toString());
		if (result.hasErrors()) {
			throw new Exception("alert.list.error");
		} else {
			board.setList(boardService.getList(board));
		}
		return "/board/list";
	}

	@Override
	public ModelAndView exceptionHandler(Exception ex,
			HttpServletRequest request) throws Exception {
		return CommonException.errorModelAndView(ex);
	}
}
