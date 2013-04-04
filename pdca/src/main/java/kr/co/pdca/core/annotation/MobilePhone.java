package kr.co.pdca.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.constraints.Size;

import kr.co.pdca.core.annotation.validator.MobilePhoneValidator;

import org.springframework.integration.annotation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobilePhoneValidator.class)
@Size(min = 12, max = 13)
public @interface MobilePhone {
	String message() default "{validation.mobilephone.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
