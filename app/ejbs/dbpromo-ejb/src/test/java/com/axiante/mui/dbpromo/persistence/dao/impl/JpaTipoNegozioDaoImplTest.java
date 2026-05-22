package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.TipoNegozioDAO;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;
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
public class JpaTipoNegozioDaoImplTest extends AbstractDaoTest {

	@Inject
	private TipoNegozioDAO tipoNegozioDao;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
			EntityManagerFactoryProducer.class, JpaTipoNegozioDAOImpl.class, DbPromo.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		TipoNegozioEntity tipoNegozio = createNegozioEntity("TEST", "PROVA TEST");
		openTransaction();
		tipoNegozioDao.persist(tipoNegozio);
		commitTransaction();
	}

	@Test
	public void shouldFindById() {
		final TipoNegozioEntity tipoNegozio = tipoNegozioDao.findById(2L);
		assertNotNull(tipoNegozio);
		assertTrue(!tipoNegozio.getCodiceTipoNegozio().isEmpty());
		assertEquals(tipoNegozio.getDescrizione(), "PROVA TEST");
	}

	@Test
	public void shouldReadAll() {
		List<TipoNegozioEntity> tipoNegozio = tipoNegozioDao.findAll();
		assertNotNull(tipoNegozio);
		assertFalse(tipoNegozio.isEmpty());
	}

	private TipoNegozioEntity createNegozioEntity(String codiceTipoNegozio, String descrizione) {
		TipoNegozioEntity tipoNegozio = new TipoNegozioEntity();
		tipoNegozio.setId(2L);
		tipoNegozio.setCodiceTipoNegozio(codiceTipoNegozio);
		tipoNegozio.setDescrizione(descrizione);
		return tipoNegozio;
	}
}
