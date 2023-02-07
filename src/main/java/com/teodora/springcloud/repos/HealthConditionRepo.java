package com.teodora.springcloud.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.teodora.springcloud.model.HealthCondition;

public interface HealthConditionRepo extends JpaRepository<HealthCondition, Long> {

	HealthCondition findByName(String name);

	
	
}
