package com.vladyslavnicko.gmail.security;

public interface PasswordHashing {
    byte[] generateSalt();
    byte[] hashPassword(String password, byte[] salt);
    boolean verifyPassword(String password, byte[] salt, byte[] storedHashedPassword);
}
