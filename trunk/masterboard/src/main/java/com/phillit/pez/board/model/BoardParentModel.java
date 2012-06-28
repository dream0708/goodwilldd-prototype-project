package com.phillit.pez.board.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.phillit.pez.common.model.CommonModel;

@XmlRootElement(name = "board")
public class BoardParentModel extends CommonModel {
	
	protected String boardName;

	public String getBoardName() {
		return boardName;
	}

	@XmlElement
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
}
