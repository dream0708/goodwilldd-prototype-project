package kr.co.pdca;

import kr.co.ujin.core.test.AbstractValidationTest;
import kr.co.ujin.service.main.entity.Customer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/root-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AnnotationTest extends AbstractValidationTest<Customer> {

	private static final Log logger = LogFactory.getLog(AnnotationTest.class);

	Customer customer;

	@Before
	public void setup() {
		this.customer = new Customer();
		this.customer.setCustomerName("1");
		this.customer.setEmail("kr.goodwilldd@gmail.com");
		this.customer.setMobile("010-6315-3535");
		this.customer.setTel("1");
	}

	@After
	public void validationTest() {
		logger.info("***** validation result *****" + isValid(customer));
	}

	@Test
	public void doValid() {
		Assert.assertNotNull(customer);
		assertValid(customer);
	}

	@Test
	public void doInValid() {
		Assert.assertNotNull(customer);
		assertInValid(customer);
	}

}
