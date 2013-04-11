package kr.co.insoft;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
	@Configuration
	@ComponentScan(basePackages = { "kr.co.insoft" })
	static class Config {
	}
}
