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

import javax.transaction.Transactional;
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
    public List<Campground> findAllCampgrounds() {
        return campgroundRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Campground> findByFacilityId(Long facilityId) {
        return campgroundRepository.findByFacilityId(facilityId);
    }

    @Override
    @Transactional
    public CampgroundDto createCampground(UserCampground userCampground) {
//        User user = userRepository.findUserById(userCampground.getUserId());
        Optional<Campground> campgroundOptional = campgroundRepository.findByFacilityId(userCampground.getCampgroundDto().getFacilityId());
        if (campgroundOptional.isPresent()) {
            Campground campground = campgroundOptional.get();
//            user.addCampgroundToSet(campground);
            return new CampgroundDto(campground);
        } else {
            Campground campground = new Campground();
            setCampgroundAttributes(userCampground.getCampgroundDto(), campground);
//            user.addCampgroundToSet(campground);
            return new CampgroundDto(campgroundRepository.saveAndFlush(campground));
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
        campground.setFacilityName(campgroundDto.getFacilityName());
        campground.setState(campgroundDto.getState());
        campground.setSitesWithPetsAllowed(campgroundDto.getSitesWithPetsAllowed());
        campground.setSitesWithAmps(campgroundDto.getSitesWithAmps());
        campground.setSitesWithWaterHookup(campgroundDto.getSitesWithWaterHookup());
        campground.setSitesWithSewerHookup(campgroundDto.getSitesWithSewerHookup());
        campground.setFaciltyPhoto(campgroundDto.getFaciltyPhoto());
    }
}
