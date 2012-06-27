package com.phillit.pez.board.model;

import java.util.ArrayList;

public class BoardListModel extends BoardParentModel {

	public BoardListModel() {

	}

	public BoardListModel(String boardName) {
		this.boardName = boardName;
	}

	private int currentPageNum = 1;
	private int totalCount;
	private String searchValue;
	private BoardSearchParam searchField;
	private ArrayList<BoardDataModel> list;
	private BoardPaging paging;

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public BoardSearchParam getSearchField() {
		return searchField;
	}

	public void setSearchField(BoardSearchParam searchField) {
		this.searchField = searchField;
	}

	public ArrayList<BoardDataModel> getList() {
		return list;
	}

	public void setList(ArrayList<BoardDataModel> list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public BoardPaging getPaging() {
		return paging;
	}

	public void setPaging(BoardPaging paging) {
		this.paging = paging;
	}

	public int getStartPageBoard() {
		return paging.getStartPageBoard();
	}

	public int getPagePerSize() {
		return paging.getPagePerSize();
	}

}
