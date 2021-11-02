package com.catapi.entity;

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
public class Questions implements Persistable<UUID> {

	@Id
	@Column("id")
	private UUID id;
	private String body;
	private String type;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private String answer;
	private boolean isNew;

}
