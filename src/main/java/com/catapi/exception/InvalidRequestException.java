package com.catapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidRequestException extends ResponseStatusException {

	private static final long serialVersionUID = 9050383505676031938L;

	public InvalidRequestException(HttpStatus status, String message) {
		super(status, message);
	}

}