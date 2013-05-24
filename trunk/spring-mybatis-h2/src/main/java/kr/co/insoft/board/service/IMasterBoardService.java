package kr.co.insoft.board.service;

import java.util.List;
import java.util.Map;

public interface IMasterBoardService {
	/**
	 * 게시판 목록 및 해당 게시판 정보
	 * 
	 * @return
	 */
	public List<Map<String,?>> getList();
	
}
