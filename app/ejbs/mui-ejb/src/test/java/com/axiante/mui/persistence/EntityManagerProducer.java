package com.axiante.mui.persistence;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerProducer {

	@Inject
	transient EntityManagerFactory emf;

	@Produces
	@RequestScoped
	public EntityManager create() {
		return emf.createEntityManager();
	}

	public void destroy(@Disposes EntityManager em) {
		em.close();
	}
}
