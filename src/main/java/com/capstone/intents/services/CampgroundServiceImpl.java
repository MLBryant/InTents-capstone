package com.capstone.intents.services;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.entities.User;
import com.capstone.intents.model.CampgroundDto;
import com.capstone.intents.model.UserCampground;
import com.capstone.intents.repositories.CampgroundRepository;
import com.capstone.intents.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class CampgroundServiceImpl implements CampgroundService{

    private final CampgroundRepository campgroundRepository;
    private final UserRepository userRepository;

    @Override
    public List<CampgroundDto> findAllCampgrounds() {
        return campgroundRepository.findAll().stream().map(CampgroundDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<Campground> findByFacilityId(Long facilityId) {
        return campgroundRepository.findByFacilityId(facilityId);
    }

    @Override
    public CampgroundDto createCampground(UserCampground userCampground) {
        User user = userRepository.findUserById(userCampground.getUserId());
        Optional<Campground> campgroundOptional = campgroundRepository.findByFacilityId(userCampground.getCampgroundDto().getFacilityId());
        Campground campground = new Campground();
        if (campgroundOptional.isPresent()) {
            campground = campgroundOptional.get();
            campground.getUsers().add(user);
            user.getCampgrounds().add(campground);
            return new CampgroundDto(campground);
        } else {
            setCampgroundAttributes(userCampground.getCampgroundDto(), campground);
            user.getCampgrounds().add(campground);
            campground.getUsers().add(user);
            return new CampgroundDto(campgroundRepository.save(campground));
        }
    }

    @Override
    public void deleteCampgroundById(Long id) {
        campgroundRepository.deleteById(id);
    }

    @Override
    public Optional<CampgroundDto> updateCampground(CampgroundDto campgroundDto) {
        Optional<Campground> campgroundOptional = campgroundRepository.findById(campgroundDto.getId());
        Campground campground = null;
        if (campgroundOptional.isPresent()) {
            campground = campgroundOptional.get();
            setCampgroundAttributes(campgroundDto, campground);
            return Optional.of(new CampgroundDto(campgroundRepository.save(campground)));
        }
        return Optional.empty();
    }

    private void setCampgroundAttributes(CampgroundDto campgroundDto, Campground campground) {
        campground.setFacilityId(campgroundDto.getFacilityId());
        campground.setName(campgroundDto.getName());
        campground.setState(campgroundDto.getState());
        campground.setPetsAllowed(campgroundDto.getPetsAllowed());
        campground.setAmpSites(campgroundDto.getAmpSites());
        campground.setWaterSites(campgroundDto.getWaterSites());
        campground.setSewerSites(campgroundDto.getSewerSites());
        campground.setPhotoUrl(campgroundDto.getPhotoUrl());
    }

    private void setCampgroundDtoAttributes(UserCampground userCampground, CampgroundDto campgroundDto) {
        campgroundDto.setFacilityId(userCampground.getCampgroundDto().getFacilityId());
        campgroundDto.setName(userCampground.getCampgroundDto().getName());
        campgroundDto.setState(userCampground.getCampgroundDto().getState());
        campgroundDto.setPetsAllowed(userCampground.getCampgroundDto().getPetsAllowed());
        campgroundDto.setAmpSites(userCampground.getCampgroundDto().getAmpSites());
        campgroundDto.setWaterSites(userCampground.getCampgroundDto().getWaterSites());
        campgroundDto.setSewerSites(userCampground.getCampgroundDto().getSewerSites());
        campgroundDto.setPhotoUrl(userCampground.getCampgroundDto().getPhotoUrl());
    }
}
