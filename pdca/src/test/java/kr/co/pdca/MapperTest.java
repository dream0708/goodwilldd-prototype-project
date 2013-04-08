package kr.co.pdca;

import kr.co.pdca.example.entity.Example;
import kr.co.pdca.example.mapper.normal.ExampleMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:/META-INF/spring/root-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTest extends CommonLogging {

	@Autowired
	ExampleMapper exampleMapper;

	Example example;

	@Before
	public void setup() {
		example = new Example();
		example.setEmail("kr.goodwilldd@gmail.com");
		example.setMobilePhone("01063153535");
		example.setUsername("GoodwillDD");
	}
	
	@Test
	public void mapperTest() {
		logging(exampleMapper.getExampleList());
	}
	
	@Test
	@Transactional
	public void transactionTest1() {
		exampleMapper.doInsert(example);
	}
	
	@Test
	@Transactional
	public void transactionTest2() {
		exampleMapper.doInsert(example);
		logging(exampleMapper.getExampleList());
	}
}
