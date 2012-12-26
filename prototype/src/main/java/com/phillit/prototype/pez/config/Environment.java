package com.phillit.prototype.pez.config;

import java.io.File;

import com.phillit.prototype.pez.util.PropertiesUtil;

public interface Environment {

	String USER_HOME = System.getProperty("user.home");
	String FILE_SEPERATOR = File.separator;
	String CONFIG_FILE_FULLPATH = USER_HOME + FILE_SEPERATOR + "server_environment.xml";
	PropertiesUtil allowedURL = PropertiesUtil.getInstance();
}
