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
	
	@NotNull(message = "field.isnotnull")
	private String register;
	
	private int readNum;
	private int ref;
	private int step;
	private int depth;
	private int childCount;
	private int isEnabled;

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

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	public int getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}
}
