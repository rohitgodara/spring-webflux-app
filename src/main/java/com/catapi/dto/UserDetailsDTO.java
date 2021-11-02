package com.catapi.dto;

import java.util.UUID;

import org.springframework.data.annotation.Transient;

import com.catapi.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class UserDetailsDTO {

	private UUID id;

	private String userName;

	@Transient
	@JsonIgnore
	private String password;

	private String name;

	private Role role;

	private boolean active;

}
