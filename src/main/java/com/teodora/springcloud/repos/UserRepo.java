package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
