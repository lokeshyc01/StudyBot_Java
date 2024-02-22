package com.app.entities;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.app.service.EmailSenderService;
import com.app.service.EmailService;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "otp")
public class OTP {
	@Transient
	private JavaMailSender mailsender;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String otp;

	@Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	// Constructors, getters, and setters...\
	
	public OTP(String email,String otp,JavaMailSender mailsender)
	{
		this.mailsender = mailsender;
		this.email = email;
		this.otp = otp;
		this.createdAt = LocalDateTime.now();
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		sendEmail(email, "OTP", otp);
	}

	public void sendEmail(String toEmail, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("shreelokesh8901@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailsender.send(message);
	}

}
