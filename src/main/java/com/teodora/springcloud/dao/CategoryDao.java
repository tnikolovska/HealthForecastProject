package com.teodora.springcloud.dao;


import java.util.List;



import com.teodora.springcloud.model.Category;

public interface CategoryDao {
	long create(Category category);
	Category getCategoryName(String name);
	Category getCategory(Long id);
	void updateCategory(Category category);
	void deleteCategory(Category category);
	List<Category> getCategories();
}
