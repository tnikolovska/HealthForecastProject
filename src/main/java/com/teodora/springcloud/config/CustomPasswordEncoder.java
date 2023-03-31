package com.teodora.springcloud.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(8));
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
	}

}

