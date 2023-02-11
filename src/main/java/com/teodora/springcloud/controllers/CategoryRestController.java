package com.teodora.springcloud.controllers;

import java.math.BigDecimal;
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
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.teodora.springcloud.model.Category;
import com.teodora.springcloud.repos.CategoryRepo;
import com.teodora.springcloud.service.CategoryService;


@Controller
//@RequestMapping("/categoryapi")
public class CategoryRestController {
	
	@Autowired
	CategoryRepo repo;	
	
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public Category create(@RequestBody Category category) {
		return repo.save(category);
	}
	
	@RequestMapping(value="/categories/{name}", method = RequestMethod.GET)
	public Category getCategory(@PathVariable("name") String name) {
		return repo.findByName(name);
		
	}
	@RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
	public Category getCategory(@PathVariable("id") Long id) {
		return repo.getReferenceById(id);
		
	}
	
	@RequestMapping(value="/updatecategories", method = RequestMethod.PUT)
	public Category updateCategory(@RequestBody Category category) {
		return repo.save(category);
	}
	@RequestMapping(value="/deletecateogory/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	@RequestMapping(value="/categories/list", method = RequestMethod.GET)
	public List<Category> getCategories() {
		return repo.findAll();
		
	}
	@RequestMapping(value="category",method = RequestMethod.GET)
	public String detailsCategory(@RequestParam String name, Model model) {
		Category category = getCategory(name);
		model.addAttribute("name", category.getName());
		model.addAttribute("beginRange", category.getBeginRange());
		model.addAttribute("endRange",category.getEndRange());
		return "category";
		
	}
	//@RequestMapping(value="category-list",method = RequestMethod.GET)
	@GetMapping("category-list")
	public String categories(Model model) {
		List<Category> categories = new ArrayList<>();
		categories=getCategories();
		//List<String> names=categories.stream().map(Category::getName).collect(Collectors.toList());
		model.addAttribute("categories",categories);
		return "category-list";
	}
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id")Long id,Model model) {
		Category updateCategory = getCategory(id);
		model.addAttribute("category",updateCategory);
		return "update-category";
	}
	@PostMapping("/update/{id}")
	public String updateCategory(@PathVariable("id") Long id,@Validated Category category,BindingResult result, Model model) {
		if(result.hasErrors()) {
			category.setId(id);
			return "update-category";
		}
		updateCategory(category);
		return "redirect:/category-list";
	}
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id") Long id, Model model) {
		delete(id);
		return "redirect:/category-list";
	}
	
	@GetMapping("/createCategoryView")
	public String getRegisterPage(Model model) {
		Category category = new Category();
		model.addAttribute("category",category);
		return "create-category";
	    }
	
	@PostMapping("/createcategory")
	public String createCategory(@ModelAttribute("category") Category category) {
		create(category);
		return "redirect:/category-list";
	}
	
	
	
	
}	
