package com.phillit.pez.board.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.phillit.pez.common.util.PagingUtil;

@XmlRootElement
public class BoardPaging extends PagingUtil {
	
	public BoardPaging() {
		
	}

	public BoardPaging(BoardListModel data) {
		this(data.getTotalCount(), data.getCurrentPageNum());
	}

	public BoardPaging(BoardListModel data, BoardConfigModel config) {
		this(data.getTotalCount(), data.getCurrentPageNum(), config
				.getPagePerSize(), config.getBlockSize());
	}

	protected BoardPaging(int totalCount, int currentPageNum) {
		super(totalCount, currentPageNum);
	}

	protected BoardPaging(int totalCount, int currentPageNum, int pagePerSize,
			int blockSize) {
		super(totalCount, currentPageNum, pagePerSize, blockSize);
	}
}
