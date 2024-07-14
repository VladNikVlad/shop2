package com.vladyslavnicko.gmail.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHashingImpl implements PasswordEncoder {

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


	@Override
	public String encode(CharSequence rawPassword) {
//		System.out.println("Hello password " + rawPassword);
		return passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		System.out.println("Hello password " + rawPassword + "   _ -"  + encodedPassword);
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
