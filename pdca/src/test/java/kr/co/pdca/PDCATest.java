package kr.co.pdca;

import junit.framework.Assert;
import kr.co.pdca.core.util.UtilFactory;

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
public class PDCATest {

	private static final Log logger = LogFactory.getLog(PDCATest.class);

	@Autowired
	Md5PasswordEncoder passwordEncoder;

	@Autowired
	UtilFactory utilFactory;

	@Test
	public void passwordEncoderTest() {
		logger.info("goodwilldd "
				+ passwordEncoder.encodePassword("goodwilldd", null));
		logger.info("ssamkj " + passwordEncoder.encodePassword("ssamkj", null));
	}

	@Test
	public void UtilFactorySingletonTest() {
		Assert.assertEquals(utilFactory.objectUtil(), utilFactory.objectUtil());
	}
	
	@Test
	public void CommonPropertiesTest() {
		Assert.assertNotNull((utilFactory.commonPropertiesUtil()).getString("test"));
		logger.info((utilFactory.commonPropertiesUtil()).getString("test"));
	}
}
