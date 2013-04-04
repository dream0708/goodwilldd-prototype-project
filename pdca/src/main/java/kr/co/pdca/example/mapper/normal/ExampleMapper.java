package kr.co.pdca.example.mapper.normal;

import java.util.List;

import kr.co.pdca.example.entity.Example;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleMapper {

	@Select(value = "select * from tb_example")
	public List<Example> getExampleList();
}
