package kr.co.pdca.core.util;

import kr.co.pdca.core.util.realize.CommonPropertiesUtil;
import kr.co.pdca.core.util.realize.ObjectUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 아직 사용하지 말 것!!
 * @author Administrator
 *
 */
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
