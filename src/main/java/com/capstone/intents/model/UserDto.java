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
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    @JsonIgnore
    private Set<Campground> campgrounds;
    @JsonIgnore
    private Set<UserCampgroundComment> userCampgroundComments;

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.campgrounds = user.getCampgroundSet();
        this.userCampgroundComments = user.getUserCampgroundCommentSet();
    }

}
