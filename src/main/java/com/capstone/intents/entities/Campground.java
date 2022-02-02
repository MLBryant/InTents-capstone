package com.capstone.intents.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String facilityName;
    private String state;
    private String sitesWithPetsAllowed;
    private String sitesWithAmps;
    private String sitesWithWaterHookup;
    private String sitesWithSewerHookup;
    private String faciltyPhoto;

    @OneToMany(mappedBy = "campground", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    private Set<UserCampgroundComment> userCampgroundCommentSet = new HashSet<>();

    @ManyToMany(mappedBy = "campgroundSet", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    private Set<User> userSet = new HashSet<>();

//    public void addUserToSet(User user) {
//        users.add(user);
//    }

    public void addCommentToSet(UserCampgroundComment userCampgroundComment) {
        this.userCampgroundCommentSet.add(userCampgroundComment);
        userCampgroundComment.setCampground(this);
    }
}

