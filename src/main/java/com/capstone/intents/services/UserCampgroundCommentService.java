package com.capstone.intents.services;

import com.capstone.intents.entities.User;
import com.capstone.intents.entities.UserCampgroundComment;
import com.capstone.intents.model.UserCampgroundCommentDto;

import java.util.List;
import java.util.Optional;

public interface UserCampgroundCommentService {

    List<UserCampgroundComment> findCommentsByCampgroundId(Long campgroundId);

    List<UserCampgroundCommentDto> findAllComments();

    String createComment(UserCampgroundCommentDto userCampgroundCommentDto);

    void deleteCommentById(Long id);

    Optional<UserCampgroundCommentDto> updateComment(UserCampgroundCommentDto userCampgroundCommentDto);
}
