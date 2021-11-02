package com.catapi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catapi.constants.Message;
import com.catapi.dto.AssessmentRequestDTO;
import com.catapi.exception.InvalidRequestException;
import com.catapi.service.AssessmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("v1/assessment")
@CrossOrigin
public class AssessmentController {

	@Autowired
	private AssessmentService assessmentService;

	@GetMapping
	public ResponseEntity<Object> assesment(@RequestParam(required = false) UUID assessmentId) {
		log.info("find all assessment....");
		if(assessmentId !=null) {
			return ResponseEntity.ok(assessmentService.findById(assessmentId));
		}
		return ResponseEntity.ok(assessmentService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Object> register(@RequestBody AssessmentRequestDTO dto) {
		log.info("saving assessment....");
		if(dto == null || dto.getQuestionLimit() < 0 || dto.getTitle() == null || dto.getTitle().isEmpty()) {
			throw new InvalidRequestException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_PARAMETER);
		}
		return ResponseEntity.ok(assessmentService.register(dto));
	}
}