package kr.co.insoft.board.service.internal;

import java.sql.SQLException;
import java.util.List;

import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.entity.DefaultListEntity;
import kr.co.insoft.board.exception.SaveException;
import kr.co.insoft.board.mapper.CommonBoardMapper;
import kr.co.insoft.board.service.ICommonBoardService;
import kr.co.insoft.core.util.CommonPropertiesUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonBoardService implements
		ICommonBoardService<DefaultDetailEntity> {

	@Autowired
	CommonBoardMapper<DefaultDetailEntity> commonBoardMapper;

	@Autowired
	CommonPropertiesUtil commonPropertiesUtil;

	@Override
	public List<DefaultDetailEntity> getList(
			DefaultListEntity<DefaultDetailEntity> entity) {
		return commonBoardMapper.getList(entity);
	}

	@Override
	public int getListCount(DefaultListEntity<DefaultDetailEntity> entity) {
		return commonBoardMapper.getListCount(entity);
	}

	@Override
	public DefaultListEntity<DefaultDetailEntity> getListWithPaging(
			DefaultListEntity<DefaultDetailEntity> entity) {
		int pageSize = commonPropertiesUtil.getInt("DEFAULT_PAGE_SIZE");
		int pagingSize = commonPropertiesUtil.getInt("DEFAULT_PAGING_SIZE");

		DefaultListEntity<DefaultDetailEntity> result = new DefaultListEntity<>();
		int totalCount = getListCount(entity);
		result.setCount(totalCount);
		result.setList(getList(entity));
		result.setPaging(totalCount, entity.getCurrentPageIndex(), pageSize,
				pagingSize);
		return result;
	}

	@Override
	public DefaultDetailEntity getDetails(int _seq) {
		return commonBoardMapper.getDetails(_seq);
	}

	@Override
	public int doSave(DefaultDetailEntity entity) throws SQLException,
			SaveException {
		try {
			commonBoardMapper.doSave(entity);
		} catch (SQLException sqlE) {
			throw sqlE;
		}

		if (entity.getBseq() <= 0) {
			throw new SaveException("fail");
		}

		return entity.getBseq();
	}

	@Override
	public int doReply(DefaultDetailEntity entity) throws SQLException,
			SaveException {
		try {
			commonBoardMapper.doReStepUpdate(entity);
			commonBoardMapper.doSave(entity);
		} catch (SQLException sqlE) {
			throw sqlE;
		}

		if (entity.getBseq() <= 0) {
			throw new SaveException("fail");
		}

		return entity.getBseq();
	}

	@Override
	public boolean doDelete(DefaultDetailEntity entity) {
		return commonBoardMapper.doDelete(entity) > 0;
	}
}
