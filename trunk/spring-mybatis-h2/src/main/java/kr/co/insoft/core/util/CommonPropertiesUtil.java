package kr.co.insoft.core.util;

import java.io.File;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.XMLPropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * 공통 Properties 관리
 * </pre>
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 * 
 */
@Component
public final class CommonPropertiesUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonPropertiesUtil.class);
	XMLPropertiesConfiguration properties = null;

	@PostConstruct
	public void init() {
		try {
			properties = new XMLPropertiesConfiguration();
			URL url = getClass().getResource(
					"/properties/common-properties.xml");
			File propertiesFile = new File(url.getPath());
			properties.load(propertiesFile);
			FileChangedReloadingStrategy reload = new FileChangedReloadingStrategy();
			reload.setConfiguration(properties);
			reload.setRefreshDelay(1000L * 60L * 60L);
			properties.setReloadingStrategy(reload);
		} catch (Exception e) {
			logger.error("CommonPropertiesUtil \n\n{}", e);
		}
	}

	public String getString(String key) {
		return properties.getString(key);
	}

	public int getInt(String key) {
		return properties.getInt(key);
	}
}
