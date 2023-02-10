package com.teodora.springcloud.dao;




import java.util.List;

import com.teodora.springcloud.model.Symptom;

public interface SymptomDao {
	Long create(Symptom symptom);
	Symptom getSymptomName(String name);
	Symptom getSymptom(Long id);
	void updateSymptom(Symptom symptom);
	void deleteSymptom(Symptom symptom);
	List<Symptom> getSymptoms();
}
