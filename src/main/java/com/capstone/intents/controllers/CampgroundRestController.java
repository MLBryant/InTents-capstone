package com.capstone.intents.controllers;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.model.CampgroundDto;
import com.capstone.intents.model.UserCampground;
import com.capstone.intents.services.CampgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CampgroundRestController {

    private final CampgroundService campgroundService;

    @Autowired
    public CampgroundRestController(CampgroundService campgroundService) {
        this.campgroundService = campgroundService;
    }

    @GetMapping("/campgrounds")
    public List<Campground> findAllCampgrounds() {
        return campgroundService.findAllCampgrounds();
    }

    @GetMapping("/campgrounds/{facilityId}")
    public Optional<Campground> findByFacilityId(@PathVariable Long facilityId) {
        return campgroundService.findByFacilityId(facilityId);
    }

    @PostMapping("/campgrounds")
    public CampgroundDto createCampground(@RequestBody CampgroundDto campgroundDto) {
        return campgroundService.createCampground(campgroundDto);
    }

    @PutMapping("/campgrounds")
    public Optional<CampgroundDto> updateCampground(@RequestBody CampgroundDto campgroundDto) {
        return campgroundService.updateCampground(campgroundDto);
    }

    @DeleteMapping("/campgrounds/{id}")
    public void deleteCampground(@PathVariable Long id) {
        campgroundService.deleteCampgroundById(id);
    }
}
