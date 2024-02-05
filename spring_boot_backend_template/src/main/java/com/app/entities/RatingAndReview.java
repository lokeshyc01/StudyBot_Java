package com.app.entities;
import javax.persistence.*;
@Entity
@Table(name="ratingandreview")
public class RatingAndReview 
{
	public String user;
	public float rating;
	public String review;
}
