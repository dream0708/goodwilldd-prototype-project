package com.phillit.pez.board.service.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;

public interface IBoardMapper {
	public int doWrite(BoardDataModel data) throws SQLException;

	public int doUpdateToRef(BoardDataModel data) throws SQLException;

	public ArrayList<BoardDataModel> getList(BoardListModel list)
			throws SQLException;
}
