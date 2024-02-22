package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subsection")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubSection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String title;
	private String timeDuration;
	private String description;
	private String videoUrl;
	private String additionUrl;

	public SubSection(String title, String timeDuration, String description, String videoUrl) {
		this.title = title;
		this.timeDuration = timeDuration;
		this.description = description;
		this.videoUrl = videoUrl;
	}

}
