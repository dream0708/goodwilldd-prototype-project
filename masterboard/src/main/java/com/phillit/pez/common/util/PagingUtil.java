package com.phillit.pez.common.util;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

import com.phillit.pez.common.model.CommonModel;

public class PagingUtil extends CommonModel {
	private int totalCount;
	private int currentPageNum;
	private int pagePerSize;
	private int blockSize;

	// Common
	private int nextPage;
	private int prePage;
	private int currentBlock;
	private int startBlock;
	private int endBlock;
	private int startPageBoard;

	// type1
	private int startPage;
	private int endPage;

	// type2
	private int nextBlock;
	private int preBlock;

	// inner
	private int totalPage;

	private ArrayList<Integer> pages;

	public PagingUtil() {
		
	}
	
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
		init();
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
		init();
	}

	public void init() {
		this.totalPage = (int) Math.ceil((double) this.totalCount
				/ this.pagePerSize);
		this.prePage = (this.currentPageNum - 1) <= 0 ? 1
				: (this.currentPageNum - 1);
		this.nextPage = (this.currentPageNum + 1) > this.totalPage ? this.totalPage
				: (this.currentPageNum + 1);
		this.startPage = totalCount == 0 ? 0
				: ((currentPageNum - 1) * pagePerSize) + 1;
		this.endPage = (this.startPage + this.pagePerSize - 1) > this.totalCount ? this.totalCount
				: (this.startPage + this.pagePerSize - 1);
		this.currentBlock = (int) Math
				.ceil((double) ((this.currentPageNum - 1) / blockSize)) + 1;
		this.startBlock = ((this.currentBlock - 1) * blockSize) + 1;
		this.endBlock = (this.startBlock + blockSize - 1) > this.totalPage ? this.totalPage
				: (this.startBlock + blockSize - 1);
		this.preBlock = this.startBlock - 1 <= 1 ? 1 : this.startBlock - 1;
		this.nextBlock = this.endBlock + 1 > this.totalPage ? this.totalPage
				: this.endBlock + 1;
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = this.startBlock; i <= this.endBlock; i++) {
			result.add(i);
		}
		this.pages = result;
		this.startPageBoard = this.startPage - 1 <= 0 ? 0 : this.startPage - 1;
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

	public int getNextPage() {
		return nextPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public int getCurrentBlock() {
		return currentBlock;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public int getEndBlock() {
		return endBlock;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public int getPreBlock() {
		return preBlock;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public ArrayList<Integer> getPages() {
		return pages;
	}

	public int getStartPageBoard() {
		return startPageBoard;
	}

	@XmlElement
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@XmlElement
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	@XmlElement
	public void setPagePerSize(int pagePerSize) {
		this.pagePerSize = pagePerSize;
	}

	@XmlElement
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	@XmlElement
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	@XmlElement
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	@XmlElement
	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}

	@XmlElement
	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}

	@XmlElement
	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}

	@XmlElement
	public void setStartPageBoard(int startPageBoard) {
		this.startPageBoard = startPageBoard;
	}

	@XmlElement
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	@XmlElement
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@XmlElement
	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	@XmlElement
	public void setPreBlock(int preBlock) {
		this.preBlock = preBlock;
	}

	@XmlElement
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@XmlElement
	public void setPages(ArrayList<Integer> pages) {
		this.pages = pages;
	}

}
