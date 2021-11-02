package com.catapi.form;

import java.util.UUID;

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
public class UserForm {

	private UUID userRole;
	private String name;
	private String password;

}