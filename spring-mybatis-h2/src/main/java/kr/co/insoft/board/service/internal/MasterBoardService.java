package kr.co.insoft.board.service.internal;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.insoft.board.mapper.MasterBoardMapper;
import kr.co.insoft.board.service.IMasterBoardService;

import org.springframework.stereotype.Service;

@Service
public class MasterBoardService implements IMasterBoardService {

	@Resource
	MasterBoardMapper masterBoardMapper;
	
	@Override
	public List<Map<String, ?>> getList() {
		return masterBoardMapper.getList();
	}

}
