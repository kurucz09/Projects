package com.ass1.da;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CityDAO {
	private SessionFactory factory;

	public CityDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CityEntity findCity(String name) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<CityEntity> cities = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM CityEntity WHERE name = :name");
			query.setParameter("name", name);
			cities = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return cities != null && !cities.isEmpty() ? cities.get(0) : null;
	}
	
}
