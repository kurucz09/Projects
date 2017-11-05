package com.ass1.da;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FlightDAO {
	private SessionFactory factory;

	public FlightDAO(SessionFactory factory) {
		this.factory = factory;
	}
	
	@SuppressWarnings("unchecked")
	public List<FlightEntity> getAllFlights() {
	

		Session session = factory.openSession();
		Transaction tx = null;
		List<FlightEntity> flights = null;
		try {
			tx = session.beginTransaction();
			flights = session.createQuery("FROM FlightEntity").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			return null;
		} finally {
			session.close();
		}
		
		return flights;
	}
	
	public void deleteFlight(String flightNumber) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("DELETE from FlightEntity WHERE flightNumber = :flightNumber");
			query.setParameter("flightNumber", flightNumber);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FlightEntity findStudent(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<FlightEntity> flights = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM FlightEntity WHERE id = :id");
			query.setParameter("id", id);
			flights = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return flights != null && !flights.isEmpty() ? flights.get(0) : null;
	}
	
	public FlightEntity addFlight(FlightEntity flight) {
		int flightId = -1;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			flightId = (Integer) session.save(flight);
			flight.setId(flightId);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return flight;
	}

	public FlightEntity updateFlight(FlightEntity flight) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<FlightEntity> flights = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("update FlightEntity set "+
					"airplaneType = :airplaneType," +
					"departureCity = :departureCity," +
					"departureDatetime = :departureDatetime," +
					"arrivalCity = :arrivalCity," +
					"arrivalDatetime = :arrivalDatetime " +
					"where flightNumber = :flightNumber");
			query.setParameter("airplaneType", flight.getAirplaneType());
			query.setParameter("departureCity", flight.getDepartureCity());
			query.setParameter("departureDatetime", flight.getDepartureDatetime());
			query.setParameter("arrivalCity", flight.getArrivalCity());
			query.setParameter("arrivalDatetime", flight.getArrivalDatetime());
			query.setParameter("flightNumber", flight.getFlightNumber());
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return flights != null && !flights.isEmpty() ? flights.get(0) : null;
	}

	
	
}
