package kr.co.insoft.core.annotation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import kr.co.insoft.core.annotation.MobilePhone;

import org.springframework.util.StringUtils;

/**
 * <pre>
 * 핸드폰 번호가 지정된 형식이 맞는지 체크하는 Validator
 * </pre>
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 * 
 */
public class MobilePhoneValidator implements
		ConstraintValidator<MobilePhone, String> {

	Pattern pattern;

	@Override
	public void initialize(MobilePhone constraintAnnotation) {
		this.pattern = Pattern.compile("^[0-9]\\d{2}-(\\d{3}|\\d{4})-\\d{4}$");
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (!StringUtils.hasLength(value))
			return true;

		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

}
