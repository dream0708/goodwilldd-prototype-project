package com.phillit.pez.board.service.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;
import com.phillit.pez.board.model.BoardModel;

public interface IBoardMapper {
	public int doWrite(BoardDataModel data) throws SQLException;

	public ArrayList<BoardDataModel> getList(BoardListModel list)
			throws SQLException;

	public int getListTotalCount(BoardListModel list) throws SQLException;
	
	public BoardModel getFirstBoard() throws SQLException;

	public void doModify(BoardDataModel data);

	public BoardDataModel doRead(BoardDataModel data);

	public BoardDataModel doRead(String bSeq) throws SQLException;

	public void doReStepUpdate(int bSeq);

	public void doDelete(int bSeq);
}
