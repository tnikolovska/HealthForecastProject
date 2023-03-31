package com.teodora.springcloud.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.teodora.springcloud.dao.HealthConditionDao;
import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.Symptom;
import com.teodora.springcloud.model.UserHealthCondition;
import com.teodora.springcloud.repos.CategoryRepo;
import com.teodora.springcloud.repos.HealthConditionRepo;
import com.teodora.springcloud.repos.UserHealthConditionRepo;


@RestController
@RequestMapping("/userhealthconditionapi")
public class UserHealthConditionRestController {
	@Autowired
	UserHealthConditionRepo repo;
	
	
	@Autowired
	HealthConditionRepo healthrepo;
	
	@Autowired
	HealthConditionDao healthConditionDao;
	
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
	
	@RequestMapping(value="userHealthCondition",method = RequestMethod.GET)
	public String detailsUserHealthCondition(@RequestParam Long id, Model model) {
		//Category category = getCategory(name);
		UserHealthCondition userHealthCondition=repo.getReferenceById(id);
		//Category category=categoryService.getCategoryName(name);
		model.addAttribute("user", userHealthCondition.getUser());
		model.addAttribute("healthCondition", userHealthCondition.getHealthCondition());
		return "userHealthCondition";	
		
	}
	
	@GetMapping("userHealthCondition-list")
	public String userHealthConditions(Model model) {
		List<UserHealthCondition> userHealthConditions = new ArrayList<>();
		//categories=getCategories();
		userHealthConditions=repo.findAll();
		//categories=categoryService.getCategories();
		//List<String> names=categories.stream().map(Category::getName).collect(Collectors.toList());
		model.addAttribute("userHealthConditions",userHealthConditions);
		return "userHealthCondition-list";
	}
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/userHealthCondition-edit/{id}")
	public String showUpdateForm(@PathVariable("id")Long id,Model model) {
		//Category updateCategory = getCategory(id);
		UserHealthCondition userHealthCondition = repo.getReferenceById(id);
		//Category updateCategory=(Category)categoryService.getCategory(id);
		model.addAttribute("userHealthCondition",userHealthCondition);
		return "update-userHealthCondition";
	}
	
	@PostMapping("/userHealthCondition-update/{id}")
	public String updateUserHealthCondition(@PathVariable("id") Long id,@Validated UserHealthCondition userHealthCondition,BindingResult result, Model model) {
		if(result.hasErrors()) {
			userHealthCondition.setId(id);
			return "update-userHealthCondition";
		}
		//updateCategory(category);
		repo.save(userHealthCondition);
		//categoryService.updateCategory(id,model.getAttribute("name").toString(), new BigDecimal(model.getAttribute("beginRange").toString()), new BigDecimal(model.getAttribute("endRange").toString()));
		return "redirect:/userHealthCondition-list";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteUserHelathCondition(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		//Category category=categoryService.getCategory(id);
		//categoryService.deleteCategory(category);
		return "redirect:/userHealthCondition-list";
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/createUserHealthConditionView")
	public String createUserHealthConditionView(Model model) {
			UserHealthCondition userHealthCondition = new UserHealthCondition();
			model.addAttribute("userHealthCondition",userHealthCondition);
			return "create-userhealthcondition";
					
	}
	@PostMapping("/createUserhealthcondition")
	public String createCategory(@ModelAttribute("userHealthCondition") UserHealthCondition userHealthCondition) {
		//categoryDao.create(category);
		//create(category);
		repo.save(userHealthCondition);
		return "redirect:/userHealthCondition-list";
	}
	
	
	
	
}
