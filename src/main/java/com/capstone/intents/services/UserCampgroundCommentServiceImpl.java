package com.capstone.intents.services;

import com.capstone.intents.entities.UserCampgroundComment;
import com.capstone.intents.model.UserCampgroundCommentDto;
import com.capstone.intents.repositories.UserCampgroundCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserCampgroundCommentServiceImpl implements UserCampgroundCommentService{

    private final UserCampgroundCommentRepository userCampgroundCommentRepository;

    @Override
    public List<UserCampgroundCommentDto> findAllComments() {
        return userCampgroundCommentRepository.findAll().stream().map(UserCampgroundCommentDto::new).collect(Collectors.toList());
    }

    @Override
    public UserCampgroundCommentDto createComment(UserCampgroundCommentDto userCampgroundCommentDto) {
        UserCampgroundComment userCampgroundComment = new UserCampgroundComment();
        userCampgroundComment.setUser(userCampgroundCommentDto.getUser());
        userCampgroundComment.setCampground(userCampgroundCommentDto.getCampground());
        userCampgroundComment.setComment(userCampgroundCommentDto.getComment());
        return new UserCampgroundCommentDto(userCampgroundCommentRepository.save(userCampgroundComment));
    }

    @Override
    public void deleteCommentById(Long id) {
        userCampgroundCommentRepository.deleteById(id);
    }

    @Override
    public Optional<UserCampgroundCommentDto> updateComment(UserCampgroundCommentDto userCampgroundCommentDto) {
        Optional<UserCampgroundComment> userCampgroundCommentOptional = userCampgroundCommentRepository.findById(userCampgroundCommentDto.getId());
        UserCampgroundComment userCampgroundComment = null;
        if(userCampgroundCommentOptional.isPresent()) {
            userCampgroundComment = userCampgroundCommentOptional.get();
            userCampgroundComment.setUser(userCampgroundCommentDto.getUser());
            userCampgroundComment.setCampground(userCampgroundCommentDto.getCampground());
            userCampgroundComment.setComment(userCampgroundComment.getComment());
            return Optional.of(new UserCampgroundCommentDto(userCampgroundCommentRepository.save(userCampgroundComment)));
        }
        return Optional.empty();
    }
}
