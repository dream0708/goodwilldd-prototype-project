package kr.co.insoft.board.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.springframework.test.web.ModelAndViewAssert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import kr.co.insoft.AbstractTest;
import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.entity.DefaultListEntity;

import org.junit.Before;
import org.junit.Test;

public class BoardControllerTest extends AbstractTest {

	DefaultListEntity<DefaultDetailEntity> entity;
	
	DefaultDetailEntity saveData;
	
	@Before
	public void init() {
		saveData.setBoardName("testBoard");
		saveData.setContent("A");
		
	}
	
	@Test
	public void testGetList() throws Exception{
		mockMvc.perform(get("/b/testBoard/1/list.htm")).andExpect(status().isOk());
	}
	
	@Test public void testSave() throws Exception {
		mockMvc.perform(post("/b/testBoard/1/save.htm")
				.requestAttr("data", entity)).andExpect(status().isOk());
	}
}
