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
	 */
	public void getList(BoardListModel list);

	/**
	 * 해당 게시판의 총 게시물 수를 반환한다.
	 * @param list
	 */
	public void getListTotalCount(BoardListModel list);
}
