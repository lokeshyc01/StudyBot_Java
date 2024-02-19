package com.app.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.OTPRepository;
import com.app.dao.ProfileRepository;
import com.app.dao.UserRepository;
import com.app.dto.UserDTO;
import com.app.entities.OTP;
import com.app.entities.Profile;
import com.app.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private OTPRepository otprepository;
	@Autowired
	private EmailSenderService emailService;
	@Autowired
	private ProfileRepository profilerepository;
	
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

	@Override
	public String singUp(UserDTO user) {
		
		 
		 String firstName = user.getFirstName();
		 
		 String lastName = user.getLastName();
		 
		 String email = user.getEmail();
		 
		 String password = user.getPassword();
		 
		 String confirmPassword = user.getConfirmPassword();
		 
		 String accountType = user.getAccountType();
		 
		 String contactNumber = user.getContactNumber();
		 
		 String otp = user.getOtp();
		 
		 if (firstName == null || lastName == null || email == null || password == null || otp == null) {
	           return "All Fields are required";
	        }

	        // Check if password and confirm password match
	        if (!password.equals(confirmPassword)) {
	            return "Password and Confirm Password do not match. Please try again.";
	        }

	        // Check if user already exists
	        if (userRepo.isUserAvailableByEmail(email)) {
	            return "User already exists. Please sign in to continue.";
	        }
	        
	        OTP latestOtp = otprepository.findTopByEmailOrderByCreatedAtDesc(email);
	        if (latestOtp == null || !otp.equals(latestOtp.getOtp())) {
	            return "The OTP is not valid";
	        }
	        
	        String hashedPassword = new BCryptPasswordEncoder().encode(password);
	        
	        Profile profileDetails = profilerepository.save(new Profile());


	}

}
