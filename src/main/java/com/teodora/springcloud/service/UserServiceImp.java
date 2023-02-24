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
public class UserServiceImp implements UserService,UserDetailsService {
	
	@Autowired
	UserDao userDao;
	
	private UserRepo userRepo;
	private CustomPasswordEncoder customPasswordEncoder;
	
	
	 @Autowired
	    public UserServiceImp(UserRepo userRepository,
	                       @Lazy CustomPasswordEncoder customPasswordEncoder){
	        this.userRepo = userRepository;
	        this.customPasswordEncoder = customPasswordEncoder;
	    }
	 
	 public void registerUser(User user) {
	        //User newUser = new User();
	        //newUser.setId(user.getId());
	        /*newUser.setFirstName(user.getFirstName());
	        newUser.setLastName(user.getLastName());
	        newUser.setEmail(user.getEmail());
	        newUser.setBirthDate(user.getBirthDate());*/
	        user.setPassword(customPasswordEncoder.encode(user.getPassword()));
	        userRepo.save(user);
	    } 
	 
	 public User findUserById(Long id) {
	        return userRepo.getReferenceById(id);
	    }

	 @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userRepo.findByEmail(email);
	    if(user == null) throw new UsernameNotFoundException(email);
	            return new org.springframework.security.core.userdetails.User(email,
	                    null,Collections.emptyList());
	    }
	 
	
	@Override
	public User create(String firstName, String lastName, Date birthDate, String email, String password) {
		// TODO Auto-generated method stub
		User user=new User(firstName,lastName,birthDate,email,password);
		userDao.create(user);
		return user;
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}

	@Override
	public void updateUser(Long id, String firstName, String lastName, Date birthDate, String email, String password) {
		// TODO Auto-generated method stub
		User updateUser = userDao.getUser(id);
		updateUser.setFirstName(firstName);
		updateUser.setLastName(lastName);
		updateUser.setBirthDate(birthDate);
		updateUser.setEmail(email);
		updateUser.setPassword(password);
		userDao.updateUser(updateUser);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userDao.deleteUser(user);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}
	
	
	

}
