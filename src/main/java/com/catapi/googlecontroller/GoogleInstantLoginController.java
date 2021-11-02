package com.catapi.googlecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catapi.constants.Message;
import com.catapi.dto.UserRequestDTO;
import com.catapi.exception.InvalidRequestException;
import com.catapi.service.GoogleLoginService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("v1/google")
@Slf4j
public class GoogleInstantLoginController {

	@Autowired
	private GoogleLoginService googleLoginService;

	@Operation(summary = "Validate Google Credentials")
	@PostMapping("validate")
	public ResponseEntity<Object> validate(@RequestBody UserRequestDTO dto) {

		if (dto == null || dto.getEmail() == null || dto.getEmail().isEmpty() || dto.getName() == null
				|| dto.getName().isEmpty() || dto.getToken() == null || dto.getToken().isEmpty()) {
			throw new InvalidRequestException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_PARAMETER);
		}
		log.info("Request entered in GoogleInstantLoginController");
		return ResponseEntity.ok(googleLoginService.updateUserInDatabase(dto));
	}
}
