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

/**
 * <pre>
 * Validation 을 확인을 위한 최상위 클래스
 * </pre>
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 * @param <T>
 */
public class AbstractValidationTest<T> {

	private static final Log logger = LogFactory
			.getLog(AbstractValidationTest.class);

	protected static Validator validator;

	@BeforeClass
	public static void classSetup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * Validataion 정상 수행 체크
	 * @param t
	 */
	public void assertValid(T t) {
		assertTrue(isValid(t));
	}

	/**
	 * Validation 실패 수행 체크
	 * @param t
	 */
	public void assertInValid(T t) {
		assertFalse(isValid(t));
	}

	/**
	 * Validation 성공 여부 반환
	 * @param t
	 * @return
	 */
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
