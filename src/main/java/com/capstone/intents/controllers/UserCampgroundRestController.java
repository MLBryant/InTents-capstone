package com.capstone.intents.controllers;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.services.UserCampgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usercampground")
public class UserCampgroundRestController {

    @Autowired
    private final UserCampgroundService userCampgroundService;

    public UserCampgroundRestController(UserCampgroundService userCampgroundService) {
        this.userCampgroundService = userCampgroundService;
    }

    @PostMapping("/{userId}/{facilityId}")
    public String addCampgroundToUser(@PathVariable ("userId") Long userId, @PathVariable ("facilityId") Long facilityId) {
        return userCampgroundService.addUserToCampground(userId, facilityId);
    }

    @GetMapping("/{userId}")
    public List<Campground> getUserCampgrounds(@PathVariable Long userId) {
        return userCampgroundService.getUserCampgrounds(userId);
    }

}
