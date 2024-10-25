package com.vladyslavnicko.gmail.DTO;

import com.vladyslavnicko.gmail.model.Language;
import com.vladyslavnicko.gmail.model.Role;
import com.vladyslavnicko.gmail.model.User;

import lombok.Data;

@Data
public class UserDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private Language lang;
  //
    private Role role;
    private AddressDTO address;

    public static UserDTO fromUser(User user) {
        UserDTO info = new UserDTO();
        info.setId(user.getId());
        info.setFirstName(user.getFirstName());
        info.setLastName(user.getLastName());
        info.setEmail(user.getEmail());
        //info.setRole(user.getRole());
        info.setLang(user.getLang());
        info.setDateOfBirth(String.valueOf(user.getDateOfBirth()));
        if (user.getAddress() != null) {
        	info.setAddress(AddressDTO.fromAddress(user.getAddress()));
        }
        
        return info;
    }

    public static User toUser(UserDTO user) {
        User info = new User();
        info.setId(user.getId());
        info.setFirstName(user.getFirstName());
        info.setLastName(user.getLastName());
        info.setEmail(user.getEmail());
      //  info.setRole(user.getRole());
      //  info.setDateOfBirth(String.valueOf(user.getDateOfBirth()));
        if (user.getAddress() != null) {
        	info.setAddress(AddressDTO.toAddress(user.getAddress()));
        }
        
        return info;
    }
}
