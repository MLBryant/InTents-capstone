package com.capstone.intents.repositories;

import com.capstone.intents.entities.User;
import com.capstone.intents.entities.UserCampgroundComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCampgroundCommentRepository extends JpaRepository<UserCampgroundComment, Long> {

    List<UserCampgroundComment> findCommentsByCampgroundId(Long campgroundId);
}
