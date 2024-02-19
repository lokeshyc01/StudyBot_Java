package com.app.service;

import com.app.entities.Section;

public interface SectionService {
	public String createSection(String sectionName,Long courseId);
	public String updateSection(String sectionName, Long sectionId);
	public String deleteSection(Long sectionId,Long courseId);
}
