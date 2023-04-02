package com.teodora.springcloud.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableColumnModelListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.bind.annotation.RestController;

import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.Symptom;
import com.teodora.springcloud.repos.HealthConditionRepo;
import com.teodora.springcloud.repos.SymptomRepo;

@Controller
@RequestMapping("/symptomapi")
public class SymptomRestController {
	
	@Autowired
	SymptomRepo repo;
	@Autowired
	HealthConditionRepo healthRepo;
	
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
	
	@RequestMapping(value="symptom",method = RequestMethod.GET)
	public String detailsSymptom(@RequestParam Long id, Model model) {
		//Symptom symptom = getSymptom(id);
		Symptom symptom = repo.getReferenceById(id);
		model.addAttribute("name", symptom.getName());
		model.addAttribute("description", symptom.getDescription());
		model.addAttribute("healthCondition",symptom.getHealthCondition());
		return "symptom";
		
	}
	@GetMapping("symptom-list")
	public String symptoms(Model model) {
		List<Symptom> symptoms = new ArrayList<Symptom>();
		//symptoms=getSymptoms();
		symptoms=repo.findAll();
		model.addAttribute("symptoms",symptoms);
		//return "symptom-list";
		return "Symptoms";
	}
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/edit-symptom/{id}")
	public String showSymptomUpdateForm(@PathVariable("id")Long id,Model model) {
		//Symptom updatesymptom = getSymptom(id);
		Symptom updatesymptom = repo.getReferenceById(id);
		model.addAttribute("symptom",updatesymptom);
		model.addAttribute("healthCondition","Arthritis Pain");
		return "update-symptom";
	}
	@PostMapping("/update-symptom/{id}")
	public String updateSymptom(@PathVariable("id") Long id,@Validated Symptom symptom,BindingResult result, Model model) {
		if(result.hasErrors()) {
			symptom.setId(id);
			return "update-symptom";
		}
		//updateSymptom(symptom);
		HealthCondition healthCondition = healthRepo.getReferenceById(symptom.getHealthCondition().getId());
		//symptom.setHealthCondition(healthCondition);
		symptom.setHealthCondition(healthCondition);
		repo.save(symptom);
		return "redirect:/symptom-list";	
	}
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/delete-symptom/{id}")
	public String deleteSymptom(@PathVariable("id") Long id, Model model) {
		//delete(id);
		repo.deleteById(id);
		return "redirect:/symptom-list";
	}
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/createSymptomView")
	public String getRegisterSymptomPage(Model model) {
		Symptom symptom = new Symptom();
		model.addAttribute("symptom",symptom);
		//model.addAttribute("healthCondition","Arthritis Pain");
		return "create-symptom";
	}
	
	@GetMapping("/createArthritisPainSymptomView/{id}")
	public String getRegisterArthritisSymptomPage(@PathVariable("id") String id,Model model) {
		Symptom symptom = new Symptom();
		HealthCondition healthCondition = healthRepo.getReferenceById(Long.parseLong(id));
		symptom.setHealthCondition(healthCondition);
		model.addAttribute("symptom",symptom);
		return "create-symptom";
	}
	
	@GetMapping("/createSinusSymptomView/{id}")
	public String getRegisterSinusSymptomPage(@PathVariable("id") String id,Model model) {
		Symptom symptom = new Symptom();
		HealthCondition healthCondition = healthRepo.getReferenceById(Long.parseLong(id));
		symptom.setHealthCondition(healthCondition);
		model.addAttribute("symptom",symptom);
		return "create-symptom";
	}
	
	@GetMapping("/createMigraineSymptomView/{id}")
	public String getRegisterMigraineSymptomPage(@PathVariable("id") String id,Model model) {
		Symptom symptom = new Symptom();
		HealthCondition healthCondition = healthRepo.getReferenceById(Long.parseLong(id));
		symptom.setHealthCondition(healthCondition);
		model.addAttribute("symptom",symptom);
		return "create-symptom";
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/create-symptom")
	public String createSymptom(@ModelAttribute("symptom") Symptom symptom, Model model) {
		//create(symptom);
		//HealthCondition healthCondition = healthRepo.getReferenceById(Long.parseLong(model.getAttribute("healthCondition_id").toString()));
		//Hea
		HealthCondition healthCondition = healthRepo.getReferenceById(symptom.getHealthCondition().getId());
		//symptom.setHealthCondition(healthCondition);
		symptom.setHealthCondition(healthCondition);
		repo.save(symptom);
		//return "redirect:/symptom-list";
		return "redirect:/healthconditionapi/healthCondition?id="+symptom.getHealthCondition().getId();
	}
	/*@GetMapping("/create-healthConditionSymptom/{id}")
	public String createHealthConditionSymptom(@PathVariable("id") Long id,Model model) {
		//create(symptom);
		Symptom symptom = new Symptom();
		HealthCondition healthCondition = healthRepo.getReferenceById(id);
		symptom.setHealthCondition(healthCondition);
		model.addAttribute("symptom",symptom);
		//model.addAttribute("healthConditionName",healthCondition.getId());
		//return "Create-Symptom";
		return "Create-Symptom";
		}*/
	
	/*@GetMapping("/createHealthConditionSymptomView/{id}")
	public String createHealthConditionSymptom(@PathVariable("id") Long id,Model model) {
		Symptom symptom = new Symptom();
		HealthCondition healthCondition=healthRepo.getReferenceById(id);
		model.addAttribute("symptom",symptom);
		model.addAttribute("healthCondition",healthCondition);
		return "create-healthConditionSymptom";
	    }*/
	
	
	
	
}
