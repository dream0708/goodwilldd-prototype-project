package kr.co.insoft.mybatis.example.normal.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface ExampleMapper {
	public String getUserName(String userId);
}
