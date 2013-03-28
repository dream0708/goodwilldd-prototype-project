package kr.co.pdca.core.util.realize;

import javax.annotation.PostConstruct;

import kr.co.pdca.core.util.properties.CustomReloadingStrategy;

import org.apache.commons.configuration.XMLPropertiesConfiguration;
import org.junit.Assert;

public class CommonPropertiesUtil {

	XMLPropertiesConfiguration properties = null;

	@PostConstruct
	public void init() {
		try {
			properties = new XMLPropertiesConfiguration();
			properties.load(getClass().getResourceAsStream(
					"/properties/common-properties.xml"));

			properties.setReloadingStrategy(new CustomReloadingStrategy(
					1000 * 60 * 60));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getString(String key) {
		Assert.assertNotNull(properties);
		return (String) properties.getProperty(key);
	}
}
