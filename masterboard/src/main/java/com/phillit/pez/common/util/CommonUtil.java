package com.phillit.pez.common.util;

import org.springframework.util.StringUtils;

public abstract class CommonUtil {
	public static String removeWhiteSpace(String _str) {
		_str = _str.replaceAll("\t", " ");
		_str = _str.replaceAll("\n", " ");
		_str = _str.replaceAll("\r", " ");
		String[] t = _str.split(" ");
		StringBuilder result = new StringBuilder();
		for (String p : t) {
			if (StringUtils.hasLength(p.trim())) {
				result.append(p.trim() + " ");
			}
		}
		return result.toString();
	}
}
