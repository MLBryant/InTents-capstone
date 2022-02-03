package com.capstone.intents.controllers;

import com.capstone.intents.entities.UserCampgroundComment;
import com.capstone.intents.model.UserCampgroundCommentDto;
import com.capstone.intents.services.UserCampgroundCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class UserCampgroundCommentRestController {

    private final UserCampgroundCommentService userCampgroundCommentService;

    @Autowired
    public UserCampgroundCommentRestController(UserCampgroundCommentService userCampgroundCommentService) {
        this.userCampgroundCommentService = userCampgroundCommentService;
    }

    @GetMapping
    public List<UserCampgroundCommentDto> findAllComments() {
        return userCampgroundCommentService.findAllComments();
    }

    @GetMapping("/{campgroundId}")
    public List<UserCampgroundComment> findCommentsByFacilityId(@PathVariable Long campgroundId) {
        return userCampgroundCommentService.findCommentsByCampgroundId(campgroundId);
    }

    @PostMapping
    public String createComment(@RequestBody UserCampgroundCommentDto userCampgroundCommentDto) {
        return userCampgroundCommentService.createComment(userCampgroundCommentDto);
    }

    @PutMapping
    public String updateComment(@RequestBody UserCampgroundCommentDto userCampgroundCommentDto) {
        return userCampgroundCommentService.updateComment(userCampgroundCommentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        userCampgroundCommentService.deleteCommentById(id);
    }
}
