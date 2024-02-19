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

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Section> courseContent = new ArrayList<Section>();

    @OneToMany(mappedBy = "course")
    private List<RatingAndReview> ratingAndReviews = new ArrayList<RatingAndReview>();

    @Column
    private Double price;

    @Column
    private String thumbnail;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "coursesEnrolled")
    private List<User> studentsEnrolled = new ArrayList<User>();

   
    @Column
    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // getters and setters
    
    public void addSectionToCourse(Section section) {
        if (section != null) {
            this.courseContent.add(section);
        }
    }
    
    public void removeSectionfromCourse(Section section)
    {
    	this.courseContent.remove(section);
    }

}
