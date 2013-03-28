package kr.co.pdca.core.util;

import kr.co.pdca.core.util.realize.CommonPropertiesUtil;
import kr.co.pdca.core.util.realize.ObjectUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 아직 사용하지 말 것!!
 * 
 * @author Administrator
 * 
 */
@Configuration
public class UtilFactory {

	@Autowired
	ObjectUtil objectUtil;

	@Autowired
	CommonPropertiesUtil commonPropertiesUtil;

	@Bean
	public ObjectUtil getObjectUtil() {
		return objectUtil;
	}

	@Bean
	public CommonPropertiesUtil getCommonPropertiesUtil() {
		return commonPropertiesUtil;
	}
}
