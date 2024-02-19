package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subsection")
@Getter
@Setter
@AllArgsConstructor

public class SubSection {

	public String title;
	public String timeDuration;
	public String description;
	public String videoUrl;
	public String additionUrl;

	public SubSection(String title, String timeDuration, String description, String videoUrl) {
		this.title = title;
		this.timeDuration = timeDuration;
		this.description = description;
		this.videoUrl = videoUrl;
	}

}
