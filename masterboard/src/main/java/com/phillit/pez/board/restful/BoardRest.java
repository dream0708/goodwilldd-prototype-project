package com.phillit.pez.board.restful;

import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;
import com.phillit.pez.common.util.CommonUtil;

@Controller
@RequestMapping("r")
public class BoardRest {
	private static final Logger log = LoggerFactory.getLogger(BoardRest.class);

	private RestTemplate template = new RestTemplate();

	private final String listUrl = "http://localhost:8080/masterboard/b/%s/list.xml?p=%s";

	@RequestMapping(value = "b/{boardName}/list/{currentPage}")
	public void getList(Model model,
			@PathVariable("boardName") String boardName,
			@PathVariable("currentPage") String currentPage, Writer writer)
			throws IOException {
		String uri = CommonUtil.formatString(listUrl, boardName, currentPage);
		writer.write(template.getForObject(uri, String.class));
	}

	@RequestMapping(value = "b/delete")
	public String doWrite(Model model, BoardDataModel data) {
		try {
			log.info(data.toString());
			template.delete(
					"http://localhost:8080/masterboard/b/1/delete/save", data);
		} catch (Exception e) {
			model.addAttribute("exceptionMsg", e.getMessage());
		}
		return "rest";
	}

}
