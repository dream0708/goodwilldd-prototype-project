package kr.co.insoft.board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import kr.co.insoft.AbstractTest;
import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.entity.DefaultListEntity;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardControllerTest extends AbstractTest {

	DefaultListEntity<DefaultDetailEntity> entity;
	DefaultDetailEntity defaultDetailEntity;

	private static final Logger logger = LoggerFactory
			.getLogger(BoardControllerTest.class);

	@Before
	public void init() {
		defaultDetailEntity = new DefaultDetailEntity();
		defaultDetailEntity.setBoardName("testBoard");
		defaultDetailEntity.setSubject("Test1");
		defaultDetailEntity.setContent("Test Content1");
		defaultDetailEntity.setRegister("goodwilldd");
		logger.debug("DefaultDetailEntity \n{}", defaultDetailEntity.toDebug());
	}

	@Test
	public void testGetList() throws Exception {
		mockMvc.perform(get("/b/testBoard/1/list.htm")).andExpect(
				status().isOk());
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
				(post("/b/{boardName}/{page}/save.htm", "testBoard", 1)
						.param("subject", "Suibject!!")
						.param("content","Content")
						.param("register", "register")
						
				))
				.andExpect(status().isOk());
	}

}
