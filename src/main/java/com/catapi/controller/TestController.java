package com.catapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catapi.dto.TestDTO;
import com.catapi.response.wrapper.TestResponseWrapper;
import com.catapi.service.TestService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Mono;

/**
 * @author rohit.godara
 *
 */
@RestController
@RequestMapping("tests")
public class TestController {

	@Autowired
	private TestService testService;

	@ApiOperation(value = "get result for the given test code", response = TestResponseWrapper.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "not authorized"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "{id}/result", produces = "application/json")
	public Mono<ResponseEntity<Object>> result(@PathVariable("id") UUID id) {
		return testService.findById(id).map(ResponseEntity::ok);
	}
	
	@PostMapping
	public ResponseEntity<Object> createTest(@RequestBody TestDTO testDTO) {
		return ResponseEntity.ok(testService.createTest(testDTO.getUserName(),testDTO.getAssessmentId()));
	}
}