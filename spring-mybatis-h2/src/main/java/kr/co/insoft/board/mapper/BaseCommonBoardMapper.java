package kr.co.insoft.board.mapper;

import java.util.List;

public interface BaseCommonBoardMapper<T> {

	public int doSave(T entity);

	public int doDelete(T entity);

	public List<T> getList();

	public int getListCount();

	public T getDetails(long _seq);
}
