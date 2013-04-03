package kr.co.pdca;

import kr.co.ujin.service.main.service.FinanceService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaServiceTest {
	@Autowired
	FinanceService financeService;

	@Test
	@Transactional
	public void serviceTest() {
		financeService.findAll();
	}
}
