package com.teodora.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.teodora.springcloud.utils.NetworkUtils;

@SpringBootApplication
public class HealthforecastprojectApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/healthforecastproject");
		SpringApplication.run(HealthforecastprojectApplication.class, args);
		
		
	}
	/*@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/

}
