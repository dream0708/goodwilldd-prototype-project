package kr.co.insoft.mybatis.example.normal.mapper;

import kr.co.insoft.core.database.CoreMapper;

import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

@Repository
@Caching
public interface ExampleMapper extends CoreMapper {
	public String getUserName(String userId);
}
