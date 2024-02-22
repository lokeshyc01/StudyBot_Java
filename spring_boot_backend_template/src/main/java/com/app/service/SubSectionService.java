package com.app.service;

import org.springframework.web.multipart.MultipartFile;


public interface SubSectionService
{
	public String createSubSection(Long sectionId, String title,  String description,
			MultipartFile file) ;
}
