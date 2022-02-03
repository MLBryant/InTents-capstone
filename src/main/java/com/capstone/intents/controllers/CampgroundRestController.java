package com.capstone.intents.controllers;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.model.CampgroundDto;
import com.capstone.intents.model.UserCampground;
import com.capstone.intents.services.CampgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "/campgrounds", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Campground> findAllCampgrounds() {
        return campgroundService.findAllCampgrounds();
    }

    @GetMapping(value = "/campgrounds/{facilityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<CampgroundDto> findByFacilityId(@PathVariable Long facilityId) {
        return campgroundService.findByFacilityId(facilityId);
    }

    @PostMapping(value = "/campgrounds", produces = MediaType.APPLICATION_JSON_VALUE)
    public CampgroundDto createCampground(@RequestBody CampgroundDto campgroundDto) {
        return campgroundService.createCampground(campgroundDto);
    }

    @PutMapping(value = "/campgrounds")
    public Optional<CampgroundDto> updateCampground(@RequestBody CampgroundDto campgroundDto) {
        return campgroundService.updateCampground(campgroundDto);
    }

    @DeleteMapping("/campgrounds/{id}")
    public void deleteCampground(@PathVariable Long id) {
        campgroundService.deleteCampgroundById(id);
    }
}
