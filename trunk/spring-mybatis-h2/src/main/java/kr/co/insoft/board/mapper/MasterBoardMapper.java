package kr.co.insoft.board.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MasterBoardMapper {
	/**
	 * 게시판 목록 및 해당 정보 반환
	 * @return
	 */
	public List<Map<String, ?>> getList();
}
