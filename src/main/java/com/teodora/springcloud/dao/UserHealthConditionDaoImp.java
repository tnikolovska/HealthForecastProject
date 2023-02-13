package com.teodora.springcloud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.teodora.springcloud.model.UserHealthCondition;
import com.teodora.springcloud.utils.HibernateUtil;

@Repository
public class UserHealthConditionDaoImp implements UserHealthConditionDao {

	private static final SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@Override
	public Long create(UserHealthCondition userHealthCondition) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		session.beginTransaction();
        session.save(userHealthCondition);
        session.getTransaction().commit();
        return userHealthCondition.getId();
	}

	@Override
	public UserHealthCondition getUserHealthCondition(Long id) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        UserHealthCondition userHealthCondition = session.createQuery("from UserHealthCondition c where c.id = :id", UserHealthCondition.class)
                .setParameter("id",id)
                .getSingleResult();;
        session.getTransaction().commit();
        session.close();
        return userHealthCondition;
	}

	@Override
	public void updateUserHealthCondition(UserHealthCondition userHealthCondition) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.update(userHealthCondition);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public void deleteUserHealthCondition(UserHealthCondition userHealthCondition) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.delete(userHealthCondition);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public List<UserHealthCondition> getUserHealthConditions() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        List<UserHealthCondition> userHealthConditions = session.createQuery("from UserHealthCondition", UserHealthCondition.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return userHealthConditions;
	}
	

}
