package kr.co.insoft.board.entity;

import java.util.ArrayList;

public class PagingEntity {

	public int PAGE_SIZE;
	public int BLOCK_SIZE;

	private int totalCount;
	private int currentPageIndex;

	public PagingEntity(int totalCount, int currentPageIndex, int pageSize, int pagingSize) {
		this.totalCount = totalCount;
		this.currentPageIndex = currentPageIndex;
		this.PAGE_SIZE = pageSize;
		this.BLOCK_SIZE = pagingSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getCurrentPageNum() {
		return currentPageIndex;
	}

	public int getTotalPages() {
		return (int) Math.ceil((double) totalCount / PAGE_SIZE);
	}

	public int getStartPageNumForBoard() {
		return getStartPageNum() - 1 <= 0 ? 0 : getStartPageNum() - 1;
	}

	public int getStartPageNum() {
		return totalCount == 0 ? 0 : ((currentPageIndex - 1) * PAGE_SIZE) + 1;
	}

	public int getEndPageNum() {
		return (getStartPageNum() + PAGE_SIZE - 1) > totalCount ? totalCount
				: (getStartPageNum() + PAGE_SIZE - 1);
	}

	public int getPrePageNum() {
		return (currentPageIndex - 1) <= 0 ? 1 : (currentPageIndex - 1);
	}

	public int getNextPageNum() {
		return (currentPageIndex + 1) > getTotalPages() ? getTotalPages()
				: (currentPageIndex + 1);
	}

	public int getRealStartPageNum() {
		return (totalCount - ((currentPageIndex - 1) * PAGE_SIZE));
	}

	public int getRealEndPageNum() {
		return getRealStartPageNum() - PAGE_SIZE + 1 < 0 ? 1
				: getRealStartPageNum() - PAGE_SIZE + 1;
	}

	public int getPreviousBlock() {
		return getStartBlock() - 1 <= 1 ? 1 : getStartBlock() - 1;
	}

	public int getNextBlock() {
		return getEndBlock() + 1 > getTotalPages() ? getTotalPages()
				: getEndBlock() + 1;
	}

	public int getCurrentBlock() {
//		return (int) Math.ceil((double) ((getCurrentPageNum() - 1) / BLOCK_SIZE)) + 1;
		return (int) Math
				.ceil((double) ((getCurrentPageNum()) / BLOCK_SIZE)) + 1;
	}

	public int getStartBlock() {
		return ((getCurrentBlock() - 1) * BLOCK_SIZE) + 1;
	}

	public int getEndBlock() {
		return (getStartBlock() + BLOCK_SIZE - 1) > getTotalPages() ? getTotalPages()
				: (getStartBlock() + BLOCK_SIZE - 1);
	}

	public ArrayList<Integer> getPages() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = getStartBlock(); i <= getEndBlock(); i++) {
			result.add(i);
		}
		return result;
	}
}
