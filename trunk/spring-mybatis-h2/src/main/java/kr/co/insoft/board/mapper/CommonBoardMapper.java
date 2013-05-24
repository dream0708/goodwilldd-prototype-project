package kr.co.insoft.board.mapper;

import java.sql.SQLException;
import java.util.List;

import kr.co.insoft.board.entity.DefaultDetailEntity;
import kr.co.insoft.board.entity.DefaultListEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonBoardMapper<T> {

	public int doSave(DefaultDetailEntity entity) throws SQLException;

	public int doReStepUpdate(DefaultDetailEntity entity);

	@Delete(value = "DELETE FROM TB_BOARD_DATA WHERE bseq = #{bseq}")
	public int doDelete(DefaultDetailEntity entity);

	@Many
	@Select(value = "SELECT bseq, boardName, register, subject, content, reStep, reLevel, FORMATDATETIME(regdate, 'yyyy-MM-dd') as regdate FROM TB_BOARD_DATA WHERE boardName=#{boardName} ORDER BY reStep desc")
	public List<DefaultDetailEntity> getList(DefaultListEntity<T> t);

	@Select(value = "SELECT COUNT(bseq) FROM TB_BOARD_DATA WHERE boardName=#{boardName}")
	public int getListCount(DefaultListEntity<T> t);

	@Select(value = "SELECT bseq, boardName, register, subject, content, reStep, reLevel, FORMATDATETIME(regdate, 'yyyy-MM-dd') as regdate FROM TB_BOARD_DATA WHERE bseq = #{_seq}")
	public DefaultDetailEntity getDetails(int _seq);

}
