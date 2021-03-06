package com.capstone.intents.repositories;

import com.capstone.intents.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    User findUserById(Long id);

    Optional<User> findUserByUserName(String userName);
}
