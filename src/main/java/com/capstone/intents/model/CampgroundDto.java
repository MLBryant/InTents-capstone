package com.capstone.intents.model;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.entities.User;
import com.capstone.intents.entities.UserCampgroundComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CampgroundDto {

    private Long id;
    private Long facilityId;
    private String name;
    private String state;
    private Boolean petsAllowed;
    private Boolean ampSites;
    private Boolean waterSites;
    private Boolean sewerSites;
    private String photoUrl;
    private Set<User> users;
    private Set<UserCampgroundComment> userCampgroundComments;

    public CampgroundDto(Campground campground) {
        this.id = campground.getId();
        this.facilityId = campground.getFacilityId();
        this.name = campground.getName();
        this.state = campground.getState();
        this.petsAllowed = campground.getPetsAllowed();
        this.ampSites = campground.getAmpSites();
        this.waterSites = campground.getWaterSites();
        this.sewerSites = campground.getSewerSites();
        this.photoUrl = campground.getPhotoUrl();
        this.users = campground.getUsers();
        this.userCampgroundComments = campground.getUserCampgroundComments();
    }
}
