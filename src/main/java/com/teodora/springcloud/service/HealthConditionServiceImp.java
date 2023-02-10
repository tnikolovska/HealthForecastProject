package com.teodora.springcloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.teodora.springcloud.dao.HealthConditionDao;
import com.teodora.springcloud.model.HealthCondition;

public class HealthConditionServiceImp implements HealthConditionService {
	@Autowired
	HealthConditionDao healthConditionDao;

	@Override
	public HealthCondition create(String name, String description) {
		// TODO Auto-generated method stub
		HealthCondition healthCondition  = new HealthCondition(name,description);
		healthConditionDao.create(healthCondition);
		return healthCondition;
		
	}

	@Override
	public HealthCondition getHealthCondition(Long id) {
		// TODO Auto-generated method stub
		return healthConditionDao.getHealthCondition(id);
	}

	@Override
	public void updateHealthCondition(HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		healthConditionDao.updateHealthCondition(healthCondition);
	}

	@Override
	public void deleteHealthCondition(HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		healthConditionDao.deleteHealthCondition(healthCondition);
	}

	@Override
	public List<HealthCondition> getHealthConditions() {
		// TODO Auto-generated method stub
		return healthConditionDao.getHealthConditions();
	}
	
}
