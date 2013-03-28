package kr.co.pdca;

import junit.framework.Assert;
import kr.co.pdca.core.util.CommonPropertiesUtil;
import kr.co.pdca.core.util.ObjectUtil;

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
	ObjectUtil objectUtil;

	@Autowired
	ObjectUtil objectUtil2;

	@Autowired
	CommonPropertiesUtil commonPropertiesUtil;

	@Autowired
	CommonPropertiesUtil commonPropertiesUtil2;

	@Test
	public void objectUtiltest() {
		Assert.assertNotNull(objectUtil);
		logger.info(objectUtil.isEmpty(null));
	}

	@Test
	public void singletonTest() {
		Assert.assertEquals(objectUtil, objectUtil2);
		Assert.assertEquals(commonPropertiesUtil, commonPropertiesUtil2);
	}

	@Test
	public void passwordEncoderTest() {
		logger.info("goodwilldd "
				+ passwordEncoder.encodePassword("goodwilldd", null));
		logger.info("ssamkj " + passwordEncoder.encodePassword("ssamkj", null));
	}

	@Test
	public void CommonPropertiesTest() {
		Assert.assertNotNull(commonPropertiesUtil.getString("test"));
		logger.info(commonPropertiesUtil.getString("test"));
	}
}
