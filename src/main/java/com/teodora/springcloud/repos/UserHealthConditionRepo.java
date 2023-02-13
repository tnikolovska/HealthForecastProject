package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.UserHealthCondition;

@Repository
public interface UserHealthConditionRepo extends JpaRepository<UserHealthCondition, Long> {

}
