package kr.co.insoft.board.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * List 형태의 기본 구조
 * 
 * @author GoodwillDD (kr.goodwilldd@gmail.com)
 * 
 * @param <T>
 */
public class DefaultListEntity<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2236939402132001219L;

	/**
	 * 전체 목록의 수
	 */
	private int count;
	
	/**
	 * 현재 페이지
	 */
	private int currentPageIndex;
	
	/**
	 * 게시판 이름
	 */
	private String boardName;

	/**
	 * 전체 목록 객체
	 */
	private List<T> list;

	/**
	 * 페이징 객체
	 */
	private PagingEntity paging;

	/**
	 * 게시물의 Ordering
	 */
	private HashMap<String, Integer> orders;
	private String searchType;
	private String searchValue;

	public PagingEntity getPaging() {
		return paging;
	}

	public void setPaging(int totalCount, int currentPageIndex, int pageSize,
			int pagingSize) {
		this.paging = new PagingEntity(totalCount, currentPageIndex, pageSize,
				pagingSize);
	}

	public HashMap<String, Integer> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<String, Integer> orders) {
		this.orders = orders;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
}
