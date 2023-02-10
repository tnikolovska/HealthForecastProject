package com.teodora.springcloud.dao;


import java.util.List;

import com.teodora.springcloud.model.UserHealthCondition;

public interface UserHealthConditionDao {
	Long create(UserHealthCondition userHealthCondition);
	UserHealthCondition getUserHealthCondition(Long id);
	void updateUserHealthCondition(UserHealthCondition userHealthCondition);
	void deleteUserHealthCondition(UserHealthCondition userHealthCondition);
	List<UserHealthCondition> getUserHealthConditions();
	
}
