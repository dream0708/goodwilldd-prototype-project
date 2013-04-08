package kr.co.pdca;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import kr.co.pdca.example.entity.Example;
import kr.co.pdca.example.service.ExampleService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("default")
@ContextConfiguration(locations = { "classpath:/META-INF/spring/root-context.xml" })
public class ExampleServiceTest {
	@Mock
	ExampleService exampleService;

	private static final Log logger = LogFactory
			.getLog(ExampleServiceTest.class);

	@PostConstruct
	public void setup() {
		logger.info("***************************");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void serviceTest() {
		when(exampleService.findAllByMyBatis()).thenReturn(
				new ArrayList<Example>());
	}
}
