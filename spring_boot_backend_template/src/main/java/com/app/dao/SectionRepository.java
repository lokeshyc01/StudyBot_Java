package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long>
{
	
}
