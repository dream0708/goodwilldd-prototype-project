package com.phillit.pez.board.model;

import com.phillit.pez.common.model.CommonModel;

public class BoardParentModel extends CommonModel {
	protected String boardName;

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
}
