package com.app.dto;

import lombok.Setter;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
	@NotBlank
	public String firstName;
	@NotBlank
	public String lastName;
	@NotBlank
	public String email;
	@NotBlank
	public String password;
	@NotBlank
	public String confirmPassword;
	@NotBlank
	public String accountType;
	@NotBlank
	public String contactNumber;
	@NotBlank
	public String otp;
	
}
