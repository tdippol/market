package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.DbPromo;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromozioneMeccanicheDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class JpaPromozioneMeccanicheDAOImplTest extends AbstractDaoTest {

	@Inject
	private PromozioneMeccanicheDAO  dao;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
			EntityManagerFactoryProducer.class, JpaPromozioneMeccanicheDAOImpl.class,JpaMeccanicheDAOImpl.class, DbPromo.class).activate(RequestScoped.class)
			.inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();
	

	@Before
	public void setUp() throws Exception {
		GruppoPromozioneEntity gruppoPromozione = createGruppoPromozioneEntity(1l, "g987", "descrizioneGruppo1");
		CanalePromozioneEntity canalePromozione = createCanalePromozioneEntity(gruppoPromozione, 1l, 22l,
				"descrizioneCanale1");
		StatoPromozioneEntity statoPromozione2 = createStatoPromozioneEntity(2l, "5678",
				PromoStatusEnum.CANCELLATA_00.getKey());
		PromozioneStatoEntity promozioneStato2 = createPromozioneStatoEntity(2l, null, statoPromozione2);
		Set<PromozioneStatoEntity> promozioneStati2 = new HashSet<>();
		promozioneStati2.add(promozioneStato2);
		MeccanicheEntity meccaniche = createMeccanicheEntity("TEST MECCANICHA", "PROVA MECCANICHA");
		PromozioneTestataEntity testata=createPromozioneTestataEntity(2L, "cod-456", canalePromozione, promozioneStati2);
		PromozioneMeccanicheEntity promozioneMeccaniche = createPromozioneMeccanicheEntity(meccaniche,testata);
		openTransaction();
		dao.persist(promozioneMeccaniche);
		commitTransaction();
	}

	@Test
	public void shouldFindById() {
		final PromozioneMeccanicheEntity meccaniche = dao.findById(2L);
		assertNotNull(meccaniche);
	}

	@Test
	public void shouldReadAll() {
		List<PromozioneMeccanicheEntity> meccaniche = dao.findAll();
		assertNotNull(meccaniche);
		assertFalse(meccaniche.isEmpty());
	}

	private PromozioneMeccanicheEntity createPromozioneMeccanicheEntity( MeccanicheEntity meccaniche,PromozioneTestataEntity testata) {
		PromozioneMeccanicheEntity promozioneMeccaniche = new PromozioneMeccanicheEntity();
		promozioneMeccaniche.setId(2L);
		promozioneMeccaniche.setMeccanicheEntity(meccaniche);
		promozioneMeccaniche.setPromozioneTestataEntity(testata);
		return promozioneMeccaniche;
	}
	
	private MeccanicheEntity createMeccanicheEntity(String codiceMeccanica, String descrizione) {
		MeccanicheEntity meccaniche = new MeccanicheEntity();
		meccaniche.setId(2L);
		meccaniche.setCodiceMeccanica(codiceMeccanica);
		meccaniche.setDescrizione(descrizione);
		return meccaniche;
	}
	
	private PromozioneTestataEntity createPromozioneTestataEntity(Long id, String codice,
			CanalePromozioneEntity canalePromozione, Set<PromozioneStatoEntity> promozioneStati) {
		PromozioneTestataEntity testata = new PromozioneTestataEntity();
		testata.setId(id);
		testata.setCodicePromozione(codice);
		testata.setDataInizio(Calendar.getInstance().getTime());
		testata.setDataFine(Calendar.getInstance().getTime());
		testata.setMuiCanalePromozione(canalePromozione);
		testata.setPromozioneStatoEntities(promozioneStati);
		return testata;
	}
	
	private CanalePromozioneEntity createCanalePromozioneEntity(GruppoPromozioneEntity gruppoPromozione, Long id,
			Long codiceCanale, String descrizione) {
		CanalePromozioneEntity canalePromozione = new CanalePromozioneEntity();
		canalePromozione.setId(id);
		canalePromozione.setCodiceCanale(codiceCanale);
		canalePromozione.setGruppoPromozioneEntity(gruppoPromozione);
		canalePromozione.setDescrizione(descrizione);
		return canalePromozione;
	}
	
	private GruppoPromozioneEntity createGruppoPromozioneEntity(Long id, String codiceGruppo, String descrizione) {
		GruppoPromozioneEntity gruppoPromozione = new GruppoPromozioneEntity();
		gruppoPromozione.setId(id);
		gruppoPromozione.setCodiceGruppo(codiceGruppo);
		gruppoPromozione.setDescrizione(descrizione);
		return gruppoPromozione;
	}
	
	private StatoPromozioneEntity createStatoPromozioneEntity(Long id, String descrizione, String codice) {
		StatoPromozioneEntity statoPromozione = new StatoPromozioneEntity();
		statoPromozione.setId(id);
		statoPromozione.setDescrizione(descrizione);
		statoPromozione.setCodiceStato(codice);
		return statoPromozione;
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
