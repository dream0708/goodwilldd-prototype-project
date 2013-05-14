package kr.co.insoft.board.entity;

public enum DefaultSearchEnum {
	SUBJECT("subject"), CONTENT("subject"), SUBJECT_CONTENT("subject"), REGISTER("subject"), REGDATE("subject"), ALL("subject");
	private String filedName;
	private String _value;
	private SearchOperator operator;
	private DefaultSearchEnum(String databaseField) {
		this.filedName = databaseField;
	}
}
