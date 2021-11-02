package com.catapi.response.wrapper;

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
public class LoginResponseWrapper {

	private UUID userId;

	private String userName;

	private String name;

	private String roleName;

}
