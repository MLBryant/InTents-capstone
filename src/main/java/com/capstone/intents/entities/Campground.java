package com.capstone.intents.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Campground {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private Long facilityId;
    private String name;
    private String state;
    private Boolean petsAllowed;
    private Boolean ampSites;
    private Boolean waterSites;
    private Boolean sewerSites;
    private String photoUrl;

    @OneToMany(mappedBy = "campground")
    private Set<UserCampgroundComment> userCampgroundComments = new HashSet<>();

    @ManyToMany(mappedBy = "campgrounds")
    private Set<User> users = new HashSet<>();
}
