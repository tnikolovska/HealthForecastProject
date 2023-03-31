package com.teodora.springcloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.teodora.springcloud.service.UserServiceImp;

@Configuration
@EnableWebSecurity
public class CustomerPasswordEncoderConfig {
	
	
	private UserServiceImp userService;
	
	@Autowired
    public CustomerPasswordEncoderConfig(UserServiceImp userService){
        this.userService = userService;
    }
	
	 //@Bean
	   /* public SecurityFilterChain configure(HttpSecurity http) throws Exception { 
	 
	        http.csrf().disable();
	        http.authorizeHttpRequests()
	                .anyRequest().permitAll();
	                /*.permitAll()
	                .and()
	                .authenticationManager(daoAuthenticationProvider());*/
	         /*return http.build();
	    }
	 
	 @Bean
	    public DaoAuthenticationProvider daoAuthenticationProvider(){
		 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setPasswordEncoder(customPasswordEncoder());
	        daoAuthenticationProvider.setUserDetailsService(userService);
	        return daoAuthenticationProvider;
	    }
	    @Bean
	    public CustomPasswordEncoder customPasswordEncoder(){
	        return new CustomPasswordEncoder();
	    }*/
	

}
