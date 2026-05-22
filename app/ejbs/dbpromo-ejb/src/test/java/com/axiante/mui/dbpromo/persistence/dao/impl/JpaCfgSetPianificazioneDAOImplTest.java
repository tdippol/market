package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.*;

import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgSetPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class JpaCfgSetPianificazioneDAOImplTest extends AbstractDaoTest {

	@Inject
	private CfgSetPianificazioneDAO setPianificazioneDAO;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
			EntityManagerFactoryProducer.class, JpaCfgSetPianificazioneDAOImpl.class).activate(RequestScoped.class)
			.inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	
	final MeccanicheEntity meccanica = createMeccanica("meccanica", "M01");
	final CanalePromozioneEntity canalePromozione = createCanalePromozione(150L, 1L,
			10L, "canale",
			createGruppoPromozioneEntity("GR1", "gruppo 1"));
	final CfgAbilitaMeccCanaleEntity cfgAbilitaMeccCanale = createCfgAbilitaMeccCanale(meccanica, canalePromozione);
	

	@Before
	public void setUp() throws Exception {
		
		
		final CfgSetPianificazioneEntity cfg_pianificazione = createCfgSetPianificazione(2L,
				"cfg pianificazione", 0L, cfgAbilitaMeccCanale);

		final CfgSetPianificazioneEntity cfg_pianificazioneLocked = createCfgSetPianificazioneLocked(3L,
				"cfg pianificazioneLocked", 1L, cfgAbilitaMeccCanale);
		
		final CfgSetPianificazioneEntity cfg_pianificazione1 = createCfgSetPianificazione(4L,
				"cfg pianificazione1", 2L, cfgAbilitaMeccCanale);

		final CfgSetPianificazioneEntity cfg_pianificazioneLocked1 = createCfgSetPianificazioneLocked(5L,
				"cfg pianificazioneLocked1", 3L, cfgAbilitaMeccCanale);
		
		final CfgSetPianificazioneEntity cfg_pianificazione2 = createCfgSetPianificazione1(6L,
				"cfg pianificazioneLocked1", 4L, cfgAbilitaMeccCanale);


		openTransaction();
		setPianificazioneDAO.persist(cfg_pianificazione);
		setPianificazioneDAO.persist(cfg_pianificazioneLocked);
		setPianificazioneDAO.persist(cfg_pianificazione1);
		setPianificazioneDAO.persist(cfg_pianificazioneLocked1);
		setPianificazioneDAO.persist(cfg_pianificazione2);
		commitTransaction();
	}


	@Test
	public void shouldFindById() {
		final CfgSetPianificazioneEntity cfgSetPianificazioneEntity = setPianificazioneDAO.findById(2L);
		assertNotNull(cfgSetPianificazioneEntity);
		assertEquals(2, (long) cfgSetPianificazioneEntity.getId());
		assertEquals(cfgSetPianificazioneEntity.getDescrizione(), "cfg pianificazione");
	}

	@Test
	public void shouldReadAll() {
		final List<CfgSetPianificazioneEntity> cfgSetPianificazioneList = setPianificazioneDAO.findAll();
		assertNotNull(cfgSetPianificazioneList);
		assertFalse(cfgSetPianificazioneList.isEmpty());
	}

	@Test
	public void shouldFindByLockedAndDataInizioNotLockedThrowsExceptionWhenMoreThan1DateFound() throws Exception {
		ex.expect(RuntimeException.class);
		final CfgSetPianificazioneEntity byLockedAndDataInizio = setPianificazioneDAO
				.findByLockedAndDataInizio(new GregorianCalendar(2020, Calendar.MAY, 10).getTime());
		assertNotNull(byLockedAndDataInizio);
	}
	
	@Test
	public void shouldFindByLockedAndDataInizioNotLocked() throws Exception {
		final CfgSetPianificazioneEntity byLockedAndDataInizio = setPianificazioneDAO
				.findByLockedAndDataInizio(new GregorianCalendar(2021, Calendar.MAY, 10).getTime());
		assertNotNull(byLockedAndDataInizio);
	}

	@Test
	public void shouldFindByLockedAndDataInizioLockedReturnNullWhenNothingFound() throws Exception {
		final CfgSetPianificazioneEntity byLockedAndDataInizio = setPianificazioneDAO
				.findByLockedAndDataInizio(new GregorianCalendar(2020, Calendar.JANUARY, 10).getTime());
		assertNull(byLockedAndDataInizio);
	}
	
	@Test
	public void shouldFindByLockedAndDataInizioLocked() throws Exception {
		ex.expect(RuntimeException.class);
		final CfgSetPianificazioneEntity byLockedAndDataInizio = setPianificazioneDAO
				.findByLockedAndDataInizio(new GregorianCalendar(2020, Calendar.JUNE, 10).getTime());
		assertNotNull(byLockedAndDataInizio);
	}
	
	@Test
	public void shouldFindAllEnabled() {
		final List<CfgSetPianificazioneEntity> cfgSetPianificazioneList = setPianificazioneDAO.findAllEnabled();
		assertNotNull(cfgSetPianificazioneList);
		assertFalse(cfgSetPianificazioneList.isEmpty());
	}

	@Test
	public void testFindOpenSetReturnsFalseWhenThereIsNoOpenSet() {
		CfgSetPianificazioneEntity openSet=setPianificazioneDAO.findOpenSet();
		assertNull(openSet);
	}
	
	@Test
	public void testFindOpenSetReturnsTrueWhenThereIsOpenSet() {
		final CfgSetPianificazioneEntity cfg_pianificazione3 = createCfgSetPianificazione3(7L,
				"cfg pianificazioneLocked1", 5L, cfgAbilitaMeccCanale);
		openTransaction();
		setPianificazioneDAO.persist(cfg_pianificazione3);
		commitTransaction();
		CfgSetPianificazioneEntity openSet=setPianificazioneDAO.findOpenSet();
		assertNotNull(openSet);
	}
	
	@Test(expected = RuntimeException.class)
	public void testFindOpenSetReturnsFalseWhenThereIsMoreThanOneOpenSet() {
		final CfgSetPianificazioneEntity cfg_pianificazione3 = createCfgSetPianificazione3(7L,
				"cfg pianificazioneLocked1", 5L, cfgAbilitaMeccCanale);
		openTransaction();
		final CfgSetPianificazioneEntity cfg_pianificazione4 = createCfgSetPianificazione3(8L,
				"cfg pianificazioneLocked1", 6L, cfgAbilitaMeccCanale);
		openTransaction();
		setPianificazioneDAO.persist(cfg_pianificazione3);
		setPianificazioneDAO.persist(cfg_pianificazione4);
		commitTransaction();
		CfgSetPianificazioneEntity openSet=setPianificazioneDAO.findOpenSet();
	}
	
	private MeccanicheEntity createMeccanica(String descrizione, String codiceMeccanica) {
		MeccanicheEntity meccanicheEntity = new MeccanicheEntity();
		meccanicheEntity.setId(100L);
		meccanicheEntity.setDescrizione(descrizione);
		meccanicheEntity.setCodiceMeccanica(codiceMeccanica);
		return meccanicheEntity;
	}

	private CanalePromozioneEntity createCanalePromozione(Long codiceCanale, Long codeRangeMin, Long codeRangeMax,
			String descrizione, GruppoPromozioneEntity gruppoPromozioneEntity) {
		CanalePromozioneEntity canalePromozioneEntity = new CanalePromozioneEntity();
		canalePromozioneEntity.setId(2L);
		canalePromozioneEntity.setCodiceCanale(codiceCanale);
		canalePromozioneEntity.setCodeRangeMin(codeRangeMin);
		canalePromozioneEntity.setCodeRangeMax(codeRangeMax);
		canalePromozioneEntity.setDescrizione(descrizione);
		canalePromozioneEntity.setGruppoPromozioneEntity(gruppoPromozioneEntity);
		return canalePromozioneEntity;
	}

	private CfgAbilitaMeccCanaleEntity createCfgAbilitaMeccCanale(MeccanicheEntity meccanicheEntity,
			CanalePromozioneEntity canalePromozioneEntity) {
		CfgAbilitaMeccCanaleEntity cfgAbilitaMeccCanaleEntity = new CfgAbilitaMeccCanaleEntity();
		cfgAbilitaMeccCanaleEntity.setId(15L);
		cfgAbilitaMeccCanaleEntity.setMeccanicheEntity(meccanicheEntity);
		cfgAbilitaMeccCanaleEntity.setCanalePromozioneEntity(canalePromozioneEntity);
		return cfgAbilitaMeccCanaleEntity;
	}

	private CfgSetPianificazioneEntity createCfgSetPianificazione(Long id, String descrizione, Long locked,
			CfgAbilitaMeccCanaleEntity cfgAbilitaMeccCanaleEntity) {
		CfgSetPianificazioneEntity cfgSetPianificazioneEntity = new CfgSetPianificazioneEntity();
		cfgSetPianificazioneEntity.setId(id);
		cfgSetPianificazioneEntity.setDescrizione(descrizione);
		cfgSetPianificazioneEntity.setLocked(locked);
		cfgSetPianificazioneEntity.setDataInizio(new GregorianCalendar(2020, Calendar.MARCH, 1).getTime());
		cfgSetPianificazioneEntity.setDataFine(new GregorianCalendar(2020, Calendar.SEPTEMBER, 30).getTime());
		return cfgSetPianificazioneEntity;
	}
	
	private CfgSetPianificazioneEntity createCfgSetPianificazione1(Long id, String descrizione, Long locked,
			CfgAbilitaMeccCanaleEntity cfgAbilitaMeccCanaleEntity) {
		CfgSetPianificazioneEntity cfgSetPianificazioneEntity = new CfgSetPianificazioneEntity();
		cfgSetPianificazioneEntity.setId(id);
		cfgSetPianificazioneEntity.setDescrizione(descrizione);
		cfgSetPianificazioneEntity.setLocked(locked);
		cfgSetPianificazioneEntity.setDataInizio(new GregorianCalendar(2021, Calendar.MARCH, 1).getTime());
		cfgSetPianificazioneEntity.setDataFine(new GregorianCalendar(2021, Calendar.SEPTEMBER, 30).getTime());
		return cfgSetPianificazioneEntity;
	}

	private CfgSetPianificazioneEntity createCfgSetPianificazioneLocked(Long id, String descrizione, Long locked,
			CfgAbilitaMeccCanaleEntity cfgAbilitaMeccCanaleEntity) {
		CfgSetPianificazioneEntity cfgSetPianificazioneEntity = new CfgSetPianificazioneEntity();
		cfgSetPianificazioneEntity.setId(id);
		cfgSetPianificazioneEntity.setDescrizione(descrizione);
		cfgSetPianificazioneEntity.setLocked(locked);
		cfgSetPianificazioneEntity.setDataInizio(new GregorianCalendar(2020, Calendar.OCTOBER, 1).getTime());
		cfgSetPianificazioneEntity.setDataFine(new GregorianCalendar(2020, Calendar.DECEMBER, 30).getTime());
		return cfgSetPianificazioneEntity;
	}

	private GruppoPromozioneEntity createGruppoPromozioneEntity(String codiceGruppo, String descrizione) {
		GruppoPromozioneEntity gruppoPromozioneEntity = new GruppoPromozioneEntity();
		gruppoPromozioneEntity.setId(2L);
		gruppoPromozioneEntity.setCodiceGruppo(codiceGruppo);
		gruppoPromozioneEntity.setDescrizione(descrizione);
		return gruppoPromozioneEntity;
	}
	
	private CfgSetPianificazioneEntity createCfgSetPianificazione3(Long id, String descrizione, Long locked,
		CfgAbilitaMeccCanaleEntity cfgAbilitaMeccCanaleEntity) {
		CfgSetPianificazioneEntity cfgSetPianificazioneEntity = new CfgSetPianificazioneEntity();
		cfgSetPianificazioneEntity.setId(id);
		cfgSetPianificazioneEntity.setDescrizione(descrizione);
		cfgSetPianificazioneEntity.setLocked(locked);
		return cfgSetPianificazioneEntity;
	}
}
