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

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Retrieved " + result.size() + " items");
		return result;
	}

	public List<Measure> findMeasures(int num) {
		List<Measure> result, truncatedResult = null;

		try {
			

			Query query2 = em.createQuery("SELECT b FROM Measure b ORDER BY b.id ASC");
			result = query2.setFirstResult(0).getResultList();
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

	public Measure createMeasure(Measure _measure) {
		em.persist(_measure);
		return _measure;
	}

}
