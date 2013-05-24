package kr.co.insoft.board.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.springframework.test.web.ModelAndViewAssert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import kr.co.insoft.AbstractTest;

import org.hamcrest.core.IsNot;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardControllerTest extends AbstractTest {

	final String boardName = "testBoard";
	final int page = 1;
	
	@Test
	public void testGetList() throws Exception {
		mockMvc.perform(get("/b/{boardName}/{page}/list.htm", boardName, page))
				.andExpect(status().isOk())
				.andExpect(view().name("tiles/board/list"))
				.andExpect(model().attribute("list", hasProperty("paging")))
				.andExpect(model().attribute("list", hasProperty("count")));
	}

	@Test
	public void testGetRead() throws Exception {
		mockMvc.perform(get("/b/testBoard/1/read/100.htm")).andExpect(
				status().isOk());
	}

	@Test
	public void testWriteForm() throws Exception {
		mockMvc.perform(get("/b/testBoard/1/writeForm.htm"))
				.andExpect(status().isOk())
				.andExpect(view().name("tiles/board/save"));
	}

	@Test
	public void testSave() throws Exception {
		mockMvc.perform(
				(post("/b/{boardName}/{page}/save.htm", boardName, page)
						.param("subject", "Suibject!!")
						.param("content", "Content")
						.param("register", "register")))
				.andExpect(status().isOk())
				.andExpect(view().name("forward:/b/testBoard/1/list.htm"));
	}

}
