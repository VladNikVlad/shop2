package com.vladyslavnicko.gmail.DTO;

import com.vladyslavnicko.gmail.model.Role;
import com.vladyslavnicko.gmail.model.User;

import lombok.Data;

@Data
public class UserInfo {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private Role role;

    private String street;
    private String city;
    private String state;
    private String zip;

    public static UserInfo getUserInfoFromUser(User user) {
        UserInfo info = new UserInfo();
        info.setId(user.getId());
        info.setFirstName(user.getFirstName());
        info.setLastName(user.getLastName());
        info.setEmail(user.getEmail());
        info.setRole(user.getRole());
        info.setDateOfBirth(String.valueOf(user.getDateOfBirth()));
        if (user.getAddress() != null) {
        	info.setCity(user.getAddress().getCity());
        	info.setState(user.getAddress().getState());
        	info.setStreet(user.getAddress().getStreet());
        	info.setZip(user.getAddress().getZip());
        }
        return info;
    }

}
