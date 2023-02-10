package com.teodora.springcloud.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.teodora.springcloud.model.Forecast;
import com.teodora.springcloud.utils.HibernateUtil;

public class ForecastDaoImp implements ForecastDao {
	
	private static final SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public long create(Forecast forecast) {
		// TODO Auto-generated method stub
		Session session=sf.openSession();
		session.beginTransaction();
        session.save(forecast);
        session.getTransaction().commit();
        return forecast.getId();
	}

	@Override
	public Forecast getForecast(Long id) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        Forecast forecast = session.createQuery("from Forecast c where c.id = :id", Forecast.class)
                .setParameter("id",id)
                .getSingleResult();;
        session.getTransaction().commit();
        session.close();
        return forecast;
	}

	@Override
	public void updateForecast(Forecast forecast) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.update(forecast);
        session.getTransaction().commit();
        session.close();
	}

	@Override
	public void deleteForecast(Forecast forecast) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        session.delete(forecast);
        session.getTransaction().commit();
        session.close();
		
	}

	@Override
	public List<Forecast> getForecasts() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        session.beginTransaction();
        List<Forecast> forecasts = session.createQuery("from Forecast", Forecast.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return forecasts;
	}

}
