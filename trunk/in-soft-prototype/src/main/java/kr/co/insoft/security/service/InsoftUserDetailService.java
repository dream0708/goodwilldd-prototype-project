package kr.co.insoft.security.service;

import kr.co.insoft.security.model.AuthenticationModel;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InsoftUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		StandardPasswordEncoder encoder = new StandardPasswordEncoder();

		AuthenticationModel auth = new AuthenticationModel();

		auth.setUsername(username);
		auth.setPassword(encoder.encode("abcd"));

		return auth;
	}

}
