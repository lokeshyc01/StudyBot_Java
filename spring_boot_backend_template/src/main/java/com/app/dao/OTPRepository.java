package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.OTP;

public interface OTPRepository extends JpaRepository<OTP, Long>{
    OTP findTopByEmailOrderByCreatedAtDesc(String email);

}
