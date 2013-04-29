package kr.co.insoft;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 모든 테스트의 베이스 클래스
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/root-context.xml",
		"/appServlet/servlet-context.xml" })
public abstract class AbstractTest {
	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;

	@Configuration
	@ComponentScan(basePackageClasses = AbstractTest.class)
	static class Config {
	}
	
	@Before
	public void setup() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
}
