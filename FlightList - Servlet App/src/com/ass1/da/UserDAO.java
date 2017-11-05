package com.ass1.da;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDAO {
	private SessionFactory factory;

	public UserDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public UserEntity addUser(UserEntity user) {
		if (user == null) {
			return null;
		}
		int studentId = -1;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			studentId = (Integer) session.save(user);
			user.setId(studentId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> getAllUsers() {
	

		Session session = factory.openSession();
		Transaction tx = null;
		List<UserEntity> users = null;
		try {
			tx = session.beginTransaction();
			users = session.createQuery("FROM UserEntity").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.close();
		}
		
		return users;
	}
}
