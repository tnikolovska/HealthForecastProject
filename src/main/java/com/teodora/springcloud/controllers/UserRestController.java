package com.teodora.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.teodora.springcloud.model.User;
import com.teodora.springcloud.repos.UserRepo;

@RestController
@RequestMapping("/userapi")
public class UserRestController {
	@Autowired
	UserRepo repo;
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return repo.save(user);
	}
	
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") Long id) {
		return repo.findById(id).orElse(null);	
	}
	@RequestMapping(value="/updateusers", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		return repo.save(user);
	}
	
	@RequestMapping(value="/deleteuser/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
}
