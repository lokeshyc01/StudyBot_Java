package com.app.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
public class Profile 
{
	
	public String gener;
	public Date dob;
	public String about;
	public String contactNumber;
}
