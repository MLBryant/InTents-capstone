package com.capstone.intents.controllers;

import com.capstone.intents.model.UserCampgroundCommentDto;
import com.capstone.intents.services.UserCampgroundCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserCampgroundCommentRestController {

    private final UserCampgroundCommentService userCampgroundCommentService;

    @Autowired
    public UserCampgroundCommentRestController(UserCampgroundCommentService userCampgroundCommentService) {
        this.userCampgroundCommentService = userCampgroundCommentService;
    }

    @GetMapping("/comments")
    public List<UserCampgroundCommentDto> findAllComments() {
        return userCampgroundCommentService.findAllComments();
    }

    @PostMapping("/comments")
    public UserCampgroundCommentDto createComment(@RequestBody UserCampgroundCommentDto userCampgroundCommentDto) {
        return userCampgroundCommentService.createComment(userCampgroundCommentDto);
    }

    @PutMapping("/comments")
    public Optional<UserCampgroundCommentDto> updateComment(@RequestBody UserCampgroundCommentDto userCampgroundCommentDto) {
        return userCampgroundCommentService.updateComment(userCampgroundCommentDto);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id) {
        userCampgroundCommentService.deleteCommentById(id);
    }
}
