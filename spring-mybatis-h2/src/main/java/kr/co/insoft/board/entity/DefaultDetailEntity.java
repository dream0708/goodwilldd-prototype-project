package kr.co.insoft.board.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotEmpty;

public class DefaultDetailEntity {
	private int bseq;
	@NotNull
	private String boardName;

	@NotEmpty
	@NotNull
	@Size(min=2)
	private String subject;

	private String content;
	private String register;
	private String regDate;
	private long readNum;
	private int enabled;
	private long reLevel;
	private long reStep;

	public String toDebug() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public int getBseq() {
		return bseq;
	}

	public void setBseq(int bseq) {
		this.bseq = bseq;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
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

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public long getReadNum() {
		return readNum;
	}

	public void setReadNum(long readNum) {
		this.readNum = readNum;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public long getReLevel() {
		return reLevel;
	}

	public void setReLevel(long reLevel) {
		this.reLevel = reLevel;
	}

	public long getReStep() {
		return reStep;
	}

	public void setReStep(long reStep) {
		this.reStep = reStep;
	}

}
