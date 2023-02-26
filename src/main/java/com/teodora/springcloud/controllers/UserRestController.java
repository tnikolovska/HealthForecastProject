package com.teodora.springcloud.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teodora.springcloud.exception.ErrorResponse;
import com.teodora.springcloud.exception.UserAlreadyExistsException;
import com.teodora.springcloud.model.User;
import com.teodora.springcloud.repos.UserRepo;
import com.teodora.springcloud.service.UserService;
import com.teodora.springcloud.utils.UserUtil;
import com.teodora.springcloud.web.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/userapi")
@Controller
public class UserRestController {
	@Autowired
	UserRepo repo;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return repo.save(user);
	}
	
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") Long id) {
		return repo.findById(id).orElse(null);	
	}
	@RequestMapping(value="/updateusers", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		return repo.save(user);
	}
	
	@RequestMapping(value="/deleteuser/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	@RequestMapping(value="/users/list", method = RequestMethod.GET)
	public List<User> getUsers() {
		return repo.findAll();
		
	}
	
	//@RequestMapping(value="user",method = RequestMethod.GET)
	@GetMapping("/user/{id}")
	public String detailsUser(@PathVariable("id")Long id, Model model) {
		//User user = getUser(name);
		User user = repo.getReferenceById(id);
		//User user=categoryService.getCategoryName(name);
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("birthDate",user.getBirthDate());
		model.addAttribute("email",user.getEmail());
		return "user";
		
	}
	@GetMapping("user-list")
	public String users(Model model) {
		List<User> users = new ArrayList<>();
		//users=getUsers();
		users=repo.findAll();
		//users=userService.getUsers();
		//List<String> names=categories.stream().map(Category::getName).collect(Collectors.toList());
		model.addAttribute("users",users);
		return "user-list";
	}
	@GetMapping("/edit-user/{id}")
	public String showUpdateForm(@PathVariable("id")Long id,Model model) {
		//User updateUser = getUser(id);
		User updateUser = repo.getReferenceById(id);
		//User updateUser=(User)userService.getUser(id);
		model.addAttribute("user",updateUser);
		return "update-user";
	}
	@PostMapping("/update-user/{id}")
	public String updateUser(@PathVariable("id") Long id,@Validated User user,BindingResult result, Model model) {
		if(result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}
		//updateUser(user);
		repo.save(user);
		//userService.updateUser(id,model.getAttribute("name").toString(), new BigDecimal(model.getAttribute("beginRange").toString()), new BigDecimal(model.getAttribute("endRange").toString()));
		return "redirect:/user-list";
	}
	@GetMapping("/delete-user/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		//User user=userService.getUser(id);
		//userService.deleteUser(user);
		return "redirect:/user-list";
	}
	
	@GetMapping("/createUserView")
	public String getUserRegisterPage(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		model.addAttribute("existedUsername",null);
		return "user-register";
	    }
	
	@PostMapping("/createuser")
	public String createUser(@ModelAttribute("user") @Validated @Valid User user, RedirectAttributes redirectAttributes,Model model,BindingResult bindingResult) {
		User existedUsername = repo.findByEmail(user.getEmail());
		model.addAttribute("existedUsername",existedUsername);
		if(existedUsername!=null) {
			bindingResult.rejectValue("email", null,"There is already an account registered with the same email");
			}
		if(bindingResult.hasErrors()) {
			model.addAttribute("user",user);
			return "user-register";
		}
		userService.registerUser(user);
		redirectAttributes.addAttribute("id", user.getId());
		return "redirect:/user/{id}";
	}
	
	@ExceptionHandler(value=UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
	}
	
	
}
