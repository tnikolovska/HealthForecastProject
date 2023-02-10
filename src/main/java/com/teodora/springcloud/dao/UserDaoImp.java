package com.teodora.springcloud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.teodora.springcloud.model.User;
import com.teodora.springcloud.utils.HibernateUtil;

public class UserDaoImp implements UserDao {

	private static final SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@Override
	public Long create(User user) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return user.getId();
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        User user = session.createQuery("from User c where c.id = :id", User.class)
                .setParameter("id",id)
                .getSingleResult();;
        session.getTransaction().commit();
        session.close();
        return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
		
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User", User.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
	}
	
	
	
}
