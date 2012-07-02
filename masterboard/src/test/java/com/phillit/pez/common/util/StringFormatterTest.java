package com.phillit.pez.common.util;

import org.junit.Test;

public class StringFormatterTest {
	@Test
	public void t1() {
		String uri = "http://localhost:8080/masterboard/b/%s/list.xml?p=%s";
		System.out.println(String.format(uri, new String[] {"aaaa", "bbbb"}));
	}
}
