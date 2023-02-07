package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teodora.springcloud.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
