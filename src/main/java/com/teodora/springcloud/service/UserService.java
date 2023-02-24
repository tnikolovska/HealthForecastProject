package com.teodora.springcloud.service;

import java.util.Date;
import java.util.List;

import com.teodora.springcloud.model.User;

public interface UserService {
	User create(String firstName, String lastName, Date birthDate, String email, String password);
	User getUser(Long id);
	void updateUser(Long id, String firstName, String lastName, Date birthDate, String email, String password);
	void deleteUser(User user);
	List<User> getUsers();
    void registerUser(User user);
}
