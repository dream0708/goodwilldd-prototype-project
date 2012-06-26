package com.phillit.pez.board.service;

import java.util.ArrayList;

import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;

public interface IBoardService {
	/**
	 * 작성된 글을 저장한다.
	 * @param data
	 * @return
	 */
	public boolean write(BoardDataModel data);

	/**
	 * 해당 게시판의 목록을 가져온다
	 * @param list 
	 * @return
	 */
	public ArrayList<BoardDataModel> getList(BoardListModel list);
}
