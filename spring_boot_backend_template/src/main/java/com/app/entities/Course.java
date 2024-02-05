package com.app.entities;

import javax.persistence.*;

@Entity
@Table(name="course")
public class Course 
{
	public String courseName;
	public String courseDescription;
	public String whatYouWillLearn;
	public int price;
	public String thumbnail;
	
	@OneToOne
	public Users instructor;
	
	@OneToMany
	public Section courseContent;
	
	@OneToMany
	public RatingAndReview ratingAndReview;
	
	@OneToMany
	public Users studentsEnrolled;
	
}
