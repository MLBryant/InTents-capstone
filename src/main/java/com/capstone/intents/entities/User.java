package com.capstone.intents.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String userName;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UserCampgroundComment> userCampgroundComments = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_campground",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "campground_id")}
    )
    private Set<Campground> campgrounds = new HashSet<>();
}
