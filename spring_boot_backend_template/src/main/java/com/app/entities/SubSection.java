package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="subsection")
public class SubSection 
{
	
	public String title;
	public String timeDuration;
	public String description;
	public String videoUrl;
	public String additionUrl;
	
}
