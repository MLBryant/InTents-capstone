package com.capstone.intents.repositories;

import com.capstone.intents.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    public User findUserById(Long id);
}
