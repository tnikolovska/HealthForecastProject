package com.teodora.springcloud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.HealthCondition;
import com.teodora.springcloud.model.Symptom;
import com.teodora.springcloud.utils.HibernateUtil;

@Repository
public class HealthConditionDaoImp implements HealthConditionDao {
	
	private static final SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public long create(HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		session.beginTransaction();
        session.save(healthCondition);
        session.getTransaction().commit();
        return healthCondition.getId();
	}

	@Override
	public HealthCondition getHealthCondition(Long id) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        HealthCondition healthCondition = session.createQuery("from HealthCondition c where c.id = :id", HealthCondition.class)
                .setParameter("id",id)
                .getSingleResult();;
        session.getTransaction().commit();
        session.close();
        return healthCondition;
	}

	@Override
	public void updateHealthCondition(HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.update(healthCondition);
        session.getTransaction().commit();
        session.close();
		
	}

	@Override
	public void deleteHealthCondition(HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.delete(healthCondition);
        session.getTransaction().commit();
        session.close();
		
	}

	@Override
	public List<HealthCondition> getHealthConditions() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        List<HealthCondition> healthConditions = session.createQuery("from HealthCondition", HealthCondition.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return healthConditions;
	}

	@Override
	public List<Symptom> getSymptoms(Long id) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		session.beginTransaction();
		List<Symptom> symptoms = session.createQuery("from Symptom s where s.healthCondition_id = :id", Symptom.class).getResultList();
		return symptoms;
	}

	@Override
	public List<Symptom> setSymptoms(List<Symptom> symptoms, HealthCondition healthCondition) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
		session.beginTransaction();
		symptoms.stream().forEach(s->s.setHealthCondition(healthCondition));
		symptoms.stream().forEach(s->session.save(s));
		session.getTransaction().commit();
		session.close();
	    return symptoms;   
	}

	

	

}
