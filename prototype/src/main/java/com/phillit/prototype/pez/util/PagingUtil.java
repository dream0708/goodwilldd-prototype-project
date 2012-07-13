package com.phillit.prototype.pez.util;

import java.util.ArrayList;

public class PagingUtil {
	private int totalCount;
	private int currentPageNum;
	private int pagePerSize;
	private int blockSize;

	/**
	 * 전체 카운터, 현재 페이지 번호, 페이지 별 글목록 수, 블럭 사이즈
	 * 
	 * @param totalCount
	 * @param currentPageNum
	 * @param pagePerSize
	 * @param blockSize
	 */
	public PagingUtil(int totalCount, int currentPageNum, int pagePerSize,
			int blockSize) {
		this.totalCount = totalCount;
		this.currentPageNum = currentPageNum;
		this.pagePerSize = pagePerSize;
		this.blockSize = blockSize;
	}

	/**
	 * 전체 카운터, 현재 페이지 번호, 기본 값 글목록 25개, 블럭 사이즈 10개
	 * 
	 * @param totalCount
	 * @param currentPageNum
	 */
	public PagingUtil(int totalCount, int currentPageNum) {
		this.totalCount = totalCount;
		this.currentPageNum = currentPageNum;
		this.pagePerSize = 25;
		this.blockSize = 10;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public int getPagePerSize() {
		return pagePerSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public int getTotalPages() {
		return (int) Math.ceil((double) totalCount / pagePerSize);
	}

	public int getStartPageNumForBoard() {
		return getStartPageNum() - 1 <= 0 ? 0 : getStartPageNum() - 1;
	}

	public int getStartPageNum() {
		return totalCount == 0 ? 0 : ((currentPageNum - 1) * pagePerSize) + 1;
	}

	public int getEndPageNum() {
		return (getStartPageNum() + pagePerSize - 1) > totalCount ? totalCount
				: (getStartPageNum() + pagePerSize - 1);
	}

	public int getPrePageNum() {
		return (currentPageNum - 1) <= 0 ? 1 : (currentPageNum - 1);
	}

	public int getNextPageNum() {
		return (currentPageNum + 1) > getTotalPages() ? getTotalPages()
				: (currentPageNum + 1);
	}

	public int getRealStartPageNum() {
		return (totalCount - ((currentPageNum - 1) * pagePerSize));
	}

	public int getRealEndPageNum() {
		return getRealStartPageNum() - pagePerSize + 1 < 0 ? 1
				: getRealStartPageNum() - pagePerSize + 1;
	}

	public int getPreviousBlock() {
		return getStartBlock() - 1 <= 1 ? 1 : getStartBlock() - 1;
	}

	public int getNextBlock() {
		return getEndBlock() + 1 > getTotalPages() ? getTotalPages()
				: getEndBlock() + 1;
	}

	public int getCurrentBlock() {
		return (int) Math
				.ceil((double) ((getCurrentPageNum() - 1) / blockSize)) + 1;
	}

	public int getStartBlock() {
		return ((getCurrentBlock() - 1) * blockSize) + 1;
	}

	public int getEndBlock() {
		return (getStartBlock() + blockSize - 1) > getTotalPages() ? getTotalPages()
				: (getStartBlock() + blockSize - 1);
	}

	public ArrayList<Integer> getPages() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = getStartBlock(); i <= getEndBlock(); i++) {
			result.add(i);
		}
		return result;
	}
}
