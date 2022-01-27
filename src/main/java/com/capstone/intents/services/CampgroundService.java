package com.capstone.intents.services;

import com.capstone.intents.model.CampgroundDto;

import java.util.List;
import java.util.Optional;


public interface CampgroundService {
    List<CampgroundDto> findAllCampgrounds();

    CampgroundDto createCampground(CampgroundDto campgroundDto);

    void deleteCampgroundById(Long id);

    Optional<CampgroundDto> updateCampground(CampgroundDto campgroundDto);
}
