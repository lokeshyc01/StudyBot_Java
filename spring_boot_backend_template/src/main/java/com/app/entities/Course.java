package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String courseName;

    @Column
    private String courseDescription;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    @Column
    private String whatYouWillLearn;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Section> courseContent = new ArrayList<Section>();

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<RatingAndReview> ratingAndReviews = new ArrayList<RatingAndReview>();

    @Column
    private Double price;

    @Column
    private String thumbnail;

    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "courses")
    private List<User> users = new ArrayList<User>();

   
    @Column
    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @Column(columnDefinition = "TIMESTAMP",updatable = false)
    private LocalDateTime createdAt;

    // getters and setters
    
    public void addCategory(Category category)
    {
    	this.category = category;
    }
    
    public void addUser(User user)
    {
    	this.users.add(user);
    }
    
    public void addTag(Tag tag)
    {
    	this.tags.add(tag);
    	tag.addCourse(this);
    }
    
    public void addSectionToCourse(Section section) {
        if (section != null) {
            this.courseContent.add(section);
        }
    }
    
    public void removeSectionfromCourse(Section section)
    {
    	this.courseContent.remove(section);
    }
    
    public void addRatingAndReview(RatingAndReview rating)
    {
    	this.ratingAndReviews.add(rating);
    	rating.addCourse(this);
    }
    
    

}
