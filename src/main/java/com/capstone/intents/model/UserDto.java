package com.capstone.intents.model;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.entities.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private List<Campground> campgrounds = new ArrayList<>();

    public UserDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.campgrounds = user.getCampgrounds();
    }

    public UserDto() {

    }
}
