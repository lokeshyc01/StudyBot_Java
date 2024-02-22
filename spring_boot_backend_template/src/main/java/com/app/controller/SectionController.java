package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.service.SectionService;

@RestController
@RequestMapping("/sections")
public class SectionController {

	@Autowired
	private SectionService secService;
	
	@PostMapping
	public ResponseEntity<?> createSection(@RequestBody String sectionName,@RequestBody Long courseId)
	{
		try
		{
			return ResponseEntity.ok(new ApiResponse(secService.createSection(sectionName, courseId)));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(new ApiResponse("Not able to crate section"));
		}
	}
	
	public ResponseEntity<?> updateSection(@RequestBody String sectionName , @RequestBody Long sectionId)
	{
		try
		{
			return ResponseEntity.ok(new ApiResponse(secService.updateSection(sectionName, sectionId)));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(new ApiResponse("Could not update section"));
		}
	}
	
	public ResponseEntity<?> deleteSection(@RequestBody Long sectionId,@RequestBody Long courseId)
	{
		try
		{
			return ResponseEntity.ok(new ApiResponse(secService.deleteSection(sectionId, courseId))); 
			
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body(new ApiResponse("Could not delete section"));
		}
	}
}
