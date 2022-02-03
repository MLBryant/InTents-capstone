package com.capstone.intents.repositories;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.model.CampgroundDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampgroundRepository extends JpaRepository<Campground, Long> {

    Optional<Campground> findById(Long campgroundId);

    Optional<CampgroundDto> findByFacilityId(Long facilityId);
}
