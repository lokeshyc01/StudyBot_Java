package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.customeexception.ResourceNotFoundException;
import com.app.dao.CourseRepository;
import com.app.dao.SectionRepository;
import com.app.dao.SubSectionRepository;
import com.app.entities.Course;
import com.app.entities.Section;

@Service
@Transactional
public class SectionServiceImpl implements SectionService {
	@Autowired
	private SectionRepository sectionRepo;
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private SubSectionRepository subsecRepo;
	
	public String createSection(String sectionName, Long courseId) {
		try {
			if (sectionName == null || courseId == null) {
				return "Missing fields";
			}

			Section section = new Section(sectionName);
			Section usection = sectionRepo.save(section);

			Course course = courseRepo.findById(courseId).orElseThrow();
			course.addSectionToCourse(usection);
			return "section created";
		} catch (Exception e) {
			return "could not create section " + e.getMessage();
		}

	}

	public String updateSection(String sectionName, Long sectionId) {
		try {
			if (sectionName == null || sectionId == null) {
				return "Missing fields";
			}

			Section section = sectionRepo.findById(sectionId)
					.orElseThrow(() -> new ResourceNotFoundException("section not found"));

			section.setSectionName(sectionName);

			sectionRepo.save(section);

			return "section created successfully";
		} catch (Exception e) {
			return "Could not create section " + e.getMessage();
		}
	}

	@Override
	public String deleteSection(Long sectionId,Long courseId) {
		
		try
		{
			if(!sectionRepo.existsById(sectionId))
			{
				return "section not found";
			}
			Section section = sectionRepo.findById(sectionId).orElseThrow();
			Course course = courseRepo.findById(courseId).orElseThrow();
			
			course.removeSectionfromCourse(section);
			sectionRepo.delete(section);
			return "section can be deleted";
		}
		catch(Exception e)
		{
			return "section could not be deleted";
		}
	}

}
