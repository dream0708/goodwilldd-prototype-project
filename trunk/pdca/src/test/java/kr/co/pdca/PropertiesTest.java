package kr.co.pdca;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:/META-INF/spring/root-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PropertiesTest {

	@Value("#{applications['application.version']}")
	String driverClass;

	@Value("#{jdbc['jdbc.username']}")
	String t1;
	@Value("#{jdbc['jdbc.driverClass']}")
	String t2;
	@Value("#{jdbc['jdbc.url']}")
	String t3;

	private static final Log logger = LogFactory.getLog(PropertiesTest.class);

	@Test
	public void test1() {
		logger.info(driverClass);
		logger.info(t1);
		logger.info(t2);
		logger.info(t3);
	}

}
