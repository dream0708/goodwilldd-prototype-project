package kr.co.pdca;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTest {

	private static final Log logger = LogFactory.getLog(LoginTest.class);

	@Test
	public void login() {
		logger.info("***** Test *****");
	}
}
