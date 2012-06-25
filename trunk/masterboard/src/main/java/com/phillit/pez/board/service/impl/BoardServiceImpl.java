package com.phillit.pez.board.service.impl;

import com.phillit.pez.board.model.BoardDataModel;
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
	public boolean write(BoardDataModel data) {
		
		return true;
//		return boardMapper.write(data) > 1;
	}

}
