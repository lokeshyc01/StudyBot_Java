package com.app.entities;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rating_and_reviews")
public class RatingAndReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(nullable = false)
    private int rating;

    @Column(length = 1000)
    private String review;

    @ManyToOne
    @JoinColumn(name = "course", nullable = false)
    private Course course;

    // getters and setters

}
