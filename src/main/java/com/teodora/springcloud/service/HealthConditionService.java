package com.teodora.springcloud.service;

import java.util.List;

import com.teodora.springcloud.model.HealthCondition;


public interface HealthConditionService {
	HealthCondition create(String name, String description);
	HealthCondition getHealthCondition(Long id);
	void updateHealthCondition(HealthCondition healthCondition);
	void deleteHealthCondition(HealthCondition healthCondition);
	List<HealthCondition> getHealthConditions();
}
