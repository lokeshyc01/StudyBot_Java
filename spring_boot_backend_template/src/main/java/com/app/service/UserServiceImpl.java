package com.app.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private EmailSenderService emailService;

	public String resetPasswordToken(String email) {

		User user = userRepo.findByEmail().orElseThrow();
		if (user == null) {
			return "User Not found";
		}
		
		
		String token = UUID.randomUUID().toString();
		user.setToken(token);
		System.out.println(token);
		
//		setting the reset password token expires in time
		LocalDateTime resetPasswordExpires = LocalDateTime.now().plus(5, ChronoUnit.MINUTES);
		user.setResetPasswordExpires(resetPasswordExpires);
		
		userRepo.save(user);
		String url = "http://localhost:3000/update-password/" + token;
		
		emailService.sendEmail(email, "Password reset Link", url);
		return "Password token sent";
	}

	public String resetPassword(String password,String confirmPassword,String token) 
	{
		if(!password.equals(confirmPassword))
		{
			return "Both password should be same";
		}
		
		User user = userRepo.findByToken(token).orElseThrow();
		if(user == null)
		{
			return "Token not valid";
		}
		
		if(user.getResetPasswordExpires().compareTo(LocalDateTime.now()) == -1)
		{
			return "token expired";
		}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		user.setPassword(hashedPassword);
		userRepo.save(user);
				
		return "Password reset";
	}

}
