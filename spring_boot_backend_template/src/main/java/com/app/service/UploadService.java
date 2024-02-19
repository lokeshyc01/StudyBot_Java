package com.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	public List<String> uploadVideo(MultipartFile file) throws IOException;
}
