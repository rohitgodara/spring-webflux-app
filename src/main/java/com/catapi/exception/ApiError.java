package com.catapi.exception;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

	private int status;
	private List<String> errors;
	private String message;
	Date timestamp = new Date(new Timestamp(System.currentTimeMillis()).getTime());

	public ApiError() {
		super();
	}

	public ApiError(HttpStatus status, List<String> errors) {
		this.status = status.value();
		this.message = status.getReasonPhrase();
		this.errors = errors;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}