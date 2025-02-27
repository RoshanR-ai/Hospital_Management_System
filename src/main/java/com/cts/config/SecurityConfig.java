package com.cts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cts.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	private final CustomAuthenticationSuccessHandler successHandler;
	
	public SecurityConfig(CustomAuthenticationSuccessHandler successHandler) {
		this.successHandler = successHandler;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
        				.requestMatchers("/login","/error").permitAll()
        				.requestMatchers("/register","/register/addPatient").permitAll()
        				.requestMatchers("/","/home","/register","/resources/**","/loginaction","/WEB-INF/jsp/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/patient/**").hasRole("PATIENT")
                        .anyRequest().authenticated()
        )
        .formLogin(login -> login
        				.loginPage("/login").permitAll()
        				.loginProcessingUrl("/loginaction")
                        .successHandler(successHandler)
                        .usernameParameter("userName")
                        .passwordParameter("password")
                        .failureUrl("/login?error=true")
                        .permitAll()
        )
        .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
        )
        .exceptionHandling(handling -> handling
                .accessDeniedPage("/unauthorizedaccess")
         )
        .csrf(csrf->csrf.disable());
        
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailsServive() {
		return new CustomUserDetailsService();
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
