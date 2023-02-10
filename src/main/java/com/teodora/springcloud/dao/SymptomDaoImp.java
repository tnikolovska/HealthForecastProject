package com.teodora.springcloud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.teodora.springcloud.model.Symptom;
import com.teodora.springcloud.utils.HibernateUtil;

public class SymptomDaoImp implements SymptomDao {
	
	private static final SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public Long create(Symptom symptom) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		session.beginTransaction();
        session.save(symptom);
        session.getTransaction().commit();
        return symptom.getId();
	}

	@Override
	public Symptom getSymptomName(String name) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        Symptom symptom = session.createQuery("from Symptom c where c.name = :name", Symptom.class)
                .setParameter("name",name)
                .getSingleResult();
        session.getTransaction().commit();
        session.close();
        return symptom;
	}

	@Override
	public void updateSymptom(Symptom symptom) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.update(symptom);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public void deleteSymptom(Symptom symptom) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.delete(symptom);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public List<Symptom> getSymptoms() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        List<Symptom> symptoms = session.createQuery("from Symptom", Symptom.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return symptoms;
	}


	@Override
	public Symptom getSymptom(Long id) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        Symptom symptom = session.createQuery("from Symptom c where c.id = :id", Symptom.class).setParameter("id",id).getSingleResult();
        session.getTransaction().commit();
        session.close();
        return symptom;
		
	}
	
}
