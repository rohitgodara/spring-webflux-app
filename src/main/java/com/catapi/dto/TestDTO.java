package com.catapi.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {
	private UUID id;
	private String code;
	private String userName;
	private UUID userId;
	private int status;
	private UUID assessmentId;
	private int noOfQuestions;
	private Float initialAbility;
	private Float finalAbility;
	private LocalDate createdOn;
	private LocalDate modifiedOn;
}
