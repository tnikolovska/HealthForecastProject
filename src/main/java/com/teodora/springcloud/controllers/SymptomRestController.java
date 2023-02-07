package com.teodora.springcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teodora.springcloud.model.Symptom;
import com.teodora.springcloud.repos.SymptomRepo;

@RestController
@RequestMapping("/symptomapi")
public class SymptomRestController {
	
	@Autowired
	SymptomRepo repo;
	
	@RequestMapping(value = "/symptoms", method = RequestMethod.POST)
	public Symptom create(@RequestBody Symptom symptom) {
		return repo.save(symptom);
	}
	
	@RequestMapping(value="/symptoms/{name}", method = RequestMethod.GET)
	public Symptom getSymptom(@PathVariable("name") String name) {
		return repo.findByName(name);
		
	}
	@RequestMapping(value="/updatesymptoms", method = RequestMethod.PUT)
	public Symptom updateSymptom(@RequestBody Symptom symptom) {
		return repo.save(symptom);
	}
	@RequestMapping(value="/deletesymptom/{id}", method = RequestMethod.DELETE)
	public void deleteSymptom(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
}
