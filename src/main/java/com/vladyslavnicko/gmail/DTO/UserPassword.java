package com.vladyslavnicko.gmail.DTO;

import lombok.Data;

@Data
public class UserPassword {
    private long id;
    private String oldPassword;
    private String newPassword;

}
