package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.MeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaMeccanicheDAOImplTest extends AbstractDaoTest {

	@Inject
	private MeccanicheDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
			EntityManagerFactoryProducer.class, JpaMeccanicheDAOImpl.class, DbPromo.class).activate(RequestScoped.class)
			.inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		MeccanicheEntity meccaniche = createMeccanicheEntity("TEST", "PROVA TEST");
		openTransaction();
		dao.persist(meccaniche);
		commitTransaction();
	}

	@Test
	public void shouldFindById() {
		final MeccanicheEntity meccaniche = dao.findById(2L);
		assertNotNull(meccaniche);
		assertTrue(!meccaniche.getCodiceMeccanica().isEmpty());
		assertEquals(meccaniche.getDescrizione(), "PROVA TEST");
	}

	@Test
	public void shouldReadAll() {
		List<MeccanicheEntity> meccaniche = dao.findAll();
		assertNotNull(meccaniche);
		assertFalse(meccaniche.isEmpty());
	}

	private MeccanicheEntity createMeccanicheEntity(String codiceMeccanica, String descrizione) {
		MeccanicheEntity meccaniche = new MeccanicheEntity();
		meccaniche.setId(2L);
		meccaniche.setCodiceMeccanica(codiceMeccanica);
		meccaniche.setDescrizione(descrizione);
		return meccaniche;
	}
}
