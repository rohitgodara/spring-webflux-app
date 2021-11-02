package com.catapi.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.catapi.dto.TestQuestionResponsesDTO;
import com.catapi.entity.TestQuestionResponses;

import reactor.core.publisher.Flux;

public interface TestQuestionResponsesRepository extends ReactiveSortingRepository<TestQuestionResponses,UUID> {

	Flux<TestQuestionResponsesDTO> findAllByTestId(UUID id);

}