package kr.co.pdca.core.mapper.normal;

import java.util.Map;

import kr.co.pdca.security.entity.UserRole;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface InitializationMapper {
	@Select(value = "SELECT count(*) FROM tb_init")
	public int doCheckTables();

	@Insert(value = "CREATE TABLE TB_INIT(SEQ INT, INIT_DATE TIMESTAMP NOT NULL)")
	public void doCreateTable_INIT();

	@Insert(value = "CREATE TABLE TB_AUTH(USERNAME VARCHAR(20), UPASSWORD VARCHAR(100))")
	public void doCreateTable_AUTH();

	@Insert(value = "CREATE TABLE TB_AUTH_ROLE(USERNAME VARCHAR(20), AUTHORITY VARCHAR(20))")
	public void doCreateTable_AUTH_ROLE();

	@Insert(value = "INSERT INTO TB_AUTH_ROLE(USERNAME, AUTHORITY) VALUES(#{username}, #{authority})")
	public void doInsertDefaultRole(UserRole user);
	
	@Insert(value = "INSERT INTO TB_AUTH(USERNAME, UPASSWORD) VALUES(#{username}, #{upassword})")
	public void doDefaultDatas(Map<String, String> datas);

}
