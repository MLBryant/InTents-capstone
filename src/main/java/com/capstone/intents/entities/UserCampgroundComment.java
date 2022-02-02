package com.capstone.intents.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserCampgroundComment {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "campground_id")
    private Campground campground;
    private String comment;
}
