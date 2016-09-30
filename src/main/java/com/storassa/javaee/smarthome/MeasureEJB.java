package com.storassa.javaee.smarthome;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MeasureEJB {

	@PersistenceContext(unitName = "smarthomeUnit")
	private EntityManager em;

	@PostConstruct
	private void init() {
		System.out.println("MeasureEJB Created");
	}

	public List<Measure> findMeasures() {
		List<Measure> result = null;

		try {

			Query query = em.createQuery("SELECT b FROM Measure b");
			result = query.getResultList();
			
			System.out.println("DB access for query: " + query.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Measure> findMeasures(int num) {
		List<Measure> result, truncatedResult = null;

		try {
			
			Query query = em.createQuery("SELECT b FROM Measure b ORDER BY b.id ASC");
			result = query.setFirstResult(0).getResultList();
			
			System.out.println("DB access for query: " + query.toString());
			
			if (null != result)
				
				truncatedResult = new ArrayList<Measure>(result.subList(result.size() > num ? result.size() - num: 0, result.size()));
			
			else
				
				return null;

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Retrieved " + truncatedResult.size() + " items");
		return truncatedResult;

	}
	
	public List<Measure> findMeasure(String date) {
		List<Measure> result = null;
		
		String q = "SELECT b FROM Measure b WHERE b.time LIKE '" + date + "%' ORDER BY b.id ASC";
		
		Query query = em.createQuery(q);
//		query.setParameter("x", date + "%");
		
		System.out.println("Created query: " + q);
		System.out.println("DB access for query: " + query.toString());
		
		result = query.getResultList();
		
		return result;
	}

	public Measure createMeasure(Measure _measure) {
		em.persist(_measure);
		return _measure;
	}

}
