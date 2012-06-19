package com.phillit.pez.board.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class BoardDataModel extends BoardParentModel {

	public BoardDataModel() {

	}

	public BoardDataModel(String boardName, String register) {
		this.boardName = boardName;
		this.register = register;
	}

	private int bSeq;

	@NotNull(message = "field.isnotnull")
	@Size(min = 1, message = "field.min1")
	private String subject;

	@NotNull(message = "field.isnotnull")
	@Size(min = 1, message = "field.min1")
	private String content;

	@DateTimeFormat(style = "S-")
	@Future
	private Date regdate = new Date(new Date().getTime() + 31536000000L);
	private String register;

	public int getbSeq() {
		return bSeq;
	}

	public void setbSeq(int bSeq) {
		this.bSeq = bSeq;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}
}
