package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.Symptom;

@Repository
public interface SymptomRepo  extends JpaRepository<Symptom, Long> {

	Symptom findByName(String name);

}
