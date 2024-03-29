package kr.co.insoft;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.springframework.test.web.ModelAndViewAssert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import kr.co.insoft.example.controller.ExampleController;
import kr.co.insoft.example.entity.Example;
import kr.co.insoft.example.mapper.NExampleMapper;
import kr.co.insoft.example.service.ExampleService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AllTest extends AbstractTest {

	private static final Log logger = LogFactory.getLog(AllTest.class);

	@Autowired
	ExampleController exampleController;

	@Autowired
	ExampleService exampleService;

	@Autowired
	NExampleMapper nExampleMapper;

	@Test
	public void testExampleController() throws Exception {
		mockMvc.perform(get("/exam/main")).andExpect(status().isOk());
	}

	@Test
	public void testExampleService() throws Exception {
		exampleService.getExamList2();
	}

	@Test
	public void testExampleMapper() throws Exception {
		for (Example e : nExampleMapper.getList()) {
			logger.info(e.toString());
		}
	}
}
