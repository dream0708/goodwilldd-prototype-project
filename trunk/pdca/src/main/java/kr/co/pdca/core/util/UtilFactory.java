package kr.co.pdca.core.util;

import kr.co.pdca.core.util.realize.ObjectUtil;
import kr.co.pdca.core.util.realize.CommonPropertiesUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilFactory {

	@Bean
	public ObjectUtil objectUtil() {
		return new ObjectUtil();
	}
	
	@Bean
	public CommonPropertiesUtil commonPropertiesUtil() {
		return new CommonPropertiesUtil();
	}
}
