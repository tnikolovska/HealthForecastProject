package com.teodora.springcloud.service;

import java.util.List;

import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.Symptom;

public interface SymptomService {
	Symptom create(String name, String description, HealthCondition healthCondition);
	Symptom getSymptomName(String name);
	Symptom getSymptom(Long id);
	void updateSymptom(Long id, String name, String description, HealthCondition healthCondition);
	void deleteSymptom(Symptom symptom);
	List<Symptom> getSymptoms();
}
