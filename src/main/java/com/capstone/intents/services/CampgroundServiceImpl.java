package com.capstone.intents.services;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.model.CampgroundDto;
import com.capstone.intents.repositories.CampgroundRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class CampgroundServiceImpl {

    private final CampgroundRepository campgroundRepository;

    @Override
    public List<CampgroundDto> findAllCampgrounds() {
        return campgroundRepository.findAll().stream().map(CampgroundDto::new).collect(Collectors.toList());
    }

    @Override
    public CampgroundDto createCampground(CampgroundDto campgroundDto) {
        Campground campground = new Campground();
        campground.setCampgroundName(CampgroundDto.getCampgroundName());
        campground.setPassword(CampgroundDto.getPassword());
        return new CampgroundDto(CampgroundRepository.save(campground));
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
            campground.setCampgroundName(campgroundDto.getCampgroundName());
            campground.setPassword(campgroundDto.getPassword());
            return Optional.of(new CampgroundDto(campgroundRepository.save(campground)));
        }
        return Optional.empty();
    }
}
