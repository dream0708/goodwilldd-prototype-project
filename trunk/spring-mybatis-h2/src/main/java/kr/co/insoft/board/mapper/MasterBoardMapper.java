package kr.co.insoft.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterBoardMapper {
	/**
	 * 게시판 목록 및 해당 정보 반환
	 * @return
	 */
	@Select(value = "SELECT bseq, boardName, boardDesc, boardListSize, boardListPageSize, boardType, reStep, reLevel, FROM TB_BOARD_DATA WHERE ORDER BY reStep desc")
	public List<Map<String, ?>> getList();
}
