package com.catapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catapi.form.UserForm;
import com.catapi.service.UserService;

/**
 * @author rohit.godara
 *
 */
@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<Object> register(@RequestBody UserForm form) {
		return ResponseEntity.ok(service.register(form));
	}

	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping()
	public ResponseEntity<Object> findAllUsers(@RequestParam("page") int page, @RequestParam("size") int size) {
		return ResponseEntity.ok(service.findAllUsers(PageRequest.of(page, size)));
	}
	
	@GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Object> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

}
