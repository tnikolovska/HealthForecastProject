package com.teodora.springcloud.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.teodora.springcloud.model.HealthCondition;
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
	public Symptom getSymptomName(@PathVariable("name") String name) {
		return repo.findByName(name);
		
	}
	@RequestMapping(value="/symptoms/{id}", method = RequestMethod.GET)
	public Symptom getSymptom(@PathVariable("id") Long id) {
		return repo.getReferenceById(id);
		
	}
	@RequestMapping(value="/updatesymptoms", method = RequestMethod.PUT)
	public Symptom updateSymptom(@RequestBody Symptom symptom) {
		return repo.save(symptom);
	}
	@RequestMapping(value="/deletesymptom/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	
	@RequestMapping(value="/symptoms/list", method = RequestMethod.GET)
	public List<Symptom> getSymptoms() {
		return repo.findAll();
		
	}
	
	@RequestMapping(value="/symptom/{id}",method = RequestMethod.GET)
	public String detailsSymptom(@RequestParam Long id, Model model) {
		Symptom symptom = getSymptom(id);
		model.addAttribute("name", symptom.getName());
		model.addAttribute("description", symptom.getDescription());
		return "symptom";
		
	}
	@GetMapping("symptom-list")
	public String symptoms(Model model) {
		List<Symptom> symptoms = new ArrayList<>();
		symptoms=getSymptoms();
		model.addAttribute("symptoms",symptoms);
		return "symptom-list";
	}
	@GetMapping("/edit-symptom/{id}")
	public String showSymptomUpdateForm(@PathVariable("id")Long id,Model model) {
		Symptom updatesymptom = getSymptom(id);
		model.addAttribute("symptom",updatesymptom);
		return "update-symptom";
	}
	@PostMapping("/update-symptom/{id}")
	public String updateSymptom(@PathVariable("id") Long id,@Validated Symptom symptom,BindingResult result, Model model) {
		if(result.hasErrors()) {
			symptom.setId(id);
			return "update-symptom";
		}
		updateSymptom(symptom);
		return "redirect:/symptom-list";
	}
	@GetMapping("/delete-symptom/{id}")
	public String deleteSymptom(@PathVariable("id") Long id, Model model) {
		delete(id);
		return "redirect:/symptom-list";
	}
	
	@GetMapping("/createSymptomView")
	public String getRegisterSymptomPage(Model model) {
		Symptom symptom = new Symptom();
		model.addAttribute("symptom",symptom);
		return "create-symptom";
	    }
	
	@PostMapping("/create-symptom")
	public String createHealthCondition(@ModelAttribute("symptom") Symptom symptom) {
		create(symptom);
		return "redirect:/symptom-list";
	}
	
}
