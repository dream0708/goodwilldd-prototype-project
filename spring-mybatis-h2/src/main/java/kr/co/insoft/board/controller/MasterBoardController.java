package kr.co.insoft.board.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 게시판 관리 Controller
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 *
 */
@Controller
@Secured(value = "ROLE_SUPERVISOR")
@RequestMapping(value = "/mb/")
public class MasterBoardController {

}
