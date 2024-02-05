package com.app.entities;

import javax.persistence.*;

@Entity
@Table(name="section")
public class Section {
	
	public String sectionName;
	
	@OneToMany
	@JoinColumn(name="subsection")
	public SubSection subsection;
}
