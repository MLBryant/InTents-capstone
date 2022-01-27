package com.capstone.intents.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Campground {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String state;
    private Boolean petsAllowed;
    private Boolean ampSites;
    private Boolean waterSites;
    private Boolean sewerSites;
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
