package kr.co.pdca;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import kr.co.ujin.core.mapper.normal.CommonMapper;
import kr.co.ujin.service.main.entity.Finance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "/root-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class JPATest {

	private static final Log logger = LogFactory.getLog(JPATest.class);

	@PersistenceContext
	EntityManager entityManager;

	@Test
	@Transactional
	public void insertTest() {
		Finance f = new Finance();
		f.setSeq(1L);
		f.setPay(10000);
		f.setWorker("goodwilldd");
		entityManager.persist(f);
		entityManager.flush();
		entityManager.clear();
		Finance f2 = (Finance) entityManager.find(Finance.class, 1L);
		assertNotNull(f2);
		logger.info(f2.getSeq());
	}

	@Autowired
	CommonMapper mapper;

	@Test
	@Transactional
	public void mybatisTest() {
		mapper.doInsertTest();
		mapper.getFinance(1);
	}
}
