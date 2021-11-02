package com.catapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catapi.constants.Message;
import com.catapi.entity.Role;
import com.catapi.exception.InvalidRequestException;
import com.catapi.repository.RoleRepository;

import io.swagger.v3.oas.annotations.Operation;

/**
 * @author rohit.godara
 *
 */
@RestController
@RequestMapping("v1/roles")
public class RoleController {

	@Autowired
	private RoleRepository repository;

	@Operation(summary = "Get Roles Details")
	@GetMapping()
	public ResponseEntity<Object> role(@RequestParam(required = false) UUID roleId) {
		if (roleId != null) {
			return ResponseEntity.ok(repository.findById(roleId));
		} else {
			return ResponseEntity.ok(repository.findAll());
		}
	}
	
	@Operation(summary = "Register New Role")
	@PostMapping
	public ResponseEntity<Object> register(@RequestBody String roleName) {
		if(roleName == null || roleName.isEmpty() || roleName.contains("}") || roleName.contains("{")) {
			throw new InvalidRequestException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_PARAMETER);
		}
		return ResponseEntity.ok(repository.save(new Role(UUID.randomUUID(), roleName, true)));
	}
}
