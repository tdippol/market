package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CheckCompratoriDAO;
import com.axiante.mui.dbpromo.persistence.dao.CompratoreDAO;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.ResponsabileEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.support.builders.CanalePromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CfgSetPianificazioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CheckCompratoriEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.CompratoreEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.GruppoPromozioneEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.PromozioneTestataEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.ResponsabileEntityBuilder;
import com.axiante.mui.dbpromo.persistence.support.builders.StatoPromozioneEntityBuilder;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaCheckCompratoreDAOTest extends AbstractDaoTest {

	@Inject
	CheckCompratoriDAO dao;

	@Inject PromozioneTestataDAO testataDao;
	@Inject CompratoreDAO compratoreDao;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
					JpaCheckCompratoreDAOImpl.class, DbPromo.class, JpaPromozioneTestataDaoImpl.class, JpaCompratoreDAOImpl.class)
			.activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	private PromozioneTestataEntity testata;
	private CompratoreEntity compratore1;
	private CompratoreEntity compratore2;
	private CompratoreEntity compratore3;

	@Before
	public void setup() {
		// testata
		List<PromozioneTestataEntity> testate = testataDao.findAll();
		if (testate.isEmpty()) {
			// siamo nella merda
			GruppoPromozioneEntity gruppo = new GruppoPromozioneEntityBuilder(1L).withCodice("G987").build();
			CanalePromozioneEntity canale = new CanalePromozioneEntityBuilder(1L).withCodice(22L).withGruppo(gruppo).build();
			StatoPromozioneEntity statoPromozione = StatoPromozioneEntityBuilder.buildCancellata();
			Set<PromozioneStatoEntity> promozioneStati1 = new HashSet<>();
			PromozioneStatoEntity promozioneStato1 = createPromozioneStatoEntity(1L, Calendar.getInstance().getTime(),
					statoPromozione);
			promozioneStati1.add(promozioneStato1);
			CfgSetPianificazioneEntity cfgSetPianificazione = new CfgSetPianificazioneEntityBuilder(1L)
					.withDataInizio(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime())
					.withDataFine(new GregorianCalendar(2024, Calendar.DECEMBER, 31).getTime())
					.withDescrizione("JUNIT TEST")
					.build();
			testata = new PromozioneTestataEntityBuilder(1L).withCodice("PROMO 01")
					.withDataInizio(new GregorianCalendar(2024, Calendar.OCTOBER, 1).getTime())
					.withDataFine(new GregorianCalendar(2024, Calendar.OCTOBER, 31).getTime())
					.withCanale(canale).withCfgSetPianificazione(cfgSetPianificazione).build();
			testata.setPromozioneStatoEntities(promozioneStati1);
			promozioneStato1.setPromozioneTestataEntity(testata);
			persist(testata);
		} else {
			testata = testate.get(0);
		}
		CheckCompratoriEntity check1 = new CheckCompratoriEntity();
		testata.addCheckCompratori(check1);
		//compratore
		List<CompratoreEntity> compratori = compratoreDao.findAll();
		if (compratori.isEmpty()) {
			ResponsabileEntity responsabile1 = new ResponsabileEntityBuilder(1L).withCodice("RES").build();
			compratore1 = new CompratoreEntityBuilder(1L).withCodice("FOO")
					.withResponsabile(responsabile1).build();
			persist(compratore1);
		} else {
			compratore1 = compratori.get(0);
		}
		compratore1.addCheckCompratori(check1);
		ResponsabileEntity responsabile2 = new ResponsabileEntityBuilder(2L).withCodice("RES").build();
		compratore2 = new CompratoreEntityBuilder(2L).withCodice("BAR").withResponsabile(responsabile2).build();
		compratore3 = new CompratoreEntityBuilder(3L).withCodice("BAZ").withResponsabile(responsabile2).build();
		CheckCompratoriEntity check2 = new CheckCompratoriEntityBuilder(2L).withPromozione(testata)
				.withCompratore(compratore3).build();
		CheckCompratoriEntity check3 = new CheckCompratoriEntityBuilder(3L).withPromozione(testata)
				.withCompratore(compratore3).build();
		persist(check1, check2, check3, compratore2, compratore3);
	}

	@Test
	public void testFindByPromozione() {
		List<CheckCompratoriEntity> result = dao.findByPromozione(testata);
		assertEquals(3, result.size());
	}

	@Test
	public void testFindByCompratore() {
		List<CheckCompratoriEntity> list = dao.findByCompratore(compratore1);
		assertNotNull(list);
		assertEquals(1, list.size());
	}

	@Test
	public void testFindByPromozioneAndCompratore() {
		assertNotNull(dao.findByPromozioneAndCompratore(testata, compratore1));
	}

	@Test
	public void findByPromozioneAndCompratore_whenNoResult_shouldThrowException() {
		ex.expect(NoResultException.class);
		dao.findByPromozioneAndCompratore(testata, compratore2);
	}

	@Test
	public void findByPromozioneAndCompratore_whenManyResult_shouldThrowException() {
		ex.expect(NonUniqueResultException.class);
		dao.findByPromozioneAndCompratore(testata, compratore3);
	}

	private PromozioneStatoEntity createPromozioneStatoEntity(Long id, Date dataFineStato,
			StatoPromozioneEntity statoPromozione) {
		PromozioneStatoEntity promozioneStato = new PromozioneStatoEntity();
		promozioneStato.setId(id);
		promozioneStato.setDataFineStato(dataFineStato);
		promozioneStato.setStatoPromozioneEntity(statoPromozione);
		return promozioneStato;
	}
}