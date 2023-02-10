package com.teodora.springcloud.dao;



import java.util.List;

import com.teodora.springcloud.model.User;

public interface UserDao {
	Long create(User user);
	User getUser(Long id);
	void updateUser(User user);
	void deleteUser(User user);
	List<User> getUsers();
}
