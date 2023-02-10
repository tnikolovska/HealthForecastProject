package com.teodora.springcloud.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.teodora.springcloud.dao.CategoryDao;
import com.teodora.springcloud.model.Category;

public class CategoryServiceImp implements CategoryService {
	@Autowired
	CategoryDao categoryDao;

	@Override
	public Category create(String name, BigDecimal beginRange, BigDecimal endRange) {
		// TODO Auto-generated method stub
		Category category = new Category(name,beginRange,endRange);
		categoryDao.create(category);
		return category;
	}

	@Override
	public Category getCategory(String name) {
		// TODO Auto-generated method stub
		Category category = categoryDao.getCategoryName(name);
		return category;
	}

	@Override
	public void updateCategory(Long id, String name, BigDecimal beginRange, BigDecimal endRange) {
		// TODO Auto-generated method stub
		Category updatecategory = categoryDao.getCategory(id);
		updatecategory.setName(name);
		updatecategory.setBeginRange(beginRange);
		updatecategory.setEndRange(endRange);
		categoryDao.updateCategory(updatecategory);
		
	}

	@Override
	public void deleteCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.deleteCategory(category);
		
	}

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return categoryDao.getCategories();
	}
	

}
