package com.example.demo.segurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;


@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

	private final UserDetailsService userDetailsService;
	
	public WebSecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtAuthorizationFilter = new JWTAuthorizationFilter();
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}
	
	private final JWTAuthorizationFilter jwtAuthorizationFilter;	

	public JWTAuthorizationFilter getJwtAuthorizationFilter() {
		return jwtAuthorizationFilter;
	}	
	

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		
		
		
		JWTAuthicationFilter jwtAuthicationFilter= new JWTAuthicationFilter();
		jwtAuthicationFilter.setAuthenticationManager(authManager);
		jwtAuthicationFilter.setFilterProcessesUrl("/login");
		
		
		
		
		return http.csrf().disable().authorizeRequests().anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(jwtAuthicationFilter)
				.addFilterBefore(jwtAuthorizationFilter,UsernamePasswordAuthenticationFilter.class)
				
				.build();
	}

	@Bean
	AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder).and().build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

}
