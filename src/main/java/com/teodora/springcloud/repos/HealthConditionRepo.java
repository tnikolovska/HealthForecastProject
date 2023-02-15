package com.teodora.springcloud.repos;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.HealthCondition;


@Repository
public interface HealthConditionRepo extends JpaRepository<HealthCondition, Long> {

	HealthCondition findByName(String name);
	
	
}
