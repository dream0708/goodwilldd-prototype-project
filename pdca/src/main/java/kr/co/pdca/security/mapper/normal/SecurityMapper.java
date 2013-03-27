package kr.co.pdca.security.mapper.normal;

import java.util.List;
import java.util.Map;

import kr.co.pdca.core.mapper.CoreMapper;
import kr.co.pdca.security.entity.AuthenticationEntity;
import kr.co.pdca.security.entity.UserRole;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityMapper extends CoreMapper {
	@Select(value = "SELECT * FROM TB_AUTH WHERE USERNAME=#{username}")
	public AuthenticationEntity getUser(String username);

	@Select(value = "SELECT * FROM TB_AUTH")
	public List<Map<String, String>> getUserList();

	@Select(value = "SELECT USERNAME, AUTHORITY FROM TB_AUTH_ROLE WHERE USERNAME=#{username}")
	public List<UserRole> getUserRole(String username);
}
