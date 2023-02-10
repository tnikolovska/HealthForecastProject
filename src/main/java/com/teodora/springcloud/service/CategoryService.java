package com.teodora.springcloud.service;

import java.math.BigDecimal;
import java.util.List;




import com.teodora.springcloud.model.Category;

public interface CategoryService {
	Category create(String name,BigDecimal beginRange,BigDecimal endRange);
	Category getCategory(String name);
	void updateCategory(Long id, String name, BigDecimal beginRange, BigDecimal endRange);
	void deleteCategory(Category category);
	List<Category> getCategories();
	
}
