package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class Users 
{
	public String Name;
	public String Email;
	public String ContactNumber;
	public String Password;
	public String AccountType;
	public boolean Active;
	public boolean Approve;
	
	@OneToMany
	@JoinColumn(name="courses")
	public Course Courses;
	
	@OneToOne
	public Profile profile;
	
	@OneToOne
	@JoinColumn(name="courseProgress")
	public CourseProgresss courseProgress;
	
}
