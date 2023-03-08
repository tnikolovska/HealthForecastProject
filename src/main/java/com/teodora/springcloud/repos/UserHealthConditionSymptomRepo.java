package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.User;
import com.teodora.springcloud.model.UserHealthConditionSymptom;

@Repository
public interface UserHealthConditionSymptomRepo extends JpaRepository<UserHealthConditionSymptom, Long> {
	
}
