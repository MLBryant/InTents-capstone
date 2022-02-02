package com.capstone.intents.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String userName;
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    private Set<UserCampgroundComment> userCampgroundCommentSet = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "campground_id"})},
            name = "user_campground",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "campground_id")}
    )
    @EqualsAndHashCode.Exclude
    private Set<Campground> campgroundSet = new HashSet<>();

    public void addCampgroundToSet(Campground campground) {
        this.campgroundSet.add(campground);
        campground.getUserSet().add(this);
    }

    public void addCommentToSet(UserCampgroundComment userCampgroundComment) {
        this.userCampgroundCommentSet.add(userCampgroundComment);
        userCampgroundComment.setUser(this);
    }
}
