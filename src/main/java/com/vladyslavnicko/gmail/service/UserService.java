package com.vladyslavnicko.gmail.service;

import com.vladyslavnicko.gmail.DTO.UserInfo;
import com.vladyslavnicko.gmail.DTO.UserPassword;
import com.vladyslavnicko.gmail.model.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    User saveUser(User user);
    List<User> findAllUsers();
    void deleteUserById(Long id);
    UserInfo updateUser(long id, UserInfo user);
    UserInfo updateUserPassword(long id, UserPassword user);
    boolean chackPasssword(String password, User user);
    User findByLogin(String login);
}
