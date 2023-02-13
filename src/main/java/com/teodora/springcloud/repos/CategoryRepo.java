package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

	Category findByName(String name);

}
