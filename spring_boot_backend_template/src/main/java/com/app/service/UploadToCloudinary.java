package com.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;

@Service
public class UploadToCloudinary implements UploadService
{
	@Value("${cloudinary.api.key}")
	private String cloudinaryApiKey;
	@Value("${cloudinary.api.secret}")
	private String cloudinaryApiSecret;
	@Value("${cloudinary.cloud.name}")
	private String clodinaryCloudName;
	@Value("${cloudinary.folder.name}")
	private String folderName;
	@Autowired
	private Cloudinary cloudinary;
	
	public List<String> uploadVideo(MultipartFile file) throws Exception
	{
		System.out.println("Upload video");
		List<String> list = new ArrayList<String>();
		Map uploadResult = this.cloudinary.uploader().upload(file.getBytes(), Map.of(
				"folder","LokeshProject",
				"resource_type","auto"
				));
		
		uploadResult.forEach((k,v) -> System.out.println(k+"   "+v));
		
		String url = uploadResult.get("url").toString();
		
		String timeDuration = uploadResult.get("duration").toString();
		System.out.println(timeDuration);
		
		list.add(url);
		list.add(timeDuration);
		return list;
	}
}
