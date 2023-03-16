package com.teodora.springcloud.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teodora.springcloud.config.CustomPasswordEncoder;
import com.teodora.springcloud.dao.UserDao;
import com.teodora.springcloud.exception.UserAlreadyExistsException;
import com.teodora.springcloud.model.Privilege;
import com.teodora.springcloud.model.Role;
import com.teodora.springcloud.model.User;
import com.teodora.springcloud.model.VerificationToken;
import com.teodora.springcloud.repos.RoleRepo;
import com.teodora.springcloud.repos.UserRepo;
import com.teodora.springcloud.repos.VerificationTokenRepository;

import org.springframework.context.annotation.Lazy;

@Service
@Transactional
public class RoleServiceImp implements RoleService {
	
	

}
