package com.phillit.pez.board.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement
public class BoardDataModel extends BoardParentModel {

	public BoardDataModel() {

	}

	public BoardDataModel(String boardName, String register) {
		this.boardName = boardName;
		this.register = register;
	}

	private int bSeq;

	@NotNull(message = "field.isnull")
	@Size(min = 1, message = "field.min1")
	private String subject;

	@NotNull(message = "field.isnull")
	@Size(min = 1, message = "field.min1")
	private String content;

	@DateTimeFormat(style = "S-")
	@Future
	private Date regdate = new Date(new Date().getTime() + 31536000000L);

	@NotNull(message = "field.isnull")
	private String register;

	private int readNum;

	private int reLevel;

	private long reStep;

	private int isEnabled;

	public int getbSeq() {
		return bSeq;
	}

	@XmlElement
	public void setbSeq(int bSeq) {
		this.bSeq = bSeq;
	}

	public String getSubject() {
		return subject;
	}

	@XmlElement
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	@XmlElement
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getRegister() {
		return register;
	}

	@XmlElement
	public void setRegister(String register) {
		this.register = register;
	}

	public int getReadNum() {
		return readNum;
	}

	@XmlElement
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	public int getIsEnabled() {
		return isEnabled;
	}

	@XmlElement
	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}

	public int getReLevel() {
		return reLevel;
	}

	@XmlElement
	public void setReLevel(int reLevel) {
		this.reLevel = reLevel;
	}

	public long getReStep() {
		return reStep;
	}

	@XmlElement
	public void setReStep(long reStep) {
		this.reStep = reStep;
	}
}
