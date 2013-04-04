package kr.co.pdca.core.annotation.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.BeforeClass;

public class AbstractValidationTest<T> {

	private static final Log logger = LogFactory
			.getLog(AbstractValidationTest.class);

	protected static Validator validator;

	@BeforeClass
	public static void classSetup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public void assertValid(T t) {
		assertTrue(isValid(t));
	}

	public void assertInValid(T t) {
		assertFalse(isValid(t));
	}

	public boolean isValid(T t) {
		Set<ConstraintViolation<T>> violations = validate(t);
		for (ConstraintViolation<T> violation : violations) {
			logger.debug(violation.toString());
		}
		return (violations.size() == 0);
	}

	private Set<ConstraintViolation<T>> validate(T t) {
		return validator.validate(t);
	}
}
