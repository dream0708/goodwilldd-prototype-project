package kr.co.insoft.example.mapper.normal;

import java.util.List;

import kr.co.insoft.example.entity.Example;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

@Repository
public interface NExampleMapper {

	@Select(value = "select * from tb_example where seq=#{seq}")
	Example getExam(long seq);

	@SelectKey(before = true, keyProperty = "seq", resultType = Long.class, statement = "select coalesce(max(seq), 0) from tb_example", statementType = StatementType.PREPARED)
	@Insert(value = "insert into tb_example(seq, username) values(#{seq}, #{username}")
	void save(Example exam);

	@Select(value = "select * from tb_example")
	List<Example> getList();

}
