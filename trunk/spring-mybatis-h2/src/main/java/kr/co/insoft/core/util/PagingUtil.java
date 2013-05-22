package kr.co.insoft.core.util;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

public class PagingUtil {

	@Autowired
	CommonPropertiesUtil commonPropertiesUtil;

	public int PAGE_SIZE = commonPropertiesUtil
			.getInt("DEFAULT_PAGE_SIZE");
	public int BLOCK_SIZE = commonPropertiesUtil
			.getInt("DEFAULT_PAGING_SIZE");

	private int totalCount;
	private int currentPageIndex;

	public PagingUtil(int totalCount, int currentPageIndex) {
		this.totalCount = totalCount;
		this.currentPageIndex = currentPageIndex;
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
		return (int) Math
				.ceil((double) ((getCurrentPageNum() - 1) / BLOCK_SIZE)) + 1;
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
