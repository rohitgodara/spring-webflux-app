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
@Table("tests")
public class Test implements Persistable<UUID>{

	@Id
	@Column("id")
	private UUID id;
	private String code;
	private UUID userId;
	private int status;
	private UUID assessmentId;
	private float initialAbility;
	private float finalAbility;
	private LocalDate createdOn;
	private LocalDate modifiedOn;
	@Transient
	@Getter(onMethod_={@JsonIgnore})
	private boolean isNew;

	
}
