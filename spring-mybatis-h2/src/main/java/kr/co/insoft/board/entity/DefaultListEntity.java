package kr.co.insoft.board.entity;

import java.io.Serializable;
import java.util.List;

public class DefaultListEntity<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2236939402132001219L;
	private long count;
	private List<T> list;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
