package com.storassa.javaee.smarthome;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class MeasureEJB {

	@PersistenceContext(unitName = "smarthomePU")
	private EntityManager em;

	public List<Measure> findMeasures() {
		Query query = em.createQuery("SELECT b FROM Measure b");
		return query.getResultList();
	}

	public Measure createMeasure (Measure _measure) {
		em.persist(_measure);
		return _measure;
	}
}
