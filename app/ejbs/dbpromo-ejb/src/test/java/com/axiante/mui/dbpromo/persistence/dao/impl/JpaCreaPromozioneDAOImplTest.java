package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CreaPromozioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
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
public class JpaCreaPromozioneDAOImplTest extends AbstractDaoTest {

	@Inject
	private CreaPromozioneDAO creaPromozioneEntityDAO;

	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class, JpaCreaPromozioneDAOImpl.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		CreaPromozioneEntity promozioneTestataEntity = createPromozione("2020", "messaggio 12345", "slot-12345",
				"user-12345");

		openTransaction();
		creaPromozioneEntityDAO.persist(promozioneTestataEntity);
		commitTransaction();
	}

	@Test
	public void shouldFindAll() {
		List<CreaPromozioneEntity> creaPromozioneEntities = creaPromozioneEntityDAO.findAll();
		assertNotNull(creaPromozioneEntities);
		assertFalse(creaPromozioneEntities.isEmpty());
	}

	@Test
	public void shouldFindWithUserId() throws Exception {
		final List<CreaPromozioneEntity> promozioneList = creaPromozioneEntityDAO.findAllByUserId("user-12345");
		assertNotNull(promozioneList);
		assertFalse(promozioneList.isEmpty());
	}

	@Test
	public void shouldFindWithUserIdAndSlotId() throws Exception {
		final CreaPromozioneEntity promozione = creaPromozioneEntityDAO.findByUserIdAndSlotId("user-12345",
				"slot-12345");
		assertNotNull(promozione);

	}

	@Test
	public void shouldNotFindWithUserId() throws Exception {
		final List<CreaPromozioneEntity> promozioneList = creaPromozioneEntityDAO.findAllByUserId("user-not-found");
		assertNotNull(promozioneList);
		assertTrue(promozioneList.isEmpty());
	}

	@Test(expected = javax.persistence.NoResultException.class)
	public void shouldNotFindWithUserIdAndSlotId() throws Exception {
		final CreaPromozioneEntity promozione = creaPromozioneEntityDAO.findByUserIdAndSlotId("user-not-found",
				"slot-not-found");
		assertNull(promozione);
	}

	private CreaPromozioneEntity createPromozione(String anno, String messaggio, String slotId, String userId) {
		CreaPromozioneEntity creaPromozioneEntity = new CreaPromozioneEntity();
		creaPromozioneEntity.setId(2L);
		creaPromozioneEntity.setAnno(anno);
		creaPromozioneEntity.setMessaggio(messaggio);
		creaPromozioneEntity.setSlotId(slotId);
		creaPromozioneEntity.setUserId(userId);
		return creaPromozioneEntity;
	}

}
