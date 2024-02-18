package com.app.service;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.TagRepsitory;
import com.app.entities.*;

public interface TagService {
	public String addTag(Tag tag);
	public List<Tag> getAllTags();
	
}
