package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
