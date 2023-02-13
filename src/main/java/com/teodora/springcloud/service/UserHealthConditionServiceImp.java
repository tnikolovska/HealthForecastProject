package com.teodora.springcloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teodora.springcloud.dao.UserHealthConditionDao;
import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.User;
import com.teodora.springcloud.model.UserHealthCondition;

@Service
public class UserHealthConditionServiceImp implements UserHealthConditionService {
	
	
	@Autowired
	UserHealthConditionDao userHealthConditionDao;

	@Override
	public UserHealthCondition create(User user, HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		UserHealthCondition userHealthCondition = new UserHealthCondition(user,healthCondition);
		userHealthConditionDao.create(userHealthCondition);
		return userHealthCondition;
		
	}

	@Override
	public UserHealthCondition getUserHealthCondition(Long id) {
		// TODO Auto-generated method stub
		return userHealthConditionDao.getUserHealthCondition(id);
	}

	@Override
	public void updateUserHealthCondition(Long id, User user, HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		UserHealthCondition updateUserHealthCondition = userHealthConditionDao.getUserHealthCondition(id);
		updateUserHealthCondition.setUser(user);
		updateUserHealthCondition.setHealthCondition(healthCondition);
		userHealthConditionDao.updateUserHealthCondition(updateUserHealthCondition);
	}

	@Override
	public void deleteUserHealthCondition(UserHealthCondition userHealthCondition) {
		// TODO Auto-generated method stub
		userHealthConditionDao.deleteUserHealthCondition(userHealthCondition);
	}

	@Override
	public List<UserHealthCondition> getUserHealthConditions() {
		// TODO Auto-generated method stub
		return userHealthConditionDao.getUserHealthConditions();
	}

}
