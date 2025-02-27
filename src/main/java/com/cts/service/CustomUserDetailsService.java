package com.cts.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cts.exception.UserNotFoundException;
import com.cts.model.CustomUserDetails;
import com.cts.model.User;
import com.cts.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username);
		//System.out.println(user);
		if(user==null) {
			throw new UserNotFoundException("User not found!");
		}
		
		GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_"+user.getUserRole());
		return new CustomUserDetails(user.getUserId(),user.getUserName(),
				encoder.encode(user.getPassword()),
				Collections.singleton(auth));
	}
}
