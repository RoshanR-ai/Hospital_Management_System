package com.cts.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		 List<GrantedAuthority> roles = (List<GrantedAuthority>) authentication.getAuthorities();

		 for(GrantedAuthority role:roles) {
			
			 if(role.getAuthority().equals("ROLE_ADMIN")) {
				 response.sendRedirect("/admin");
			 }
			 else if(role.getAuthority().equals("ROLE_DOCTOR")) {
				 response.sendRedirect("/doctor");
			 }
			 else if(role.getAuthority().equals("ROLE_PATIENT")) {
				 response.sendRedirect("/patient");
			 }
			 else {
				 response.sendRedirect("/error");
			 }
		 }
	}

}
