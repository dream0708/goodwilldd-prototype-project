package kr.co.insoft.security.normal.mapper;

import kr.co.insoft.core.database.CoreMapper;
import kr.co.insoft.security.model.AuthenticationModel;

import org.springframework.stereotype.Repository;

@Repository
public interface SecurityMapper extends CoreMapper {
	public AuthenticationModel getUser(String username);
}
