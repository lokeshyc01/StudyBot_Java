package com.app.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sections")
public class Section {

    public Section(String sectionName2) {
		this.sectionName = sectionName2;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sectionName;

    @OneToMany(mappedBy = "section",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<SubSection> subSections = new ArrayList<SubSection>();
    
    public void addToSection(SubSection section)
    {
    	subSections.add(section);
    }
}

