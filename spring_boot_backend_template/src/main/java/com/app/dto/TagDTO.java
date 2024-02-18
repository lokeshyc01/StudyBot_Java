package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.app.entities.Course;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TagDTO 
{
	
	    @NotBlank(message = "Name is required")
	    private String name;
	    @NotBlank(message = "Descrition is required")
	    private String description;
	    
	    @NotNull
	    private Long courseId;

	    public TagDTO(String name, String description, Long courseId) {
	       
	        this.name = name;
	        this.description = description;
	        this.courseId = courseId;
	    }
}
