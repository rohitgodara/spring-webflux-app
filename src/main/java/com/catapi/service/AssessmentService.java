package com.catapi.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.catapi.constants.Message;
import com.catapi.dto.AssessmentRequestDTO;
import com.catapi.entity.Assessments;
import com.catapi.exception.UserNotFoundException;
import com.catapi.repository.AssessmentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AssessmentService {

	@Autowired
	private AssessmentRepository assessmentRepository;

	private LocalDate localdate = LocalDate.now();

	public Flux<Assessments> findAll() {
		return assessmentRepository.findAll();

	}

	public Mono<Assessments> findById(UUID id) {
		return assessmentRepository.findById(id).doOnSuccess(assessment -> {
			if (assessment == null) {
				throw new UserNotFoundException(HttpStatus.NOT_FOUND, Message.ASSESSMENT_NOT_FOUND_FOR_TEST);
			}
		});
	}

	public Mono<Assessments> register(AssessmentRequestDTO dto) {
		return assessmentRepository
				.save(new Assessments(UUID.randomUUID(), dto.getTitle(), dto.getQuestionLimit(), localdate, localdate, true));

	}
}