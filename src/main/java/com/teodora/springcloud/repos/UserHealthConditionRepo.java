package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teodora.springcloud.model.UserHealthCondition;

public interface UserHealthConditionRepo extends JpaRepository<UserHealthCondition, Long> {

}
