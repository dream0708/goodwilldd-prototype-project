package kr.co.insoft.security.service;

import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import kr.co.insoft.core.util.ObjectUtil;
import kr.co.insoft.security.entity.AuthenticationEntity;
import kr.co.insoft.security.entity.UserRole;
import kr.co.insoft.security.mapper.SecurityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	SecurityMapper securityMapper;

	@Autowired
	ObjectUtil objectUtil;

	@Override
	@DependsOn(value = { "securityMapper" })
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Vector<GrantedAuthority> userAuthorities = new Vector<GrantedAuthority>();
		List<UserRole> userRole = getUserRole(username);
		ListIterator<UserRole> userRoleLiterator = userRole.listIterator();
		while (userRoleLiterator.hasNext()) {
			String tempRole = userRoleLiterator.next().getAuthority();
			userAuthorities.add(new SimpleGrantedAuthority(tempRole));
		}

		try {
			AuthenticationEntity domainUser = getUser(username);
			if (objectUtil.isEmpty(domainUser)) {
				domainUser.setPassword("");
				domainUser.setUsername("");
			}

			boolean enabled = true;
			if (domainUser.isEnabled()) {
				enabled = true;
			} else {
				enabled = false;
			}

			boolean accountNonExpired = true;
			if (!StringUtils.hasLength(domainUser.getUsername())) {
				accountNonExpired = false;
			}
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			User user = new User(domainUser.getUsername(),
					domainUser.getPassword(), enabled, accountNonExpired,
					credentialsNonExpired, accountNonLocked, userAuthorities);
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public List<UserRole> getUserRole(String username) {
		return securityMapper.getUserRole(username);
	}

	public AuthenticationEntity getUser(String username) {
		return securityMapper.getUser(username);
	}
}
