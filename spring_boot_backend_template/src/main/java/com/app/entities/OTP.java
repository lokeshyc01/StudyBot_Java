package com.app.entities;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.service.EmailSenderService;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "otp")
public class OTP {
	
	@Autowired
	private EmailSenderService emailsender;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String otp;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    // Constructors, getters, and setters...

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        emailsender.sendEmail(email,"OTP",otp); 
    }

    
}
