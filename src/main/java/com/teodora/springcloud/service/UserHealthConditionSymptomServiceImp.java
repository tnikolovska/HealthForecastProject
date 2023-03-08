package com.teodora.springcloud.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teodora.springcloud.config.CustomPasswordEncoder;
import com.teodora.springcloud.dao.UserDao;
import com.teodora.springcloud.exception.UserAlreadyExistsException;
import com.teodora.springcloud.model.User;
import com.teodora.springcloud.repos.UserRepo;
import org.springframework.context.annotation.Lazy;

@Service
public class UserHealthConditionSymptomServiceImp implements UserHealthConditionSymptomService {
	
	
	
	

}
