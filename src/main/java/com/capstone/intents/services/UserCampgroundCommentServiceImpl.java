package com.capstone.intents.services;

import com.capstone.intents.entities.Campground;
import com.capstone.intents.entities.User;
import com.capstone.intents.entities.UserCampgroundComment;
import com.capstone.intents.model.CampgroundDto;
import com.capstone.intents.model.UserCampgroundCommentDto;
import com.capstone.intents.repositories.CampgroundRepository;
import com.capstone.intents.repositories.UserCampgroundCommentRepository;
import com.capstone.intents.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserCampgroundCommentServiceImpl implements UserCampgroundCommentService{

    private final UserCampgroundCommentRepository userCampgroundCommentRepository;
    private final UserRepository userRepository;
    private final CampgroundRepository campgroundRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserCampgroundComment> findCommentsByCampgroundId(Long campgroundId) {
        return entityManager.createNativeQuery(
                        "SELECT ucc.id AS ucc_id, u.id AS u_id, u.user_name, ucc.comment FROM users u " +
                                "INNER JOIN user_campground_comment ucc ON u.id = ucc.user_id " +
                                "INNER JOIN campground c ON ucc.campground_id = c.id " +
                                "WHERE c.facility_id = ?1")
                .setParameter(1, campgroundId).getResultList();
    }

    @Override
    public List<UserCampgroundCommentDto> findAllComments() {
        return userCampgroundCommentRepository.findAll().stream().map(UserCampgroundCommentDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String createComment(UserCampgroundCommentDto userCampgroundCommentDto) {
        UserCampgroundComment userCampgroundComment = new UserCampgroundComment();
        User user = userRepository.findUserById(userCampgroundCommentDto.getUser().getId());
        Optional<CampgroundDto> campgroundDtoOptional = campgroundRepository.findByFacilityId(userCampgroundCommentDto.getCampground().getFacilityId());
        Optional<Campground> campgroundOptional = campgroundRepository.findById(campgroundDtoOptional.get().getId());
        userCampgroundComment.setUser(userRepository.findUserById(userCampgroundCommentDto.getUser().getId()));
        userCampgroundComment.setCampground(campgroundOptional.get());
        userCampgroundComment.setComment(userCampgroundCommentDto.getComment());
        user.getUserCampgroundCommentSet().add(userCampgroundComment);
        campgroundOptional.get().getUserCampgroundCommentSet().add(userCampgroundComment);
        userCampgroundCommentRepository.save(userCampgroundComment);
        return "yo";
    }

    @Override
    public void deleteCommentById(Long id) {
        userCampgroundCommentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public String updateComment(UserCampgroundCommentDto userCampgroundCommentDto) {
        Optional<UserCampgroundComment> userCampgroundCommentOptional = userCampgroundCommentRepository.findById(userCampgroundCommentDto.getId());
        if(userCampgroundCommentOptional.isPresent()) {
            UserCampgroundComment userCampgroundComment = userCampgroundCommentOptional.get();
            userCampgroundComment.setComment(userCampgroundCommentDto.getComment());
            entityManager.persist(userCampgroundComment);
            entityManager.flush();
            return "badass";
        }
        return "shit";
    }
}
