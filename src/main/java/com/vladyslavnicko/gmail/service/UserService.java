package com.vladyslavnicko.gmail.service;

import com.vladyslavnicko.gmail.DTO.UserDTO;
import com.vladyslavnicko.gmail.DTO.UserPassword;
import com.vladyslavnicko.gmail.model.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    User saveUser(User user);
    List<User> findAllUsers();
    void deleteUserById(Long id);
    UserDTO updateUser(long id, UserDTO user);
    UserDTO updateUserPassword(long id, UserPassword user);
    boolean chackPasssword(String password, User user);
    User findByLogin(String login);
    String getCurrentUsername();
    User getCurrentUser();
}
