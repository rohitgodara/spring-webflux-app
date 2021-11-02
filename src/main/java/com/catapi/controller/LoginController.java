package com.catapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catapi.constants.Message;
import com.catapi.exception.InvalidCredentialsException;
import com.catapi.form.LogInForm;
import com.catapi.repository.RoleRepository;
import com.catapi.repository.UserRepository;
import com.catapi.response.wrapper.LoginResponseWrapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@RestController
@Log4j2
@RequestMapping("login")
public class LoginController {

	@Autowired
	private ReactiveAuthenticationManager authManager;

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@ApiOperation(value = "authenticate user based on given creadentials", response = LoginResponseWrapper.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PostMapping(produces = "application/json")
	public Mono<ResponseEntity<LoginResponseWrapper>> login(@Valid @RequestBody LogInForm loginForm) {
		return authenticate(loginForm);
	}

	private Mono<ResponseEntity<LoginResponseWrapper>> authenticate(LogInForm loginForm) {
		return authManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUserName(), loginForm.getPassword()))
				.doOnError(error -> {
					log.error("error while authenticating user for given id {} ", loginForm.getUserName(), error);
					if (error instanceof BadCredentialsException)
						throw new InvalidCredentialsException(HttpStatus.BAD_REQUEST, Message.INVALID_CREDENTIALS);
				}).flatMap(authenticate -> repository.findByUserName(loginForm.getUserName()))
				.flatMap(user -> roleRepository.findById(user.getRoleId())
						.map(role -> ResponseEntity.ok(new LoginResponseWrapper(user.getId(), user.getUserName(),
								user.getName(), role.getName()))));
	}
	
}
