package kr.co.pdca;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kr.co.pdca.example.entity.Example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "/root-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryTest extends CommonLogging {

	@PersistenceContext
	private EntityManager entityManager;

	Example example;

	@Before
	public void setup() {
		example = new Example();
		example.setEmail("kr.goodwilldd@gmail.com");
		example.setMobilePhone("010-6315-3535");
		example.setUsername("GoodwillDD");
	}

	@Test
	@Transactional
	public void repositoryTest() {
		entityManager.persist(example);
		entityManager.flush();
	}
}
