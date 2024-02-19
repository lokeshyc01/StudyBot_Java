package com.app.service;

import com.app.dto.UserDTO;

public interface UserService {
	public String resetPasswordToken(String email);
	
	public String  resetPassword(String password,String confirmPass,String token);
	public String singUp(UserDTO user);
}
