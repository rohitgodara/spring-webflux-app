package com.catapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

import com.catapi.dto.UserDetailsDTO;
import com.catapi.repository.RoleRepository;
import com.catapi.repository.UserRepository;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ReactiveAuthenticationManager reactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(
				userDetailsService);
		authenticationManager.setPasswordEncoder(passwordEncoder);
		return authenticationManager;
	}

	@Bean
	public ReactiveUserDetailsService userDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
		return username -> userRepository.findByUserName(username)
				.flatMap(user -> roleRepository.findById(user.getRoleId())
						.map(role -> new UserDetailsDTO(user.getId(), user.getUserName(), user.getPassword(),
								user.getName(), role, user.isActive())))
				.map(userDetails -> User.withUsername(userDetails.getUserName()).password(userDetails.getPassword())
						.authorities(userDetails.getRole().getName()).accountExpired(!userDetails.isActive())
						.credentialsExpired(!userDetails.isActive()).disabled(!userDetails.isActive())
						.accountLocked(!userDetails.isActive()).build());

	}

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
		return http.csrf(CsrfSpec::disable).httpBasic(HttpBasicSpec::disable)
				.securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
				.authorizeExchange(
						it -> it.pathMatchers(HttpMethod.POST, "/login/**").permitAll().anyExchange().permitAll())
				.build();
	}
}
