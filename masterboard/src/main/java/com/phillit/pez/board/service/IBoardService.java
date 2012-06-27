package com.phillit.pez.board.service;

import com.phillit.pez.board.BoardController.COMMAND;
import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;

public interface IBoardService {
	/**
	 * 작성된 글을 저장/수정/처리한다.
	 * 
	 * @param data
	 * @param action 
	 * @param action 
	 * @return
	 */
	public boolean write(BoardDataModel data, COMMAND action);

	/**
	 * 해당 게시판의 목록을 가져온다
	 * 
	 * @param list
	 */
	public void getList(BoardListModel data);

	public BoardDataModel doRead(String bSeq);

}
