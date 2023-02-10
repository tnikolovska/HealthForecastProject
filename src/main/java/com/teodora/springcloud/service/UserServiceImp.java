package com.teodora.springcloud.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.teodora.springcloud.dao.UserDao;
import com.teodora.springcloud.model.User;

public class UserServiceImp implements UserService {
	
	@Autowired
	UserDao userDao;
	
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
