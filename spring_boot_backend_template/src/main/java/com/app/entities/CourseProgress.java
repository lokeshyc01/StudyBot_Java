package com.app.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course_progress")
public class CourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    intentionally left blank find out how to cofigure
//    private List<SubSection> completedVideos;

    // getters and setters

}
