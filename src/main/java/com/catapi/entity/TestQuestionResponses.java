package com.catapi.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author rohit.godara
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class TestQuestionResponses implements Persistable<UUID> {

	@Id
	@Column("id")
	private UUID id;
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
	@Transient
	@Getter(onMethod_ = { @JsonIgnore })
	private boolean isNew;

}
