package kr.co.insoft.security.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.springframework.test.web.ModelAndViewAssert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.security.Principal;

import kr.co.insoft.AbstractTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;

public class SecurityControllerTest extends AbstractTest {

	private static final Logger logger = LoggerFactory
			.getLogger(SecurityControllerTest.class);

	Principal principal = new Principal() {
		@Override
		public String getName() {
			return "goodwilldd";
		}
	};

	@Test
	public void loginTest() throws Exception {
		MvcResult result = mockMvc.perform(
				post("/sec/login.htm").param("sec_userid", "goodwilldd").param(
						"sec_password", "goodwilldd")).andReturn();

		mockMvc.perform(get("/").principal(principal)).andExpect(
				status().isOk());
	}
}
