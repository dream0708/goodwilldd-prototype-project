package kr.co.insoft.security.mapper;

import kr.co.insoft.security.model.AuthenticationModel;

import org.springframework.stereotype.Repository;

@Repository
public interface SecurityMapper {
	public AuthenticationModel getUser(String username);
}
