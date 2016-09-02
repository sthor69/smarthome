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
	
	@Resource
    ManagedExecutorService managedExecutorService;
	
	@Inject
    Instance<UpdatingTask> myTaskInstance;
 

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
	
	public void executeBgThread() {
		UpdatingTask myTask = myTaskInstance.get();
        this.managedExecutorService.submit(myTask);
	}
}
