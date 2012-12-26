package com.phillit.prototype.pez.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class PropertiesUtil {

	private static PropertiesUtil instance;

	public static PropertiesUtil getInstance() {
		if(instance == null) {
			instance = new PropertiesUtil();
		}

		return instance;
	}

	private static final String PROPERTIES = "/config/system/allowedURIList.properties";
	private Properties allowed;

	private PropertiesUtil() {
		allowed = new Properties();
		try {
			InputStream is = this.getClass().getResourceAsStream(PROPERTIES);
			allowed.load(is);
			is.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getAllowed() {
		List<String> result = new ArrayList<String>();
		String tmp = null;
		Enumeration<Object> en = allowed.keys();
		Object ke = null;
		while(en.hasMoreElements()) {
			ke = en.nextElement();
			if(ke.toString().startsWith("allowedURIList")) {
				tmp = allowed.get(ke).toString();
				result.add(tmp);
			}
		}
		return result;
	}
}
