package kr.co.insoft.security.service;

import java.util.ArrayList;
import java.util.Collection;

import kr.co.insoft.security.mapper.SecurityMapper;
import kr.co.insoft.security.model.AuthenticationModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsoftUserDetailService implements UserDetailsService {
	/*
	 * 참고 하여 수정
	 * http://stackoverflow.com/questions/11844446/spring-security-autowired
	 * -userdetailsservice
	 */
	SecurityMapper securityMapper;

	@Autowired
	public InsoftUserDetailService(SecurityMapper securityMapper) {
		this.securityMapper = securityMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		AuthenticationModel auth = securityMapper.getUser(username);
		if (auth == null)
			throw new UsernameNotFoundException(username);

		return prepare(auth);
	}

	private User prepare(AuthenticationModel user) {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (GrantedAuthority auth : user.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
		}

		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
				user.isCredentialsNonExpired(), user.isAccountNonLocked(), authorities);
	}
}
