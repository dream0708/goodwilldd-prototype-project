package kr.pe.goodwilldd.example.mapper.normal;

import kr.pe.goodwilldd.core.database.CoreMapper;

import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

@Repository
@Caching
public interface ExampleMapper extends CoreMapper {
	public String getUserName(String userId);
}
