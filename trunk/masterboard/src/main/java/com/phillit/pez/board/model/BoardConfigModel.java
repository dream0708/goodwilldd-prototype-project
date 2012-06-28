package com.phillit.pez.board.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BoardConfigModel {
	private int pagePerSize;
	private int blockSize;

	public int getPagePerSize() {
		return pagePerSize;
	}

	@XmlElement
	public void setPagePerSize(int pagePerSize) {
		this.pagePerSize = pagePerSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	@XmlElement
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

}
