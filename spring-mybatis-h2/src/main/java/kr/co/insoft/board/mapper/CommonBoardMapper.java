package kr.co.insoft.board.mapper;

import java.util.List;

import kr.co.insoft.board.entity.DefaultDetailEntity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonBoardMapper {

	@SelectKey(before = true, keyProperty = "bseq", statementType = StatementType.PREPARED, statement = "SELECT (IFNULL(MAX(BSEQ),0)+1) as bseq FROM TB_BOARD_DATA", resultType = Long.class)
	@Insert(value = "INSERT INTO TB_BOARD_DATA(bseq, boardName, register, subject, content, reStep, reLevel, regdate) SELECT #{bseq}, #{boardName}, #{register}, #{subject}, #{content}, ifnull(((floor(max(reStep)/100))+1)*100+99, 199) , 0, CURRENT_DATE() FROM TB_BOARD_DATA")
	public int doSave(DefaultDetailEntity entity);

	@Delete(value = "DELETE FROM TB_BOARD_DATA WHERE bseq = #{bseq}")
	public int doDelete(DefaultDetailEntity entity);

	@Many
	@Select(value = "SELECT * FROM TB_BOARD_DATA")
	public List<DefaultDetailEntity> getList();

	@Select(value = "SELECT COUNT(*) FROM TB_BOARD_DATA")
	public int getListCount();

	@Select(value = "SELECT * FROM TB_BOARD_DATA WHERE bseq = #{_seq}")
	public DefaultDetailEntity getDetails(long _seq);
}
