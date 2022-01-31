package com.capstone.intents.repositories;

import com.capstone.intents.entities.UserCampgroundComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCampgroundCommentRepository extends JpaRepository<UserCampgroundComment, Long> {
}
