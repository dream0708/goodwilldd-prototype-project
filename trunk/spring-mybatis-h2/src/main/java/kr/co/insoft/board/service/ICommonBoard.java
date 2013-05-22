package kr.co.insoft.board.service;

import java.util.List;

import kr.co.insoft.board.entity.DefaultListEntity;

public interface ICommonBoard<T> {

	public List<T> getList();

	public int getListCount();

	public DefaultListEntity<T> getListWithPaging();
	
	public boolean doSave(T t);

	public boolean doDelete(T t);

	public T getDetails(long _seq);
}
