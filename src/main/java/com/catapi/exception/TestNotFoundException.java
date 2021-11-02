package com.catapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author rohit.godara
 *
 */
public class TestNotFoundException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestNotFoundException(HttpStatus status, String message) {
		super(status, message);
	}

}