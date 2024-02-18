package com.app.service;

public interface UserService {
	public String resetPasswordToken(String email);
	
	public String  resetPassword(String password,String confirmPass,String token);

}
