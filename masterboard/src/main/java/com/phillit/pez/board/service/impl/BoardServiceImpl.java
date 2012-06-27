package com.phillit.pez.board.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.phillit.pez.board.BoardController.COMMAND;
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
	public void getList(BoardListModel data) {
		try {
			data.setTotalCount(boardMapper.getListTotalCount(data));
			data.setPaging(new BoardPaging(data));
			data.setList(boardMapper.getList(data));
		} catch (SQLException e) {
			data.setTotalCount(0);
			data.setPaging(new BoardPaging(data));
			data.setList(new ArrayList<BoardDataModel>());
		}
	}

	@Override
	public BoardDataModel doRead(String bSeq) {
		try {
			return boardMapper.doRead(bSeq);
		} catch (SQLException e) {
			return new BoardDataModel();
		}
	}

	@Override
	@Transactional
	public boolean write(BoardDataModel data, COMMAND action) {
		try {
			switch (action) {
			default:
			case write:
				boardMapper.doWrite(data);
				break;
			case modify:
				boardMapper.doModify(data);
				break;
			case reply:
				boardMapper.doReStepUpdate(data.getbSeq());
				boardMapper.doWrite(data);
				break;
			case delete:
				boardMapper.doDelete(data.getbSeq());
				break;
			}
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
}
