package com.teodora.springcloud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.teodora.springcloud.model.UserHealthCondition;
import com.teodora.springcloud.repos.UserHealthConditionRepo;


@RestController
@RequestMapping("/userhealthconditionapi")
public class UserHealthConditionRestController {
	@Autowired
	UserHealthConditionRepo repo;
	
	@RequestMapping(value = "/userhealthconditions", method = RequestMethod.POST)
	public UserHealthCondition create(@RequestBody UserHealthCondition userHealthCondition) {
		return repo.save(userHealthCondition);
	}
	
	@RequestMapping(value="/userhealthconditions/{id}", method = RequestMethod.GET)
	public UserHealthCondition getUserHealthCondition(@PathVariable("id") Long id) {
		return repo.findById(id).orElse(null);	
	}
	@RequestMapping(value="/updateuserhealthconditions", method = RequestMethod.PUT)
	public UserHealthCondition updateUserHealthCondition(@RequestBody UserHealthCondition userHealthCondition) {
		return repo.save(userHealthCondition);
	}
	
	@RequestMapping(value="/deleteuserhealthcondition/{id}", method = RequestMethod.DELETE)
	public void deleteUserHealthCondition(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	@RequestMapping(value="/userhealthconditions/list", method = RequestMethod.GET)
	public List<UserHealthCondition> getUserHealthConditions() {
		return repo.findAll();
		
	}
}
