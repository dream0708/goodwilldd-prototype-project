package com.phillit.pez.board.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;
import com.phillit.pez.board.service.IBoardService;
import com.phillit.pez.board.service.dao.IBoardMapper;

public class BoardServiceImpl implements IBoardService {

	private IBoardMapper boardMapper;

	public IBoardMapper getBoardMapper() {
		return boardMapper;
	}

	public void setBoardMapper(IBoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	@Transactional
	public boolean write(BoardDataModel data) {
		try {
			boardMapper.doWrite(data);
			boardMapper.doUpdateToRef(data);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<BoardDataModel> getList(BoardListModel list) {
		ArrayList<BoardDataModel> result = new ArrayList<>();
		try {
			result = boardMapper.getList(list);
		} catch (SQLException e) {
			return new ArrayList<>();
		}
		return result;
	}

}
