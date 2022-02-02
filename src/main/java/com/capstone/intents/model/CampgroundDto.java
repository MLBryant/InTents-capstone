package com.capstone.intents.model;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.entities.User;
import com.capstone.intents.entities.UserCampgroundComment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class CampgroundDto {

    private Long id;
    private Long facilityId;
    private String facilityName;
    private String state;
    private String sitesWithPetsAllowed;
    private String sitesWithAmps;
    private String sitesWithWaterHookup;
    private String sitesWithSewerHookup;
    private String faciltyPhoto;
    @JsonIgnore
    private Set<User> users;
    @JsonIgnore
    private Set<UserCampgroundComment> userCampgroundComments;

    public CampgroundDto(Campground campground) {
        this.id = campground.getId();
        this.facilityId = campground.getFacilityId();
        this.facilityName = campground.getFacilityName();
        this.state = campground.getState();
        this.sitesWithPetsAllowed = campground.getSitesWithPetsAllowed();
        this.sitesWithAmps = campground.getSitesWithAmps();
        this.sitesWithWaterHookup = campground.getSitesWithSewerHookup();
        this.sitesWithSewerHookup = campground.getSitesWithSewerHookup();
        this.faciltyPhoto = campground.getFaciltyPhoto();
        this.users = campground.getUserSet();
        this.userCampgroundComments = campground.getUserCampgroundCommentSet();
    }
}
