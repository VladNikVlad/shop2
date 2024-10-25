package com.vladyslavnicko.gmail.api.model;

import com.vladyslavnicko.gmail.model.User;

import lombok.Data;

@Data
public class UserRegistration {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	
	
	public static User toUser(UserRegistration u) {
		User user = new User();
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setPassword(u.getPassword());
		user.setEmail(u.getEmail());
		
		return user;
		
	}
}
