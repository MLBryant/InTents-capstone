package com.capstone.intents.model;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.entities.User;
import com.capstone.intents.entities.UserCampgroundComment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCampgroundCommentDto {
    private Long id;
    private User user;
    private Campground campground;
    private String comment;

    public UserCampgroundCommentDto(UserCampgroundComment userCampgroundComment) {
        this.id = userCampgroundComment.getId();
        this.user = userCampgroundComment.getUser();
        this.campground = userCampgroundComment.getCampground();
        this.comment = userCampgroundComment.getComment();
    }
}
