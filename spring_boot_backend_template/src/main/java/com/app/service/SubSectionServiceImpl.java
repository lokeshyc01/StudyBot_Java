package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.customeexception.ResourceNotFoundException;
import com.app.dao.SectionRepository;
import com.app.dao.SubSectionRepository;
import com.app.entities.Section;
import com.app.entities.SubSection;

@Service
@Transactional
public class SubSectionServiceImpl implements SubSectionService {
	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private SectionRepository sectionrepo;
	

	public String createSubSection(Long sectionId, String title, String timeDuration, String description,
			MultipartFile file) {
		try {
			if (sectionId == null || title == null || description == null || file == null) {
				return "All the fields are required";
			}
			List<String> list = uploadService.uploadVideo(file);

			SubSection subsection = new SubSection(title, list.get(1), description, list.get(0));

			Section section = sectionrepo.findById(sectionId)
					.orElseThrow(() -> new ResourceNotFoundException("section not exist with give id"));

			section.addToSection(subsection);
			sectionrepo.save(section);
			return "section created successfully";

		} catch (Exception e) {
			return "Could not create subsection";
		}
	}
}
