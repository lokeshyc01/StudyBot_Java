package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean approved;

    @OneToOne
    @JoinColumn(name = "additional_details_id")
    private Profile additionalDetails;

    @ManyToMany
    @JoinTable(name = "courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses = new ArrayList<Course>();

    @Column
    private String token;

    @Column
    private Date resetPasswordExpires;

    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "user")
    private List<CourseProgress> courseProgress = new ArrayList<CourseProgress>();

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // getters and setters

}
