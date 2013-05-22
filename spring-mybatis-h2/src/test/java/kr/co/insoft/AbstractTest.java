package kr.co.insoft;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 모든 테스트의 베이스 클래스
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/root-context.xml",
		"/servlet-context.xml" })
@Profile("test")
@WebAppConfiguration
public abstract class AbstractTest {
	@Autowired
	protected WebApplicationContext wac;
	protected MockMvc mockMvc;

	@Configuration
	@ComponentScan(basePackageClasses = AbstractTest.class)
	static class Config {
	}
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
}
