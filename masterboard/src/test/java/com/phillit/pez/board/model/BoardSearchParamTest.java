package com.phillit.pez.board.model;

import org.junit.Test;
import org.springframework.util.StringUtils;

public class BoardSearchParamTest {
	@Test
	public void t1() {
		String[] t = "select count(bSeq) from board         where  isEnabled=0                            and boardName='1'".split(" ");
		StringBuilder result = new StringBuilder();
		for(String p : t) {
			System.out.println(p);
			if ( StringUtils.hasLength(p.trim()) ) {
				result.append(p + " ");
			}
		}
		System.out.println(result.toString());
	}
}
