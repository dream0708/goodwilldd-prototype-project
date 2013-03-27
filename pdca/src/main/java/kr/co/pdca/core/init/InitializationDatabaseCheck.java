package kr.co.pdca.core.init;

import kr.co.pdca.core.service.CoreService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class InitializationDatabaseCheck {

	private static final Log logger = LogFactory
			.getLog(InitializationDatabaseCheck.class);

	@Autowired
	CoreService coreService;

	@Bean
	@DependsOn(value = { "coreService" })
	public boolean init() {
		try {
			logger.info("***** check tables *****");
			coreService.doInitializationDatabase();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
