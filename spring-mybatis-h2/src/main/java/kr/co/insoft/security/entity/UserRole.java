package kr.co.insoft.security.entity;

import java.io.Serializable;

public class UserRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5268491225812750784L;

	public UserRole() {

	}

	public UserRole(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}

	String username;
	String authority;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
