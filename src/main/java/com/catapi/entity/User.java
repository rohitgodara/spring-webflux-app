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
@Table("users")
public class User  implements Persistable<UUID> {

	@Id
	@Column("id")
	private UUID id;
	@Column("user_name")
	private String userName;

	private String password;

	private String name;

	private UUID roleId;
	
	@Column("active")
	private boolean active;

	private LocalDate createdOn;

	private LocalDate modifiedOn;
	
	@Transient
	private boolean isNew;

}
