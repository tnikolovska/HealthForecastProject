package com.teodora.springcloud.dao;

import java.util.List;

import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.Symptom;

public interface HealthConditionDao {
	long create(HealthCondition healthCondition);
	HealthCondition getHealthCondition(Long id);
	void updateHealthCondition(HealthCondition healthCondition);
	void deleteHealthCondition(HealthCondition healthCondition);
	List<HealthCondition> getHealthConditions();
	List<Symptom> getSymptoms(Long id);
	List<Symptom> setSymptoms(List<Symptom> symptoms, HealthCondition healthCondition);
}
