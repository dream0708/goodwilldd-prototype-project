package kr.co.insoft.security.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kr.co.insoft.mybatis.example.service.ExampleServiceIF;
import kr.co.insoft.security.model.AuthenticationModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InsoftUserDetailService implements UserDetailsService {
	/*
	 * 참고 하여 수정 
	 * http://stackoverflow.com/questions/11844446/spring-security-autowired-userdetailsservice 
	 */
	@Autowired
	ExampleServiceIF exampleService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		StandardPasswordEncoder encoder = new StandardPasswordEncoder();

		AuthenticationModel auth = new AuthenticationModel();

		auth.setUsername(username);
		auth.setPassword(encoder.encode("abcd"));

		/*String sql = "select * from user where email like :username";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("username", username);

		SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(getDataSource());
		User user = sjt.queryForObject(sql, new UserMapper(), source);*/
		
		exampleService.getUserName(username);
		return auth;
	}

	private Collection<? extends GrantedAuthority> getAuthorities(
			boolean isAdmin) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		if (isAdmin) {
			authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		}
		return authList;
	}

	private class UserMapper implements ParameterizedRowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			return new User(rs.getString("user_id"), rs.getString("password"),
					true, true, true, true,
					getAuthorities(rs.getBoolean("admin")));
		}

	}
}
