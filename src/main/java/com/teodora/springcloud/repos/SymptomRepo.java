package com.teodora.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.teodora.springcloud.model.Symptom;

public interface SymptomRepo  extends JpaRepository<Symptom, Long> {

	Symptom findByName(String name);

}
