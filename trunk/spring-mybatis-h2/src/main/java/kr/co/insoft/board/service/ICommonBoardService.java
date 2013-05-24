package kr.co.insoft.board.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.insoft.board.entity.DefaultListEntity;
import kr.co.insoft.board.exception.SaveException;

public interface ICommonBoardService<T> {

	public List<T> getList(DefaultListEntity<T> entity);

	public int getListCount(DefaultListEntity<T> entity);

	public DefaultListEntity<T> getListWithPaging(DefaultListEntity<T> entity);

	public int doSave(T t) throws SQLException, SaveException;

	public int doReply(T t) throws SQLException, SaveException;

	public boolean doDelete(T t);

	public T getDetails(int _seq);
}
