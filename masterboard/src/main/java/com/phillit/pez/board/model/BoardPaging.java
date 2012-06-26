package com.phillit.pez.board.model;

import com.phillit.pez.common.util.PagingUtil;

public class BoardPaging extends PagingUtil {
	
	public BoardPaging(BoardListModel data) {
		this(data.getTotalCount(), data.getCurrentPageNum());
	}
	
	public BoardPaging(BoardListModel data, BoardConfigModel config) {
		this(data.getTotalCount(), data.getCurrentPageNum(), config.getPagePerSize(), config.getBlockSize());
	}
	
	protected BoardPaging(int totalCount, int currentPageNum) {
		super(totalCount, currentPageNum);
	}
	
	protected BoardPaging(int totalCount, int currentPageNum, int pagePerSize,
			int blockSize) {
		super(totalCount, currentPageNum, pagePerSize, blockSize);
	}
}
