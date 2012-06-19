package com.phillit.pez.board.model;

public class BoardListModel extends BoardParentModel {

	public BoardListModel() {

	}

	public BoardListModel(String boardName) {
		this.boardName = boardName;
	}

	private int currentPageNum;
	private String searchValue;
	private String searchField;

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

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
}
