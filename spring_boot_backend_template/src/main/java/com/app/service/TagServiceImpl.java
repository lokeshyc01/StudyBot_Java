package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TagRepsitory;
import com.app.entities.Tag;
@Service
@Transactional
public class TagServiceImpl implements TagService {
	@Autowired
	private TagRepsitory tagRepo;
	
	public String addTag(Tag tag) {
		tagRepo.save(tag);
		return null;
	}

	
	public List<Tag> getAllTags() {
		List<Tag> tagList = tagRepo.findAll();
		return tagList;
	}
}
