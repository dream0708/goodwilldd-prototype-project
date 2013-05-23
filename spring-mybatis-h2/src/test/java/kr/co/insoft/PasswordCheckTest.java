package kr.co.insoft;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.springframework.test.web.ModelAndViewAssert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordCheckTest extends AbstractTest {

private static final Logger logger = LoggerFactory
		.getLogger(PasswordCheckTest.class);
	
	@Autowired
	Md5PasswordEncoder password;
	
	@Test
	public void doCheckPass() {
		logger.info(password.encodePassword("vocalstars", null));
	}
}
