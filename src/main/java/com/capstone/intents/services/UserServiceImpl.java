package com.capstone.intents.services;
import com.capstone.intents.entities.User;
import com.capstone.intents.model.UserDto;
import com.capstone.intents.repositories.UserCampgroundCommentRepository;
import com.capstone.intents.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserCampgroundCommentRepository userCampgroundCommentRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public Optional<User> findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public UserDto logUserIn(UserDto userDto) {
        Optional<User> userOptional = findUserByUserName(userDto.getUserName());
        if (userOptional.isPresent()) {
            if (bCryptPasswordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
                return new UserDto(userOptional.get());
            }
            else {
                return userDto;
            }
        } else {
            return userDto;
        }
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        Optional<User> userOptional = findUserByUserName(userDto.getUserName());
        if (userOptional.isPresent()) {
            return userDto;
        } else {
            String password = bCryptPasswordEncoder.encode(userDto.getPassword());
            User user = new User();
            user.setUserName(userDto.getUserName());
            user.setPassword(password);
            return new UserDto(userRepository.save(user));
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> updateUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getId());
        User user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setUserName(userDto.getUserName());
            user.setPassword(userDto.getPassword());
        return Optional.of(new UserDto(userRepository.save(user)));
        }
        return Optional.empty();
    }
}
