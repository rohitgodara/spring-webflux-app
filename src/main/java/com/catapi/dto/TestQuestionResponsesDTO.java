package com.catapi.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestQuestionResponsesDTO {
	private UUID id;
	@JsonIgnore
	private UUID testId;
	private UUID questionId;
	private String answer;
	private boolean isCorrect;
	private float newAbility;
	private int sequence;
	private float discrimination;
	private float difficulty;
	private float successProbability;
	private LocalDate createdOn;
	private LocalDate modifiedOn;
	private String testQuestionResponsesCol;

}
