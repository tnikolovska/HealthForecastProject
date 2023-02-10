package com.teodora.springcloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.teodora.springcloud.dao.SymptomDao;
import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.Symptom;

public class SymptomServiceImp implements SymptomService {

	@Autowired
	SymptomDao symptomDao;
	
	@Override
	public Symptom create(String name, String description, HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		Symptom symptom=new Symptom(name,description,healthCondition);
		symptomDao.create(symptom);
		return symptom;
		
	}

	@Override
	public Symptom getSymptomName(String name) {
		// TODO Auto-generated method stub
		return symptomDao.getSymptomName(name);
	}
	
	
	@Override
	public void updateSymptom(Long id, String name, String description, HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		Symptom updateSymptom = symptomDao.getSymptom(id);
		updateSymptom.setName(name);
		updateSymptom.setDescription(description);
		updateSymptom.setHealthCondition(healthCondition);
		symptomDao.updateSymptom(updateSymptom);
	}

	@Override
	public void deleteSymptom(Symptom symptom) {
		// TODO Auto-generated method stub
		symptomDao.deleteSymptom(symptom);
	}

	@Override
	public List<Symptom> getSymptoms() {
		// TODO Auto-generated method stub
		return symptomDao.getSymptoms();
	}

	@Override
	public Symptom getSymptom(Long id) {
		// TODO Auto-generated method stub
		return symptomDao.getSymptom(id);
	}

}
