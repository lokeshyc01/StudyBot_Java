package com.app.entities;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails
{
	
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean approved;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "additional_details")
    private Profile additionalDetails;

    @ManyToMany
    @JoinTable(name = "courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses = new ArrayList<Course>();

    @Column
    private String token;

    @Column
    private LocalDateTime resetPasswordExpires;

    @Column
    private String image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CourseProgress> courseProgress = new ArrayList<CourseProgress>();


    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;
    
 
    public User(String firstName,String lastName,String email,String password,boolean approved,Role role,Profile profile,String imageUrl)
    {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.email = email;
    	this.password = password;
    	this.role = role;
    	this.approved = approved;
    	this.additionalDetails = profile;
    	this.image = imageUrl;
    	this.createdAt = LocalDateTime.now();
    }
    public void addCourse(Course course)
    {
    	this.courses.add(course);
    	course.addUser(this);
    }
    
    // getters and setters
    public void addProfile(Profile profile)
    {
    	this.additionalDetails = profile;
    }
    
    public void addCourseProgress(CourseProgress courseProgress)
    {
    	this.courseProgress.add(courseProgress);
    	courseProgress.addUser(this);
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return  List.of(new SimpleGrantedAuthority(role.name()));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getFirstName();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
