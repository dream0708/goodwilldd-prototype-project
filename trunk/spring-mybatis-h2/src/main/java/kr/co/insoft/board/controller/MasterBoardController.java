package kr.co.insoft.board.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

@Controller
@Secured(value="ROLE_SUPERVISOR")
public class MasterBoardController {

}
