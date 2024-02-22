package com.app.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Course> courses = new ArrayList<Course>();

    // getters and setters
    public void addCourse(Course course)
    {
    	this.courses.add(course);
    	course.addCategory(this);
    }

}
