package com.capstone.intents.services;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.model.CampgroundDto;
import com.capstone.intents.model.UserCampground;

import java.util.List;
import java.util.Optional;


public interface CampgroundService {
    List<Campground> findAllCampgrounds();

    Optional<Campground> findByFacilityId(Long facilityId);

    CampgroundDto createCampground(UserCampground userCampground);

    void deleteCampgroundById(Long id);

    Optional<CampgroundDto> updateCampground(CampgroundDto campgroundDto);
}
