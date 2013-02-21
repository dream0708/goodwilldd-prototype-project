package kr.co.insoft.mybatis.example.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface ExampleMapper {
	public String getUserName(String userId);
}
