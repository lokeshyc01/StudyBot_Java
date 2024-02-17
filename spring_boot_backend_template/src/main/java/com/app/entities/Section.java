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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String sectionName;

    @OneToMany(mappedBy = "section")
    private List<SubSection> subSections = new ArrayList<SubSection>();

    // getters and setters

}

