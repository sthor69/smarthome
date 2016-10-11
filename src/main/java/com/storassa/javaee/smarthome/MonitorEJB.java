package com.storassa.javaee.smarthome;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MonitorEJB {

	@PersistenceContext(unitName = "smarthomeUnit")
	private EntityManager em;

	@PostConstruct
	private void init() {
		System.out.println("MonitorEJB Created");
	}
	
	public List<Monitor> findMonitors() {
		List<Monitor> result = null;

		try {

			Query query = em.createQuery("SELECT m FROM Monitor m");
			result = query.getResultList();
			
			System.out.println("DB access for query: " + query.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Monitor> findMonitors(int num) {
		List<Monitor> result, truncatedResult = null;

		try {
			
			Query query = em.createQuery("SELECT m FROM Monitor m ORDER BY m.id ASC");
			result = query.setFirstResult(0).getResultList();
			
			System.out.println("DB access for query: " + query.toString());
			
			if (null != result)
				
				truncatedResult = new ArrayList<Monitor>(result.subList(result.size() > num ? result.size() - num: 0, result.size()));
			
			else
				
				return null;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Retrieved " + truncatedResult.size() + " items");
		return truncatedResult;

	}
	
	public List<Monitor> findMonitor(String date) {
		List<Monitor> result = null;
		
		String q = "SELECT m FROM Monitor m WHERE m.time LIKE '" + date + "%' ORDER BY m.id ASC";
		
		Query query = em.createQuery(q);
//		query.setParameter("x", date + "%");
		
		System.out.println("Created query: " + q);
		System.out.println("DB access for query: " + query.toString());
		
		result = query.getResultList();
		
		return result;
	}

	public Monitor createMonitor(Monitor _monitor) {
		em.persist(_monitor);
		return _monitor;
	}
}
