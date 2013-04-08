package kr.co.pdca.example.mapper.normal;

import java.util.List;

import kr.co.pdca.example.entity.Example;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleMapper {

	@Select(value = "select * from tb_example")
	public List<Example> getExampleList();

	@SelectKey(before=true, keyProperty="seq", resultType=Long.class,statementType=StatementType.PREPARED, statement = { "select coalesce(max(seq), 1) from tb_example" })
	@Insert(value = "insert into tb_example(seq, username, email, mobilePhone) values(#{seq}, #{username}, #{email}, #{mobilePhone})")
	public void doInsert(Example example);
}
