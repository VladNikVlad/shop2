package com.vladyslavnicko.gmail.api.model;

import java.time.LocalDate;

import com.vladyslavnicko.gmail.model.Address;
import com.vladyslavnicko.gmail.model.Cart;
import com.vladyslavnicko.gmail.model.Language;
import com.vladyslavnicko.gmail.model.Role;
import com.vladyslavnicko.gmail.model.User;

import lombok.Data;


@Data
public class UserAPI {

	private Long id;
	private String firstName;
	private String lastName;
	private String login;
	private String email;
	private String password;
	private LocalDate dateOfBirth;
	private Address address;
	private Cart cart;
	private Language lang;
	private Role role;
	private boolean enabled;
	

	public static UserAPI fromUser(User u) {
		UserAPI userAPI = new UserAPI();
		userAPI.setId(u.getId());
		userAPI.setFirstName(u.getFirstName());
		userAPI.setLastName(u.getLastName());
		userAPI.setLogin(u.getLogin());
		userAPI.setEmail(u.getEmail());
		userAPI.setDateOfBirth(u.getDateOfBirth());
		userAPI.setAddress(u.getAddress());
		userAPI.setCart(u.getCart());
		userAPI.setLang(u.getLang());
		//userAPI.setRole(u.getRole());
		userAPI.setEnabled(u.isEnabled());
		
		return userAPI;
		
	}
	
	public static User toUser(UserAPI u) {
		User user = new User();
		user.setId(u.getId());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setPassword(u.getPassword());
		user.setLogin(u.getLogin());
		user.setEmail(u.getEmail());
		user.setDateOfBirth(u.getDateOfBirth());
		user.setAddress(u.getAddress());
		user.setCart(u.getCart());
		user.setLang(u.getLang());
		//user.setRole(u.getRole());
		user.setEnabled(u.isEnabled());
		
		return user;
		
	}


}
