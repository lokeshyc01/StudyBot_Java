package com.app.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="profile")
public class Profile 
{
	
	public String gender;
	public Date dob;
	public String about;
	public String contactNumber;
	
	public Profile()
	{
		this.gender = null;
		this.dob = null;
		this.about = null;
		this.contactNumber = null;
	}
}
