package kr.co.insoft.core.annotation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public @interface TService {
	String value() default "";
}
