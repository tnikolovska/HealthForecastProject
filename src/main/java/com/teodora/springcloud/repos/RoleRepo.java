package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
