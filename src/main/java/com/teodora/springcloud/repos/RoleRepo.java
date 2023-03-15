package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teodora.springcloud.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
