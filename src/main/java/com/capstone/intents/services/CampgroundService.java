package com.capstone.intents.services;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.model.CampgroundDto;
import com.capstone.intents.model.UserCampground;

import java.util.List;
import java.util.Optional;


public interface CampgroundService {
    List<Campground> findAllCampgrounds();

    Optional<Campground> findById(Long campgroundId);

    Optional<CampgroundDto> findByFacilityId(Long facilityId);

    CampgroundDto createCampground(CampgroundDto campgroundDto);

    void deleteCampgroundById(Long id);

    Optional<CampgroundDto> updateCampground(CampgroundDto campgroundDto);
}
