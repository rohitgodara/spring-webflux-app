package com.catapi.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LogInForm {

	@NotBlank(message="{email.not.null}")
	@Pattern(regexp = "([a-z])+@([a-z])+\\.com", message = "{email.pattern.mismatch}")
	private String userName;
	
	@NotBlank(message="{password.not.null}")
	private String password;
	
}
