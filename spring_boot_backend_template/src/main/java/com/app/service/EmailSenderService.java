package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender mailsender;
	
	public void sendEmail(String toEmail,String subject,String body)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shreelokesh8901@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailsender.send(message);
	}
}
