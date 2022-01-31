package com.capstone.intents.services;

import com.capstone.intents.model.UserCampgroundCommentDto;

import java.util.List;
import java.util.Optional;

public interface UserCampgroundCommentService {

    List<UserCampgroundCommentDto> findAllComments();

    UserCampgroundCommentDto createComment(UserCampgroundCommentDto userCampgroundCommentDto);

    void deleteCommentById(Long id);

    Optional<UserCampgroundCommentDto> updateComment(UserCampgroundCommentDto userCampgroundCommentDto);
}
