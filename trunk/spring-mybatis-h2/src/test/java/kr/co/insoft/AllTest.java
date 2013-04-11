package kr.co.insoft;

import kr.co.insoft.example.controller.ExampleController;
import kr.co.insoft.example.entity.Example;
import kr.co.insoft.example.mapper.normal.NExampleMapper;
import kr.co.insoft.example.service.ExampleService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class AllTest extends AbstractTest {

	private static final Log logger = LogFactory.getLog(AllTest.class);

	@Autowired
	ExampleController exampleController;

	@Autowired
	ExampleService exampleService;

	@Autowired
	NExampleMapper nExampleMapper;

	@Test
	public void testExampleController() throws Exception {
		exampleController.m();
	}

	@Test
	public void testExampleService() throws Exception {
		exampleService.getExamList2();
	}

	@Test
	public void testExampleMapper() throws Exception {
		for (Example e : nExampleMapper.getList()) {
			logger.info(e.toString());
		}
	}
}
