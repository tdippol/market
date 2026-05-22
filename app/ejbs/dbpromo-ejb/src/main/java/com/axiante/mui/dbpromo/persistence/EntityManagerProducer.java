package com.axiante.mui.dbpromo.persistence;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionScoped;

public class EntityManagerProducer {

	 @PersistenceContext(unitName = "dbPromoPU")
	 EntityManager em;
	 
	 @Produces
	 @TransactionScoped
	 @DbPromo
	 public EntityManager getEntityManager(){
		 return em;
	 }

}
