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
public class CalibratedQuestions implements Persistable<UUID> {

	@Id
	@Column("id")
	private UUID id;
	private float discrimination;
	private float difficulty;
	private float guessing;
	private float asymptote;
	private UUID assessmentId;
	private UUID questionId;
	private Date createdOn;
	private Date modifiedOn;
	private boolean isNew;
	
}
