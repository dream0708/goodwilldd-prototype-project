package kr.co.pdca;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTest {

	private static final Log logger = LogFactory.getLog(LoginTest.class);

	@Autowired
	Md5PasswordEncoder passwordEncoder;
	
	@Test
	public void passwordEncoderTest() {
		logger.info("goodwilldd " + passwordEncoder.encodePassword("goodwilldd", null));
		logger.info("ssamkj " + passwordEncoder.encodePassword("ssamkj", null));
	}
	
}
