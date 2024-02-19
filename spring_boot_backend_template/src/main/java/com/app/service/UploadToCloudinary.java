package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;

@Service
public class UploadToCloudinary 
{
	@Value("${cloudinary.api.key}")
	private String cloudinaryApiKey;
	@Value("${cloudinary.api.secret}")
	private String cloudinaryApiSecret;
	@Value("${cloudinary.cloud.name}")
	private String clodinaryCloudName;
	@Value("${cloudinary.folder.name}")
	private String folderName;
	
	public List<String> uploadVideo(MultipartFile file) throws Exception
	{
		List<String> list  = new ArrayList<>();
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name",clodinaryCloudName,
				"api_key", cloudinaryApiKey,
				"api_secret" , cloudinaryApiSecret
				));
		
		
		Map<?,?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
				"folder",folderName,
				"resource_type","auto"
				));
		String url = uploadResult.get("secure_url").toString();
		ApiResponse videoDetails = cloudinary.api().resource(url, ObjectUtils.emptyMap());
		System.out.println(url+" "+videoDetails);
		
		String timeDuration = videoDetails.get("duration").toString();
		System.out.println(timeDuration);
		
		list.add(url);
		list.add(timeDuration);
		return list;
	}
}
