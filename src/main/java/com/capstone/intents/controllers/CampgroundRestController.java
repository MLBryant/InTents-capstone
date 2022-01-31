package com.capstone.intents.controllers;

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
    public List<CampgroundDto> findAllCampgrounds() {
        return campgroundService.findAllCampgrounds();
    }

    @PostMapping("/campgrounds")
    public CampgroundDto createCampground(@RequestBody UserCampground userCampground) {
        return campgroundService.createCampground(userCampground);
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
