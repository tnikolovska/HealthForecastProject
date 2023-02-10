package com.teodora.springcloud.service;

import java.util.List;

import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.User;
import com.teodora.springcloud.model.UserHealthCondition;

public interface UserHealthConditionService {
	UserHealthCondition create(User user, HealthCondition healthCondition);
	UserHealthCondition getUserHealthCondition(Long id);
	void updateUserHealthCondition(Long id, User user, HealthCondition healthCondition);
	void deleteUserHealthCondition(UserHealthCondition userHealthCondition);
	List<UserHealthCondition> getUserHealthConditions();
}
