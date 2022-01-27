package com.capstone.intents.repositories;

import com.capstone.intents.entities.Campground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampgroundRepository extends JpaRepository<Campground, Long> {
}
