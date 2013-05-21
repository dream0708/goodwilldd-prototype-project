package kr.co.insoft.board.service;

import java.util.List;

import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.entity.DefaultListEntity;
import kr.co.insoft.board.mapper.CommonBoardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonBoard implements ICommonBoard<DefaultDetailEntity> {

	@Autowired
	CommonBoardMapper commonBoardMapper;

	@Override
	@Transactional(readOnly = true)
	public List<DefaultDetailEntity> getList() {
		return commonBoardMapper.getList();
	}

	@Override
	@Transactional(readOnly = true)
	public long getListCount() {
		return commonBoardMapper.getListCount();
	}

	@Override
	@Transactional(readOnly = true)
	public DefaultListEntity<DefaultDetailEntity> getListWithPaging() {
		DefaultListEntity<DefaultDetailEntity> result = new DefaultListEntity<>();
		result.setCount(getListCount());
		result.setList(getList());
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public DefaultDetailEntity getDetails(long _seq) {
		return commonBoardMapper.getDetails(_seq);
	}

	@Override
	public boolean doSave(DefaultDetailEntity entity) {
		return commonBoardMapper.doSave(entity) > 0;
	}

	@Override
	public boolean doDelete(DefaultDetailEntity entity) {
		return commonBoardMapper.doDelete(entity) > 0;
	}
}
