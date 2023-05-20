package com.teodora.springcloud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teodora.springcloud.model.Role;
import com.teodora.springcloud.repos.RoleRepo;

//@RestController
@Controller
@RequestMapping("/roleapi")
public class RoleRestController {
	
	
	@Autowired
	RoleRepo repo;
	
	
	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	public Role create(@RequestBody Role role) {
		return repo.save(role);
	}
	
	@RequestMapping(value="/roles/{name}", method = RequestMethod.GET)
	public Role getRole(@PathVariable("name") String name) {
		return repo.findByName(name);
		
	}
	@RequestMapping(value="/roles/{id}", method = RequestMethod.GET)
	public Role getRole(@PathVariable("id") Long id) {
		return repo.getReferenceById(id);
		
	}
	
	@RequestMapping(value="/updateroles", method = RequestMethod.PUT)
	public Role updateRole(@RequestBody Role role) {
		return repo.save(role);
	}
	@RequestMapping(value="/deleterole/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	@RequestMapping(value="/roles/list", method = RequestMethod.GET)
	public List<Role> getRoles() {
		return repo.findAll();
		
	}
}
