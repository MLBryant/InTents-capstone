package com.capstone.intents.model;

import com.capstone.intents.entities.User;
import lombok.Data;

@Data
public class UserCampground {
    private Long userId;
    private CampgroundDto campgroundDto;
}
