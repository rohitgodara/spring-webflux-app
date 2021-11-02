package com.catapi.entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rohit.godara
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class UserQuestionResponsesForCalibration implements Persistable<UUID> {

	@Id
	@Column("id")
	private UUID id;
	private boolean isNew;
	private UUID userId;
	private UUID assessmentId;
	private UUID questionId;
	private int isCorrect;
	private Date createdOn;
	private Date modifiedOn;
	
}
