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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;
import com.phillit.pez.board.service.IBoardService;
import com.phillit.pez.common.exception.CommonException;
import com.phillit.pez.common.exception.ICommonExceptionHandler;

@Controller
@RequestMapping(value = "b")
public class BoardController implements ICommonExceptionHandler {
	private static final Logger log = LoggerFactory
			.getLogger(BoardController.class);

	@Autowired
	IBoardService boardService;

	public static enum COMMAND {
		write, modify, reply, read, delete
	};

	/**
	 * 쓰기, 수정 대한 폼 처리
	 * 
	 * @param board
	 * @return
	 */
	@RequestMapping(value = "{boardName}/{action}/form", method = RequestMethod.GET)
	public String boardFormProcess(
			Model model,
			@PathVariable("boardName") String boardName,
			@PathVariable("action") String action,
			@RequestParam(value = "bSeq", required = false, defaultValue = "0") String bSeq) {
		switch(COMMAND.valueOf(action)) {
		default:
		case write:
			model.addAttribute("boardDataModel", new BoardDataModel(boardName,
					"sylee"));
			break;
		case read:
		case modify:
		case reply:
			model.addAttribute("boardDataModel", boardService.doRead(bSeq));
			break;
		}
		return "/board/form";
	}

	/**
	 * 글쓰기, 수정하기에 대한 처리
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "{boardName}/{action}/save", method = RequestMethod.POST)
	public String boardActionProcess(Model model,
			@PathVariable("boardName") String boardName,
			@PathVariable("action") String action,
			@ModelAttribute("boardDataModel") @Valid BoardDataModel data,
			BindingResult result, SessionStatus status,
			HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("boardDataModel", data);
			return "/board/form";
		} else {
			if (boardService.write(data, COMMAND.valueOf(action))) {
				return "redirect:../list";
			} else {
				throw new Exception("alert.write.error");
			}
		}
	}

	/**
	 * 목록
	 * 
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "{boardName}/list", method = { RequestMethod.GET })
	public String boardListProcessGet(Model model,
			@PathVariable("boardName") String boardName) throws Exception {
		BoardListModel data = new BoardListModel();
		data.setBoardName(boardName);
		boardService.getList(data);
		model.addAttribute("boardListModel", data);
		return "/board/list";
	}

	/**
	 * 목록에 대한 처리 및 검색
	 * 
	 * @param data
	 * @param result
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "{boardName}/list", method = { RequestMethod.POST })
	public String boardListProcessPost(Model model,
			@ModelAttribute("boardListModel") BoardListModel data,
			@PathVariable("boardName") String boardName,
			BindingResult result, SessionStatus status) throws Exception {
		data.setBoardName(boardName);
		if (result.hasErrors()) {
			throw new Exception("alert.list.error");
		} else {
			boardService.getList(data);
		}
		return "/board/list";
	}

	@Override
	public ModelAndView exceptionHandler(Exception ex,
			HttpServletRequest request) throws Exception {
		return CommonException.errorModelAndView(ex);
	}
}
