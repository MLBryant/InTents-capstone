package com.capstone.intents.services;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.entities.User;
import com.capstone.intents.repositories.CampgroundRepository;
import com.capstone.intents.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserCampgroundServiceImpl implements UserCampgroundService{

    @Autowired
    private final CampgroundRepository campgroundRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    public UserCampgroundServiceImpl(CampgroundRepository campgroundRepository, UserRepository userRepository) {
        this.campgroundRepository = campgroundRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public String addUserToCampground(Long userId, Long facilityId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Campground> campgroundOptional = campgroundRepository.findByFacilityId(facilityId);
        if (userOptional.isPresent() && campgroundOptional.isPresent()) {
            userOptional.get().addCampgroundToSet(campgroundOptional.get());
            entityManager.persist(userOptional.get());
            entityManager.flush();
            return "Success! " + userOptional.get().getUserName() + " was added to " + campgroundOptional.get().getFacilityName();
        }
        return "Unable to process";
    }

    @Override
    public List<Campground> getUserCampgrounds(Long userId) {
        return entityManager.createNativeQuery(
                "SELECT c.id, c.facility_id, c.name, c.state, c.pets_allowed, c.amp_sites, c.water_sites, c.sewer_sites, c.photo_url FROM users u " +
                        "INNER JOIN user_campground uc ON u.id = uc.user_id " +
                        "INNER JOIN campground c ON uc.campground_id = c.id " +
                        "WHERE u.id = ?1")
                .setParameter(1, userId).getResultList();
    }
}
