package com.app.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="profile")
public class Profile 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	@Column
	public String gender;
	@Column
	public Date dob;
	@Column
	public String about;
	@Column
	public String contactNumber;
	
	public Profile()
	{
		this.gender = null;
		this.dob = null;
		this.about = null;
		this.contactNumber = null;
	}
}
