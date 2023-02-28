package com.teodora.springcloud.controllers;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.teodora.springcloud.dao.HealthConditionDao;
import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.Symptom;
import com.teodora.springcloud.repos.HealthConditionRepo;

@Controller
//@RequestMapping("/healthconditionapi")
public class HealthConditionRestController {
	
	@Autowired
	HealthConditionRepo repo;
	@Autowired
	HealthConditionDao healthConditionDao;
	
	@RequestMapping(value = "/healthconditions", method = RequestMethod.POST)
	public HealthCondition create(@RequestBody HealthCondition healthCondition) {
		return repo.save(healthCondition);
		
	}
	
	@RequestMapping(value="/healthconditions/{name}", method = RequestMethod.GET)
	public HealthCondition getHealthConditionName(@PathVariable("name") String name) {
		return repo.findByName(name);
		
	}
	@RequestMapping(value="/healthcondition/{id}", method = RequestMethod.GET)
	public HealthCondition getHealthCondition(@PathVariable("id") Long id) {
		return repo.getReferenceById(id);
		
	}
	
	@RequestMapping(value="/updatehealthcondition", method = RequestMethod.PUT)
	public HealthCondition updateHealthCondition(@RequestBody HealthCondition healthCondition) {
		return repo.save(healthCondition);
	}
	@RequestMapping(value="/deletehealthcondition/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	@RequestMapping(value="/healthconditions/list", method = RequestMethod.GET)
	public List<HealthCondition> getHealthConditions() {
		return repo.findAll();
		
	}
	
	@RequestMapping(value="healthCondition",method = RequestMethod.GET)
	public String detailsHealthCondition(@RequestParam Long id, Model model) {
		//HealthCondition healthCondition = getHealthConditionName(name);
		HealthCondition healthCondition = repo.getReferenceById(id);
		List<Symptom> symptoms = healthConditionDao.getSymptoms(id);
		model.addAttribute("name", healthCondition.getName());
		model.addAttribute("description", healthCondition.getDescription());
		model.addAttribute("symptoms",symptoms);
		return "healthCondition";
		
	}
	@GetMapping("healthCondition-list")
	public String healthConditions(Model model) {
		List<HealthCondition> healthConditions = new ArrayList<>();
		//healthConditions=getHealthConditions();
		healthConditions=repo.findAll();
		model.addAttribute("healthConditions",healthConditions);
		//return "healthCondition-list";
		return "Health-Conditions";
	}
	@GetMapping("/edit-healthCondition/{id}")
	public String showHealthConditionUpdateForm(@PathVariable("id")Long id,Model model) {
		//HealthCondition updatehealthCondition = getHealthCondition(id);
		HealthCondition updatehealthCondition=repo.getReferenceById(id);
		model.addAttribute("healthCondition",updatehealthCondition);
		return "update-healthCondition";
	}
	@PostMapping("/update-healthCondition/{id}")
	public String updateHealthCondition(@PathVariable("id") Long id,@Validated HealthCondition healthCondition,BindingResult result, Model model) {
		if(result.hasErrors()) {
			healthCondition.setId(id);
			return "update-healthCondition";
		}
		//updateHealthCondition(healthCondition);
		repo.save(healthCondition);
		return "redirect:/healthCondition-list";
	}
	@GetMapping("/delete-healthCondition/{id}")
	public String deleteHealthCondition(@PathVariable("id") Long id, Model model) {
		//delete(id);
		repo.deleteById(id);
		return "redirect:/healthCondition-list";
	}
	
	@GetMapping("/createHealthConditionView")
	public String getRegisterHealthConditionPage(Model model) {
		HealthCondition healthCondition = new HealthCondition();
		model.addAttribute("healthCondition",healthCondition);
		return "create-healthCondition";
	    }
	
	@PostMapping("/create-healthCondition")
	public String createHealthCondition(@ModelAttribute("healthCondition") HealthCondition healthCondition) {
		//create(healthCondition);
		repo.save(healthCondition);
		return "redirect:/healthCondition-list";
	}
	@GetMapping("/createHealthConditionSymptomView/{id}")
	public String registerHealthConditionSymptom(@PathVariable("id") Long id,Model model) {
		Symptom symptom = new Symptom();
		HealthCondition healthCondition=repo.getReferenceById(id);
		symptom.setHealthCondition(healthCondition);
		model.addAttribute("symptom",symptom);
		model.addAttribute("healthcondition_id",healthCondition.getId());
		return "create-symptom";
	}
	
	
	
	
	
	
	

}
