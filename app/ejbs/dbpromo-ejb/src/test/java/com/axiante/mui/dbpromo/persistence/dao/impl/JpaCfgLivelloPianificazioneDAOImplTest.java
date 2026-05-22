package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgLivelloPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
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
public class JpaCfgLivelloPianificazioneDAOImplTest extends AbstractDaoTest {

	@Inject
	private CfgLivelloPianificazioneDAO cfgLivelloPianificazioneDAO;
	
	@Rule
	public WeldInitiator weld = WeldInitiator
			.from(DbPromoTestsEntityManagerProducer.class,EntityManagerFactoryProducer.class, JpaCfgLivelloPianificazioneDAOImpl.class)
			.activate(RequestScoped.class).inject(this).build();
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	

	final CfgLivelloPianificazioneEntity CfgLivelloPianificazioneEntity3 = createCfgLivelloPianificazioneEntity((long) 3, "CfgLivelloPianificazione", "3");
	final CfgLivelloPianificazioneEntity CfgLivelloPianificazioneEntity4 = createCfgLivelloPianificazioneEntity((long) 4, "CfgLivelloPianificazione", "4");


	@Before
	public void setUp() throws Exception {
		
		final CfgLivelloPianificazioneEntity CfgLivelloPianificazioneEntity1 = createCfgLivelloPianificazioneEntity((long) 1, "CfgLivelloPianificazione1", "1");
		final CfgLivelloPianificazioneEntity CfgLivelloPianificazioneEntity2 = createCfgLivelloPianificazioneEntity((long) 2, "CfgLivelloPianificazione2", "2");
		openTransaction();
		cfgLivelloPianificazioneDAO.persist(CfgLivelloPianificazioneEntity1);
		cfgLivelloPianificazioneDAO.persist(CfgLivelloPianificazioneEntity2);
		commitTransaction();
	}


	@Test
	public void shouldFindByDescription() {
		openTransaction();
		cfgLivelloPianificazioneDAO.persist(CfgLivelloPianificazioneEntity3);
		cfgLivelloPianificazioneDAO.persist(CfgLivelloPianificazioneEntity4);
		commitTransaction();
		final CfgLivelloPianificazioneEntity cfgLivelloPianificazione= cfgLivelloPianificazioneDAO.findByDescription("CfgLivelloPianificazione".toUpperCase());
		assertNotNull(cfgLivelloPianificazione);
	}
	
	@Test
	public void shouldNotFindLivelloPianificazioneForGivenDecription() {
		openTransaction();
		cfgLivelloPianificazioneDAO.persist(CfgLivelloPianificazioneEntity3);
		cfgLivelloPianificazioneDAO.persist(CfgLivelloPianificazioneEntity4);
		commitTransaction();
		final CfgLivelloPianificazioneEntity cfgLivelloPianificazione= cfgLivelloPianificazioneDAO.findByDescription("test".toUpperCase());
		assertNull(cfgLivelloPianificazione);
	}

	private CfgLivelloPianificazioneEntity createCfgLivelloPianificazioneEntity(Long id, String descrizione, String codice) {
		CfgLivelloPianificazioneEntity cfgLivelloPianificazioneEntity = new CfgLivelloPianificazioneEntity();
		cfgLivelloPianificazioneEntity.setId(id);
		cfgLivelloPianificazioneEntity.setDescrizione(descrizione);
		cfgLivelloPianificazioneEntity.setCodice(codice);
		return cfgLivelloPianificazioneEntity;
	}
	
	
}
