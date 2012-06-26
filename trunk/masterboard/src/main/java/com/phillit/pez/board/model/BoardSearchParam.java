package com.phillit.pez.board.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum BoardSearchParam {
	subject("subject", "search.field.subject"), content("content",
			"search.field.content"), regdate("regdate", "search.field.regdate");

	private String item;
	private String localizationKey;

	BoardSearchParam(String item, String localizationKey) {
		this.item = item;
		this.localizationKey = localizationKey;
	}

	public static List<BoardSearchParam> asList() {
		return new ArrayList<BoardSearchParam>(
				EnumSet.allOf(BoardSearchParam.class));
	}

	/**
	 * Finds the value of the given enumeration by name, case-insensitive.
	 * Throws an IllegalArgumentException if no match is found.
	 **/
	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T valueOfIgnoreCase(Class<T> enumeration,
			String name) {
		for (Enum<?> enumValue : enumeration.getEnumConstants()) {
			if (enumValue.name().equalsIgnoreCase(name)) {
				return (T) enumValue;
			}
		}
		throw new IllegalArgumentException("There is no value with name '"
				+ name + " in Enum " + enumeration.getClass().getName());
	}

	public static boolean ieq(BoardSearchParam p, String target) {
		return BoardSearchParam.valueOfIgnoreCase(BoardSearchParam.class, p.name()).getItem().equalsIgnoreCase(target);
	}
	
	public static boolean eq(BoardSearchParam p, String target) {
		return BoardSearchParam.valueOfIgnoreCase(BoardSearchParam.class, p.name()).getItem().equals(target);
	}
	
	public String getLocalizationKey() {
		return localizationKey;
	}

	public void setLocalizationKey(String localizationKey) {
		this.localizationKey = localizationKey;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
}
