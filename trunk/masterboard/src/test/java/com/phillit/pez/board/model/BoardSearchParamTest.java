package com.phillit.pez.board.model;

import org.junit.Test;

public class BoardSearchParamTest {
	@Test
	public void t1() {
		BoardSearchParam p = BoardSearchParam.valueOfIgnoreCase(BoardSearchParam.class, "subject");
		System.out.println(p.name().equals("subject"));
		System.out.println(p.getItem().equals("subject"));
	}
}
