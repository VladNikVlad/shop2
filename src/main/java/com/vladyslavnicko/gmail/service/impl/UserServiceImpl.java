package com.vladyslavnicko.gmail.service.impl;

import com.vladyslavnicko.gmail.DTO.AddressDTO;
import com.vladyslavnicko.gmail.DTO.UserDTO;
import com.vladyslavnicko.gmail.DTO.UserPassword;
import com.vladyslavnicko.gmail.config.PasswordHashingImpl;
import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.Address;
import com.vladyslavnicko.gmail.model.User;
import com.vladyslavnicko.gmail.repository.UserRepository;
//import com.vladyslavnicko.gmail.security.PasswordHashing;
import com.vladyslavnicko.gmail.service.UserService;

import io.micrometer.common.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

//    private final PasswordHashing passwordHashing;
    private PasswordHashingImpl passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordHashingImpl passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
    
    public User findByName(String login) {
    	return userRepository.findByLogin(login);	
    }

    @Transactional
    @Override
    public User saveUser(User user) {
    	if (user.getPassword() != null) {
    		user.setPassword(passwordEncoder.encode(user.getPassword()));
    	}
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public UserDTO updateUser(long id, UserDTO user) {
        User findUser = userRepository.findById(id);
        if (findUser == null) {
            throw new ConflictException("Invalid id");
        }
      
        if (user.getAddress() != null){
            findUser.setAddress(AddressDTO.toAddress(user.getAddress()));
        }

        if (user.getFirstName() == null ||  user.getFirstName().isBlank()) {
            throw new ConflictException("The name cannot be empty");
        }
        findUser.setFirstName(user.getFirstName().trim());
        if (user.getLastName() != null && !user.getLastName().isBlank()) {
            findUser.setLastName(user.getLastName());
        }
        if (user.getDateOfBirth() != null && !user.getDateOfBirth().isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate = LocalDate.parse(user.getDateOfBirth(), formatter);
            findUser.setDateOfBirth(localDate);
        }
        
        return UserDTO.fromUser(userRepository.save(findUser));
    }

    @Transactional
    @Override
    public UserDTO updateUserPassword(long id, UserPassword user){
        User findUser = userRepository.findById(id);
        if (findUser == null || !findUser.getPassword().equals(user.getOldPassword())) {
            throw new ConflictException("Invalid email or password");
        }
        if (user.getOldPassword().isBlank() || user.getOldPassword() == null) {
            throw new ConflictException("Old password is empty");
        }
        if (user.getNewPassword().isBlank() || user.getNewPassword() == null) {
            throw new ConflictException("New password is empty");
        }
        if (user.getOldPassword().equals(user.getNewPassword())) {
            throw new ConflictException("The entered password is identical to the old one");
        }
      //  findUser.setPassword(user.getNewPassword());
        return UserDTO.fromUser(userRepository.save(findUser));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public User findByLogin(String login) {
    	return userRepository.findByLogin(login);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean chackPasssword(String password, User user) {
    	if (user != null && StringUtils.isNotBlank(user.getPassword())) {
    	return passwordEncoder.matches(password, user.getPassword());
    	} else {
    		return false;
    	}
    }
}
