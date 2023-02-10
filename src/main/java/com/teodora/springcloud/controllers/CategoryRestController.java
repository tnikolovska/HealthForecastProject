package com.teodora.springcloud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teodora.springcloud.model.Category;
import com.teodora.springcloud.repos.CategoryRepo;


@RestController
@RequestMapping("/cateogoryapi")
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
	@RequestMapping(value="/updatecategories", method = RequestMethod.PUT)
	public Category updateCategory(@RequestBody Category category) {
		return repo.save(category);
	}
	@RequestMapping(value="/deletecateogory/{id}", method = RequestMethod.DELETE)
	public void deleteCategory(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	@RequestMapping(value="/categories/list", method = RequestMethod.GET)
	public List<Category> getCategories() {
		return repo.findAll();
		
	}
}	
