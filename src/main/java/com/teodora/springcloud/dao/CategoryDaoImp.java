package com.teodora.springcloud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.teodora.springcloud.model.Category;
import com.teodora.springcloud.utils.HibernateUtil;



public class CategoryDaoImp implements CategoryDao {
	
	private static final SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@Override
	public long create(Category category) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        return category.getId();
	}

	@Override
	public Category getCategoryName(String name) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        Category category = session.createQuery("from Category c where c.name = :name", Category.class)
                .setParameter("name",name)
                .getSingleResult();;
        session.getTransaction().commit();
        session.close();
        return category;
	}
	
	@Override
	public Category getCategory(Long id) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        Category category = session.createQuery("from Category c where c.id = :id", Category.class)
                .setParameter("id",id)
                .getSingleResult();;
        session.getTransaction().commit();
        session.close();
        return category;
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.update(category);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public void deleteCategory(Category category) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		  Session session = sf.openSession();
	        session.beginTransaction();
	        List<Category> categories = session.createQuery("from Category", Category.class).getResultList();
	        session.getTransaction().commit();
	        session.close();
	        return categories;
	}
	
}
