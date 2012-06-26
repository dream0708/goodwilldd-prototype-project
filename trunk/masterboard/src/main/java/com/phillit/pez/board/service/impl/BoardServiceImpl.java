package com.phillit.pez.board.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.phillit.pez.board.model.BoardDataModel;
import com.phillit.pez.board.model.BoardListModel;
import com.phillit.pez.board.model.BoardPaging;
import com.phillit.pez.board.service.IBoardService;
import com.phillit.pez.board.service.dao.IBoardMapper;

public class BoardServiceImpl implements IBoardService {
	private static final Logger log = LoggerFactory
			.getLogger(BoardServiceImpl.class);

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
	public void getList(BoardListModel data) {
		try {
			if (!StringUtils.hasLength(data.getBoardName()))
				data.setBoardName(boardMapper.getFirstBoard().getBoardName());

			data.setTotalCount(boardMapper.getListTotalCount(data));
			data.setPaging(new BoardPaging(data));
			data.setList(boardMapper.getList(data));
		} catch (SQLException e) {
			data.setTotalCount(0);
			data.setPaging(new BoardPaging(data));
			data.setList(new ArrayList<BoardDataModel>());
		}
	}
}
