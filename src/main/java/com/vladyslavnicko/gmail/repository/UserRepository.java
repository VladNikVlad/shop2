package com.vladyslavnicko.gmail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vladyslavnicko.gmail.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFirstName(String username);
    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    User findById(long id);
    User findByLogin(String login);
}
