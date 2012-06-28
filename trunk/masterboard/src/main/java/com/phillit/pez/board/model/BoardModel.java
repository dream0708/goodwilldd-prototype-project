package com.phillit.pez.board.model;

import javax.xml.bind.annotation.XmlElement;

public class BoardModel extends BoardParentModel {
	private int bseq;
	
	private int boardOrder;

	public int getBseq() {
		return bseq;
	}

	@XmlElement
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}

	public int getBoardOrder() {
		return boardOrder;
	}

	@XmlElement
	public void setBoardOrder(int boardOrder) {
		this.boardOrder = boardOrder;
	}

}
