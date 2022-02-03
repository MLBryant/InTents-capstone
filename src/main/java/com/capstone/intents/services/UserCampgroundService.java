package com.capstone.intents.services;

import com.capstone.intents.entities.Campground;

import java.util.List;

public interface UserCampgroundService {

    String addUserToCampground (Long userId, Long facilityId);

    List<Campground> getUserCampgrounds(Long userId);

    String removeUserCampground(Long userId, Long facilityId);
}
