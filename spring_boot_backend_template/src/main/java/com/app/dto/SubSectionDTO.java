package com.app.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubSectionDTO {
	public Long id;
	public String title;
	public String description;
	public MultipartFile file;
}
