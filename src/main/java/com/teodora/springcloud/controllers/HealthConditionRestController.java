package com.teodora.springcloud.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.repos.HealthConditionRepo;

@RestController
@RequestMapping("/healthconditionapi")
public class HealthConditionRestController {
	
	@Autowired
	HealthConditionRepo repo;
	
	@RequestMapping(value = "/healthconditions", method = RequestMethod.POST)
	public HealthCondition create(@RequestBody HealthCondition healthCondition) {
		return repo.save(healthCondition);
	}
	
	@RequestMapping(value="/healthconditions/{name}", method = RequestMethod.GET)
	public HealthCondition getHealthCondition(@PathVariable("name") String name) {
		return repo.findByName(name);
		
	}
	@RequestMapping(value="/updatehealthcondition", method = RequestMethod.PUT)
	public HealthCondition updateHealthCondition(@RequestBody HealthCondition healthCondition) {
		return repo.save(healthCondition);
	}
	@RequestMapping(value="/deletehealthcondition/{id}", method = RequestMethod.DELETE)
	public void deleteHealthCondition(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	

}
