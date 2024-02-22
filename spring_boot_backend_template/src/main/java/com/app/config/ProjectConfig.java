package com.app.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ProjectConfig {

	
	@Bean
	public Cloudinary getCloudinary()
	{
		Map map = new HashMap();
		
		map.put("cloud_name", "dfptr5vsy");
		map.put("api_key", "172663363169883");
		map.put("api_secret", "eq0xS3MNSsW_W4dCAP-x7KRMyio");
		map.put("secure", true);
		
		return new Cloudinary(map);
	}
	
}
