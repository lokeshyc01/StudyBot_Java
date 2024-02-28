package com.app.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
	
	String extractUserName(String token);
	
	String generateToken(UserDetails userdetails);
	
	boolean isTokenValid(String token, UserDetails userDetails);
	 
}
