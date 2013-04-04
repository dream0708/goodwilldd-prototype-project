package kr.co.pdca;

import kr.co.pdca.core.annotation.test.AbstractValidationTest;
import kr.co.pdca.example.entity.Example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:/META-INF/spring/root-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AnnotationTest extends AbstractValidationTest<Example> {

	private static final Log logger = LogFactory.getLog(AnnotationTest.class);

	Example example;

	@Before
	public void setup() {
		example = new Example();
		example.setMobilePhone("010-6315-3535");
		example.setEmail("kr.goodwilldd@gmail.com");
		example.setUsername("goodwilldd");
		example.setSeq(1);
	}

	@After
	public void validationTest() {
		logger.info("***** validation result *****" + isValid(example));
	}

	@Test
	public void doValid() {
		Assert.assertNotNull(example);
		assertValid(example);
	}

	@Test
	public void doInValid() {
		Assert.assertNotNull(example);
		assertInValid(example);
	}
}
