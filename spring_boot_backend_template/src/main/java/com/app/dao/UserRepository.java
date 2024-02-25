package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.entities.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByEmail(String email);
	
	public User findByFirstName(String username);
	public Optional<User> findByToken(String token);
//	public  boolean isUserAvailableByEmail(String email);
}
