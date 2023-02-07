package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teodora.springcloud.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

	Category findByName(String name);

}
