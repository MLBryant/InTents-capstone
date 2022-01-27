package com.capstone.intents.model;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.entities.User;
import lombok.Data;

@Data
public class CampgroundDto {

    private Long id;
    private String name;
    private String state;
    private Boolean petsAllowed;
    private Boolean ampSites;
    private Boolean waterSites;
    private Boolean sewerSites;
    private String photoUrl;
    private User user;

    public CampgroundDto(Campground campground) {
        this.id = campground.getId();
        this.name = campground.getName();
        this.state = campground.getState();
        this.petsAllowed = campground.getPetsAllowed();
        this.ampSites = campground.getAmpSites();
        this.waterSites = campground.getWaterSites();
        this.sewerSites = campground.getSewerSites();
        this.photoUrl = campground.getPhotoUrl();
        this.user = campground.getUser();
    }

    public CampgroundDto() {

    }
}
