package com.teodora.springcloud.controllers;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.teodora.springcloud.dao.HealthConditionDao;
import com.teodora.springcloud.dao.HealthConditionDaoImp;
import com.teodora.springcloud.dao.SymptomDao;
import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.Symptom;
import com.teodora.springcloud.model.User;
import com.teodora.springcloud.model.UserHealthCondition;
import com.teodora.springcloud.model.UserHealthConditionSymptom;
import com.teodora.springcloud.repos.HealthConditionRepo;
import com.teodora.springcloud.repos.SymptomRepo;
import com.teodora.springcloud.repos.UserHealthConditionRepo;
import com.teodora.springcloud.repos.UserRepo;

@Controller
public class UserHealthConditionSymptomController {
	
	@Autowired
	UserHealthConditionRepo repo;
	
	
	@Autowired
	HealthConditionRepo healthrepo;
	
	@Autowired
	HealthConditionDao healthConditionDao;
	
	@Autowired
	SymptomRepo symptomRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/user-health-condition")
	public String userHealthCondition(Model model) {
		UserHealthConditionSymptom userHealthConditionSymptom = new UserHealthConditionSymptom();
		List<HealthCondition> healthConditions=healthrepo.findAll();
		//List<Symptom> arthritisSymptoms = healthConditionDao.getSymptoms(healthrepo.findByName("Arthritis Pain").getId());
		//List<Symptom> arthritisSymptoms = healthConditionDao.getSymptoms(healthrepo.findByName("Arthritis Pain").getId());
		List<Symptom> arthritisSymptoms  = symptomRepo.findAll().stream().filter(s->s.getHealthCondition().getName().equals("Arthritis Pain")).collect(Collectors.toList());
		List<String> arthritisSymptomsName = arthritisSymptoms.stream().map(s->s.getName()).collect(Collectors.toList());
		List<Symptom> migraineSymptoms  = symptomRepo.findAll().stream().filter(s->s.getHealthCondition().getName().equals("Migraine Headache")).collect(Collectors.toList());
		List<String> migraineSymptomsName = migraineSymptoms.stream().map(s->s.getName()).collect(Collectors.toList());
		List<Symptom> sinusSymptoms = symptomRepo.findAll().stream().filter(s->s.getHealthCondition().getName().equals("Sinus Headache")).collect(Collectors.toList());
		List<String> sinusSymptomsName = sinusSymptoms.stream().map(s->s.getName()).collect(Collectors.toList());
		model.addAttribute("healthConditions",healthConditions);
		model.addAttribute("arthritisSymptoms",arthritisSymptomsName);
		model.addAttribute("migraineSymptoms",migraineSymptomsName);
		model.addAttribute("sinusSymptoms",sinusSymptomsName);
		model.addAttribute("userHealthConditionSymptom",userHealthConditionSymptom);
		return "userHealthCondition";
	}
	
	@PostMapping("/determine-user-health")
	public String determineUserHealthCondition(@ModelAttribute("healthCondition") UserHealthConditionSymptom userHealthConditionSymptom,Model model) {
		HealthCondition healthCondition = healthrepo.getReferenceById(userHealthConditionSymptom.getHealthCondition().getId());
		Object listArthritis = model.getAttribute("arthritisSymptoms");
		Object listMigraine = model.getAttribute("migraineSymptoms");
		Object listSinus = model.getAttribute("sinusSymptoms");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getPrincipal().toString();
		User user = userRepo.findByEmail(username);
		userHealthConditionSymptom.setHealthCondition(healthCondition);
		userHealthConditionSymptom.setUser(user);
		return "";
	}
}
