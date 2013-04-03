package kr.co.ujin.core.util;

import java.io.File;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.XMLPropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class CommonPropertiesUtil {

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
			e.printStackTrace();
		}
	}

	public String getString(String key) {
		return (String) properties.getProperty(key);
	}
	
	public int getInt(String key) {
		return properties.getInt(key);
	}
}
