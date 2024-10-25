package com.vladyslavnicko.gmail.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.vladyslavnicko.gmail.DTO.MyUserDetails;
import com.vladyslavnicko.gmail.model.Role;
import com.vladyslavnicko.gmail.model.User;
import com.vladyslavnicko.gmail.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        if (user.getRole() == null) {
			user.setRole(Role.VISITAN);
		}
        
        return new MyUserDetails(user);
	}

}
