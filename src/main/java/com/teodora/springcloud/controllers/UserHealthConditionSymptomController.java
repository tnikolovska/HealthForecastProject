package com.teodora.springcloud.controllers;

import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import com.teodora.springcloud.repos.UserHealthConditionSymptomRepo;
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
	
	@Autowired
	UserHealthConditionSymptomRepo userHealthConditionSymptomRepo;
	
	@GetMapping("user-health-condition")
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
	public String determineUserHealthCondition(@ModelAttribute("userHealthConditionSymptom") UserHealthConditionSymptom userHealthConditionSymptom,Model model, RedirectAttributes redirectAttributes) {
		HealthCondition healthCondition = healthrepo.getReferenceById(userHealthConditionSymptom.getHealthCondition().getId());
		/*Object listArthritis = model.getAttribute("arthritisSymptoms");
		Object listMigraine = model.getAttribute("migraineSymptoms");
		Object listSinus = model.getAttribute("sinusSymptoms");*/
		//userHealthConditionSymptom.setUserSymptoms(new ArrayList<String>());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getPrincipal().toString();
		User user = userRepo.findByEmail(username);
		userHealthConditionSymptom.setHealthCondition(healthCondition);
		userHealthConditionSymptom.setUser(user);
		//List<String> userSymptoms = userHealthConditionSymptom.getUserSymptoms();
		//userHealthConditionSymptom.setUserSymptoms(userSymptoms);
		model.addAttribute("userHealthConditionSymptom",userHealthConditionSymptom);
		model.addAttribute("userSymptoms",userHealthConditionSymptom.getUserSymptoms());
		
		//userSymptoms = new ArrayList<String>();
		//redirectAttributes.addAttribute("id", healthCondition.getId());
		if(healthCondition.getName().equals("Arthritis Pain")) {
			if(userHealthConditionSymptom.getUserSymptoms().size()>0) {
				userHealthConditionSymptomRepo.save(userHealthConditionSymptom);
				return "redirect:/result/"+healthCondition.getId();
			}
		}
		else if(healthCondition.getName().equals("Migraine Headache")) {
			if(userHealthConditionSymptom.getUserSymptoms().size()>0) {
				userHealthConditionSymptomRepo.save(userHealthConditionSymptom);
				return "redirect:/result/"+healthCondition.getId();
			}
		}
		else if(healthCondition.getName().equals("Sinus Headache")){
			if(userHealthConditionSymptom.getUserSymptoms().size()>0) {
				userHealthConditionSymptomRepo.save(userHealthConditionSymptom);
				return "redirect:/result/"+healthCondition.getId();
			}
		}
		
		return "redirect:/user-health-condition";
		
	}
}
