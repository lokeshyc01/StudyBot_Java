package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.SubSectionDTO;
import com.app.service.SubSectionService;

@RestController
@RequestMapping("/subsections")
public class SubSectionController {
	@Autowired
	private SubSectionService subsectionservice;
	
	@PostMapping
	public ResponseEntity<?> createSubSection(@ModelAttribute SubSectionDTO subsection,@RequestParam("file") MultipartFile file)
	{
		try
		{
			System.out.println(subsection.getId()+" "+subsection.getDescription());
			return ResponseEntity.ok(new ApiResponse
					(subsectionservice.createSubSection(subsection.getId(),subsection.getTitle(),subsection.getDescription(),file)));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Could not upload course"));
		}
	}
}
