package com.catapi.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
public class Assessments implements Persistable<UUID> {

	@Id
	@Column("id")
	private UUID id;
	private String title;
	
	@Column("questions_limit")
	private int questionLimit;
	private LocalDate createdOn;
	private LocalDate modifiedOn;
	
	@Transient
	private boolean isNew;
}
