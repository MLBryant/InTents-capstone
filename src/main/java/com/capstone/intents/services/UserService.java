package com.capstone.intents.services;
import com.capstone.intents.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAllUsers();

    UserDto createUser(UserDto userDto);

    void deleteUserById(Long id);

    Optional<UserDto> updateUser(UserDto userDto);
}
