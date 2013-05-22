package kr.co.insoft.board.service;

import java.util.List;

import kr.co.insoft.board.entity.DefaultListEntity;

public interface ICommonBoard<T> {

	public List<T> getList(DefaultListEntity<T> entity);

	public int getListCount(DefaultListEntity<T> entity);

	public DefaultListEntity<T> getListWithPaging(DefaultListEntity<T> entity);
	
	public boolean doSave(T t);

	public boolean doDelete(T t);

	public T getDetails(long _seq);
}
