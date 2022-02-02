package com.capstone.intents.services;
import com.capstone.intents.entities.User;
import com.capstone.intents.model.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findUserById(Long id);

    Optional<User> findUserByUserName(String userName);

    UserDto logUserIn(UserDto userDto);

    List<UserDto> findAllUsers();

    UserDto createUser(UserDto userDto);

    void deleteUserById(Long id);

    Optional<UserDto> updateUser(UserDto userDto);
}
