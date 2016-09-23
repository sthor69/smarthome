package com.storassa.javaee.smarthome;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class MeasureEJB {
	
	@PersistenceContext(unitName = "smarthomePU")
	private EntityManager em;

	public List<Measure> findMeasures() {
		Query query = em.createQuery("SELECT b FROM Measure b");
		List result = query.getResultList();
		System.out.println("Retrieved " + result.size() + " items");
		return result;
	}

	public Measure createMeasure (Measure _measure) {
		em.persist(_measure);
		return _measure;
	}
	
}
