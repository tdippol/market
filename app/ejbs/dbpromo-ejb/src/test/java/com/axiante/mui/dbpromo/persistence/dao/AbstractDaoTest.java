package com.axiante.mui.dbpromo.persistence.dao;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.entities.DbPromoReadOnlyEntity;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.Getter;
import org.junit.BeforeClass;

public abstract class AbstractDaoTest {
	static final String DATABASE_CREATE_URL = "jdbc:derby:memory:dbpromo;create=true";
	static final String DATABASE_DROP_URL = "jdbc:derby::memory:dbpromo;drop=true";

	@Inject
	@DbPromo
	@Getter
	protected EntityManager em;

	@BeforeClass
	public static void initTest() {
		System.setProperty(DbPromoReadOnlyEntity.OVERRIDE_READ_ONLY, "true");
	}

	protected void commitTransaction() {
		if (em.getTransaction().isActive()) {
			em.getTransaction().commit();
		}
	}

	protected void openTransaction() {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
		}
	}

	protected void persist(Object... entities) {
		if (!em.getTransaction().isActive()) {
			em.getTransaction().begin();
			for (Object entity : entities) {
				entity = em.merge(entity);
				em.persist(entity);
			}
			em.getTransaction().commit();
		}
	}
}
