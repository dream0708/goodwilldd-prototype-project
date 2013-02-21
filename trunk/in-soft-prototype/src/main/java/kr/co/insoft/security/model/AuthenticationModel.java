package kr.co.insoft.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kr.co.insoft.contants.ROLES;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationModel implements UserDetails {

	private String username;
	private String password;
	private boolean lock;
	private boolean accountExpired;
	private boolean credentialExpried;
	private boolean use;

	/**
	 * 
	 */
	private static final long serialVersionUID = 6566746604750997139L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(ROLES.USER.getRoleName()));
		return authorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountExpired;
	}

	public void setAccountNonExpried(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setAccountNonLocked(boolean lock) {
		this.lock = lock;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.lock;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialExpried;
	}

	public void setCredentialsNonExpried(boolean credentialExpried) {
		this.credentialExpried = credentialExpried;
	}

	@Override
	public boolean isEnabled() {
		return this.use;
	}

	public void setEnabled(boolean use) {
		this.use = use;
	}

	public void setEnabled() {
		setEnabled(true);
	}

	public void setDisabled() {
		setEnabled(false);
	}
}
