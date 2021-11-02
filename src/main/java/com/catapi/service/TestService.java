package com.catapi.service;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.catapi.constants.Message;
import com.catapi.dto.TestDTO;
import com.catapi.entity.Test;
import com.catapi.exception.TestNotFoundException;
import com.catapi.exception.UserNotFoundException;
import com.catapi.repository.AssessmentRepository;
import com.catapi.repository.TestQuestionResponsesRepository;
import com.catapi.repository.TestRepository;
import com.catapi.repository.UserRepository;
import com.catapi.response.wrapper.TestResponseWrapper;

import reactor.core.publisher.Mono;

/**
 * @author rohit.godara
 *
 */
@Service
public class TestService {

	@Autowired 
	private TestRepository testRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AssessmentRepository assessmentRepository;
	@Autowired
	private TestQuestionResponsesRepository testQuestionResponsesRepository;

	public Mono<TestResponseWrapper> findById(UUID id) {
		return testRepository.findById(id).doOnSuccess(user -> {
			if (user == null) {
				throw new TestNotFoundException(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
			}
		}).flatMap(test -> testQuestionResponsesRepository.findAllByTestId(test.getId()).collectList()
				.map(testQuestionResponses -> TestResponseWrapper.builder().id(test.getId()).code(test.getCode())
						.userId(test.getUserId()).status(test.getStatus()).assessmentId(test.getAssessmentId())
						.initialAbility(test.getInitialAbility()).finalAbility(test.getFinalAbility())
						.createdOn(test.getCreatedOn()).modifiedOn(test.getModifiedOn())
						.questions(testQuestionResponses).build()));
	}

	public Mono<TestDTO> createTest(String userName, UUID assessmentId) {
		String testCode = generateRandomTestCode();
		return userRepository.findByUserName(userName).flatMap(user -> {
			if (user.getId() != null) {
				return assessmentRepository.findById(assessmentId).flatMap(assessment -> {
					if (assessment.getId() != null) {
						return testRepository.save(new Test(UUID.randomUUID(), testCode, user.getId(), 1,
								assessment.getId(), 0f, 0f, LocalDate.now(), LocalDate.now(), true)).map(testEntity -> {
									TestDTO testDto = new TestDTO();
									testDto.setId(testEntity.getId());
									testDto.setCode(testEntity.getCode());
									testDto.setUserName(userName);
									testDto.setUserId(testEntity.getUserId());
									testDto.setStatus(testEntity.getStatus());
									testDto.setAssessmentId(testEntity.getAssessmentId());
									testDto.setNoOfQuestions(assessment.getQuestionLimit());
									testDto.setInitialAbility(testEntity.getInitialAbility());
									testDto.setFinalAbility(testEntity.getFinalAbility());
									testDto.setCreatedOn(testEntity.getCreatedOn());
									testDto.setModifiedOn(testEntity.getModifiedOn());
									return testDto;
								});
					} else {
						return Mono.error(
								new UserNotFoundException(HttpStatus.NOT_FOUND, Message.ASSESSMENT_NOT_FOUND_FOR_TEST));
					}

				}).switchIfEmpty(Mono
						.error(new UserNotFoundException(HttpStatus.NOT_FOUND, Message.ASSESSMENT_NOT_FOUND_FOR_TEST)));
			} else {
				return Mono.error(new UserNotFoundException(HttpStatus.NOT_FOUND, Message.USER_NOT_FOUND_FOR_TEST));
			}
		}).switchIfEmpty(Mono.error(new UserNotFoundException(HttpStatus.NOT_FOUND, Message.USER_NOT_FOUND_FOR_TEST)));

	}

	public String generateRandomTestCode() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
}
