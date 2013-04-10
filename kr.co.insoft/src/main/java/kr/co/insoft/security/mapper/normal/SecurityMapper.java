package kr.co.insoft.security.mapper.normal;

import java.util.List;

import kr.co.insoft.security.entity.AuthenticationEntity;
import kr.co.insoft.security.entity.UserRole;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityMapper {
	@Select(value = "SELECT * FROM TB_AUTH WHERE USERNAME=#{username}")
	public AuthenticationEntity getUser(String username);

	@Select(value = "SELECT * FROM TB_AUTH_ROLE WHERE USERNAME=#{username}")
	public List<UserRole> getUserRole(String username);
}
