package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.TagDTO;
import com.app.entities.Tag;
import com.app.service.TagService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tags")
public class TagController {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private TagService tagService;
	

	@PostMapping
	public ResponseEntity<?> createTag(@RequestBody @Valid TagDTO tagRequest) {

		try {
			String name = tagRequest.getName();
			String description = tagRequest.getDescription();
			Long courseId = tagRequest.getCourseId();

			if (name == null || description == null || name.isEmpty() || description.isEmpty()) {
				
				return ResponseEntity.badRequest().body("All fields are required");
			}
			Tag tag = mapper.map(TagDTO.class, Tag.class);
			
			return ResponseEntity.ok(tagService.addTag(tag));
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getTags()
	{
		try
		{
			List<Tag> tagList = tagService.getAllTags();
			return ResponseEntity.ok(tagList);
		}
		catch(Exception e)
		{
			return ResponseEntity.status(500).body("Error : "+e.getMessage());
		}
	}
}
