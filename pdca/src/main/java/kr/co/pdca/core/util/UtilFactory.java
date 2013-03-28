package kr.co.pdca.core.util;

import kr.co.pdca.core.util.realize.ObjectUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilFactory {

	@Bean
	public ObjectUtil objectUtil() {
		return new ObjectUtil();
	}
}
