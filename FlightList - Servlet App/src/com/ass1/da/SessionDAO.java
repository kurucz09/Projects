package com.ass1.da;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class SessionDAO {
	private SessionFactory factory;

	public SessionDAO(SessionFactory factory) {
		this.factory = factory;
	}

	public SessionEntity addSession(SessionEntity sess) {
		if (sess == null) {
			return null;
		}
		int sessionId = -1;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			sessionId = (Integer) session.save(sess);
			sess.setId(sessionId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			session.close();
		}
		return sess;
	}

	@SuppressWarnings("unchecked")
	public List<SessionEntity> getAllSessions() {

		Session session = factory.openSession();
		Transaction tx = null;
		List<SessionEntity> sessions = null;
		try {
			tx = session.beginTransaction();
			sessions = session.createQuery("FROM SessionEntity").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.close();
		}

		return sessions;
	}

	public void deleteSession(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("DELETE from SessionEntity WHERE id = :id");
			query.setParameter("id", id);
			query.executeUpdate();
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}
}
