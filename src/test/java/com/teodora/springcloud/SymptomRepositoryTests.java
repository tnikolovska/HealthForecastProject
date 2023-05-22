package com.teodora.springcloud;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.repos.HealthConditionRepo;
import com.teodora.springcloud.repos.SymptomRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class SymptomRepositoryTests {
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	SymptomRepo repo;
	
	@Test
	public void createSymptom() {
		
		/*HealthCondition migraineHealthCondition = new HealthCondition();
		migraineHealthCondition.setName("Migraine Headache");
		migraineHealthCondition.setDescription("Migraine Headache");
		
		HealthCondition savedMigraineHealthConditon = repo.save(migraineHealthCondition);
		HealthCondition existMigraineHealthCondition = entityManager.find(HealthCondition.class,savedMigraineHealthConditon.getId());
		
		
		HealthCondition healthCondition = new HealthCondition();
		healthCondition.setName("Arthritis Pain");
		healthCondition.setDescription("Arthritis Pain");
		
		HealthCondition savedHealthConditon = repo.save(healthCondition);
		HealthCondition existArthritisHealthCondition = entityManager.find(HealthCondition.class,savedHealthConditon.getId());
		
		HealthCondition sinusHealthCondition = new HealthCondition();
		sinusHealthCondition.setName("Sinus Headache");
		sinusHealthCondition.setDescription("Sinus Headache");
		
		HealthCondition savedSinusHealthConditon = repo.save(sinusHealthCondition);
		HealthCondition existSinusHealthCondition = entityManager.find(HealthCondition.class,savedSinusHealthConditon.getId());
		List<HealthCondition> listRoles = repo.findAll();
		
		assertThat(listRoles.size()).isEqualTo(3);*/
		
	}
}
