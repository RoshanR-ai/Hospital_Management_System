package com.cts.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User{
	
	private int userId;

	public CustomUserDetails(int userId,String username, String password,Collection<? extends GrantedAuthority> authorities) {
		super(username, password,authorities);
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "CustomUserDetails [userId=" + userId + ", getUserId()=" + getUserId() + ", getAuthorities()="
				+ getAuthorities() + ", getPassword()=" + getPassword() + ", getUsername()=" + getUsername()
				+ ", isEnabled()=" + isEnabled() + ", isAccountNonExpired()=" + isAccountNonExpired()
				+ ", isAccountNonLocked()=" + isAccountNonLocked() + ", isCredentialsNonExpired()="
				+ isCredentialsNonExpired() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}

}
