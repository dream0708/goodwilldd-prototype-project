package com.phillit.pez.board.service;

import com.phillit.pez.board.model.BoardDataModel;

public interface IBoardService {
	/**
	 * 작성된 글을 저장한다.
	 * @param data
	 * @return
	 */
	public boolean write(BoardDataModel data);
}
