package com.app.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.OTPRepository;
import com.app.dao.UserRepository;
import com.app.dto.UserDTO;
import com.app.entities.OTP;
import com.app.entities.Profile;
import com.app.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private JavaMailSender mailsender;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private OTPRepository otprepository;
	@Autowired
	private EmailSenderService emailService;
	@Autowired
	private UserRepository userrepository;
	
	public String resetPasswordToken(String email) {

		User user = userRepo.findByEmail(email).orElseThrow();
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
		
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		String hashedPassword = passwordEncoder.encode(password);
//		
		user.setPassword(password);
		userRepo.save(user);
				
		return "Password reset";
	}
	public static String generateOTP() {
        // Define the length of the OTP
        int otpLength = 6;

        // Characters allowed in the OTP
        String numbers = "0123456789";

        // Use StringBuilder to efficiently concatenate characters
        StringBuilder otp = new StringBuilder(otpLength);

        // Create a random object
        Random random = new Random();

        // Generate OTP by randomly selecting characters from the allowed set
        for (int i = 0; i < otpLength; i++) {
            int index = random.nextInt(numbers.length());
            char digit = numbers.charAt(index);
            otp.append(digit);
        }

        return otp.toString();
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
		 
		 System.out.println(firstName+""+lastName+""+email);
		 
		 if (firstName == null || lastName == null || email == null || password == null || otp == null) {
	           return "All Fields are required";
	        }

	        // Check if password and confirm password match
	        if (!password.equals(confirmPassword)) {
	            return "Password and Confirm Password do not match. Please try again.";
	        }

	        // Check if user already exists
//	        if (userRepo.isUserAvailableByEmail(email)) {
//	            return "User already exists. Please sign in to continue.";
//	        }
	        Optional<User> checkUserExist = userRepo.findByEmail(email);
	        if(checkUserExist.isPresent())
	        {
	        	return "User already exists. Please sign in to continue."; 
	        }
	        String generatedOtp = generateOTP();
	        otprepository.save(new OTP(email, generatedOtp,mailsender));
	        OTP latestOtp = otprepository.findTopByEmailOrderByCreatedAtDesc(email);
	        if (latestOtp == null || !otp.equals(latestOtp.getOtp())) {
	            return "The OTP is not valid";
	        }
	        
	        
	        
//	        String hashedPassword = new BCryptPasswordEncoder().encode(password);
	        
	        Profile profile = new Profile();
	        
            boolean approved = accountType.equals("Instructor");
	        
//            const user = await User.create({
//                firstName,
//                lastName,
//                email,
//                contactNumber,
//                password: hashedPassword,
//                accountType: accountType,
//                approved: approved,
//                additionalDetails: profileDetails._id,
//                image: "",
//              })
            User createUser = new User(firstName,lastName,email,password,approved,accountType,profile,"");
            userRepo.save(createUser);
            return "User created successfully";
	}

}
