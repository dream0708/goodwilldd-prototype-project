package kr.co.insoft.board.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 게시판 관리 Controller
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 * 
 */
@Controller
@Secured(value = "ROLE_SUPERVISOR")
@RequestMapping(value = "/mb")
public class MasterBoardController {
	final String FORM = "tiles/masterBoard/form";
	/**
	 * 게시판 관리 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(Model model) {
		return FORM;
	}

	/**
	 * 게시판 목록
	 *  
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(Model model) {
		return "";
	}

	/**
	 * 게시판 저장
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(Model model) {
		return "";
	}

	/**
	 * 게시판 삭제
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "remove")
	public String remove(Model model) {
		return "";
	}

}
