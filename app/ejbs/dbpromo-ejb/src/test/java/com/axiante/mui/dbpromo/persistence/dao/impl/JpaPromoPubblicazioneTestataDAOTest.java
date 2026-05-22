package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.PromoPubblicazioneTestataDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JpaPromoPubblicazioneTestataDAOTest extends AbstractDaoTest {

	@Inject
	private PromoPubblicazioneTestataDAO promoPubblicazioneTestataDAO;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class, EntityManagerFactoryProducer.class,
			JpaPromoPubblicazioneTestataDAOImpl.class).activate(RequestScoped.class).inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	private final static Long PROMOZIONETESTATAENTITY1_ID = 4L;
	private final static Long PROMOZIONETESTATAENTITY2_ID = 5L;

	private static PromoPubblicazioneTestataEntity promoPubblicazioneTestataEntity1;
	private static PromoPubblicazioneTestataEntity promoPubblicazioneTestataEntity2;

	static final AtomicBoolean initialized = new AtomicBoolean(false);

	@Before
	public void setUp() throws Exception {
		if (initialized.get() == false) {
			GruppoPromozioneEntity gruppoPromozioneEntity = createGruppoPromozioneEntity();
			CanalePromozioneEntity canalePromozioneEntity = createCanalePromozioneEntity(gruppoPromozioneEntity);
			StatoPromozioneEntity statoPromozioneEntity1 = createStatoPromozioneEntity(101l, "0234",
					PromoStatusEnum.CANCELLATA_00.getKey());
			StatoPromozioneEntity statoPromozioneEntity2 = createStatoPromozioneEntity(102l, "0678",
					PromoStatusEnum.CANCELLATA_00.getKey());
			PromozioneStatoEntity promozioneStatoEntity1 = createPromozioneStatoEntity(101l,
					Calendar.getInstance().getTime(), statoPromozioneEntity1);
			PromozioneStatoEntity promozioneStatoEntity2 = createPromozioneStatoEntity(102l, null,
					statoPromozioneEntity2);
			Set<PromozioneStatoEntity> promozioneStatoEntities1 = new HashSet<>();
			promozioneStatoEntities1.add(promozioneStatoEntity1);
			Set<PromozioneStatoEntity> promozioneStatoEntities2 = new HashSet<>();
			promozioneStatoEntities2.add(promozioneStatoEntity2);
			PromozioneTestataEntity promozioneTestataEntity1 = createPromozioneTestataEntity(
					PROMOZIONETESTATAENTITY1_ID, "cod-921", canalePromozioneEntity, promozioneStatoEntities1);
			PromozioneTestataEntity promozioneTestataEntity2 = createPromozioneTestataEntity(
					PROMOZIONETESTATAENTITY2_ID, "cod-957", canalePromozioneEntity, promozioneStatoEntities2);

			promoPubblicazioneTestataEntity1 = createPromoPubblicazioneTestataEntity(801l, promozioneTestataEntity1);
			promoPubblicazioneTestataEntity2 = createPromoPubblicazioneTestataEntity(802l, promozioneTestataEntity2);

			openTransaction();
			promoPubblicazioneTestataDAO.persist(promoPubblicazioneTestataEntity1);
			promoPubblicazioneTestataDAO.persist(promoPubblicazioneTestataEntity2);
			commitTransaction();
		} else {
			System.out.println("skipping");
		}
	}

	private StatoPromozioneEntity createStatoPromozioneEntity(Long id, String descrizione, String codice) {
		StatoPromozioneEntity statoPromozioneEntity = new StatoPromozioneEntity();
		statoPromozioneEntity.setId(id);
		statoPromozioneEntity.setDescrizione(descrizione);
		statoPromozioneEntity.setCodiceStato(codice);
		return statoPromozioneEntity;
	}

	private PromozioneStatoEntity createPromozioneStatoEntity(Long id, Date dataFineStato,
			StatoPromozioneEntity statoPromozioneEntity) {
		PromozioneStatoEntity promozioneStatoEntity = new PromozioneStatoEntity();
		promozioneStatoEntity.setId(id);
		promozioneStatoEntity.setDataFineStato(dataFineStato);
		promozioneStatoEntity.setStatoPromozioneEntity(statoPromozioneEntity);
		return promozioneStatoEntity;
	}

	private GruppoPromozioneEntity createGruppoPromozioneEntity() {
		GruppoPromozioneEntity gruppoPromozioneEntity = new GruppoPromozioneEntity();
		gruppoPromozioneEntity.setId(881l);
		gruppoPromozioneEntity.setCodiceGruppo("g987");
		return gruppoPromozioneEntity;
	}

	private CanalePromozioneEntity createCanalePromozioneEntity(GruppoPromozioneEntity gruppoPromozioneEntity) {
		CanalePromozioneEntity canalePromozioneEntity = new CanalePromozioneEntity();
		canalePromozioneEntity.setId(881l);
		canalePromozioneEntity.setCodiceCanale(1l);
		canalePromozioneEntity.setGruppoPromozioneEntity(gruppoPromozioneEntity);
		return canalePromozioneEntity;
	}

	private PromozioneTestataEntity createPromozioneTestataEntity(Long id, String codice,
			CanalePromozioneEntity canalePromozioneEntity, Set<PromozioneStatoEntity> promozioneStatoEntities) {
		PromozioneTestataEntity promozioneTestataEntity = new PromozioneTestataEntity();
		promozioneTestataEntity.setId(id);
		promozioneTestataEntity.setCodicePromozione(codice);
		promozioneTestataEntity.setDataInizio(Calendar.getInstance().getTime());
		promozioneTestataEntity.setDataFine(Calendar.getInstance().getTime());
		promozioneTestataEntity.setMuiCanalePromozione(canalePromozioneEntity);
		promozioneTestataEntity.setPromozioneStatoEntities(promozioneStatoEntities);
		return promozioneTestataEntity;
	}

	private PromoPubblicazioneTestataEntity createPromoPubblicazioneTestataEntity(long id,
			PromozioneTestataEntity promozioneTestataEntity) {
		PromoPubblicazioneTestataEntity promoPubblicazioneTestataEntity = new PromoPubblicazioneTestataEntity();
		promoPubblicazioneTestataEntity.setId(id);
		promoPubblicazioneTestataEntity.setDescrizione("descrizione");
		promoPubblicazioneTestataEntity.setPromozioneTestataEntity(promozioneTestataEntity);
		return promoPubblicazioneTestataEntity;
	}

	@Test
	public void testFindByIdReturnsEmptyWhenNullArg() {
		final List<PromoPubblicazioneTestataEntity> promoPubblicazioneTestataEntities = promoPubblicazioneTestataDAO
				.findByPromozioneID(null);
		assertNotNull(promoPubblicazioneTestataEntities);
		assertTrue(promoPubblicazioneTestataEntities.isEmpty());
	}

	@Test
	public void testFindByIdReturnsEmptyWhenNotFound() {
		final List<PromoPubblicazioneTestataEntity> promoPubblicazioneTestataEntities = promoPubblicazioneTestataDAO
				.findByPromozioneID(999l);
		assertNotNull(promoPubblicazioneTestataEntities);
		assertTrue(promoPubblicazioneTestataEntities.isEmpty());

	}

	@Test
	public void testFindByIdReturnsOneElement() {
		final List<PromoPubblicazioneTestataEntity> promoPubblicazioneTestataEntities = promoPubblicazioneTestataDAO
				.findByPromozioneID(PROMOZIONETESTATAENTITY1_ID);
		assertNotNull(promoPubblicazioneTestataEntities);
		assertFalse(promoPubblicazioneTestataEntities.isEmpty());
		assertEquals(promoPubblicazioneTestataEntities.size(), 1);
		assertEquals(promoPubblicazioneTestataEntity1.getId(), promoPubblicazioneTestataEntities.get(0).getId());
		assertEquals(promoPubblicazioneTestataEntity1.getDescrizione(),
				promoPubblicazioneTestataEntities.get(0).getDescrizione());
		assertNotNull(promoPubblicazioneTestataEntities.get(0).getPromozioneTestataEntity());

	}

	@Test
	public void shouldFindAllByPromozioneId() {
		List<PromoPubblicazioneTestataEntity> promoPubblicazioneTestataEntities = promoPubblicazioneTestataDAO
				.findByPromozioneID(PROMOZIONETESTATAENTITY1_ID);
		PromozioneTestataEntity promozioneTestataEntity = promoPubblicazioneTestataEntities.get(0)
				.getPromozioneTestataEntity();
		assertEquals(promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getId(),
				promozioneTestataEntity.getId());
		assertEquals(promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getCodicePromozione(),
				promozioneTestataEntity.getCodicePromozione());
		assertEquals(promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getDataInizio(),
				promozioneTestataEntity.getDataInizio());
		assertEquals(promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getDataFine(),
				promozioneTestataEntity.getDataFine());
		assertNotNull(promozioneTestataEntity.getMuiCanalePromozione());
		assertEquals(promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getMuiCanalePromozione().getId(),
				promozioneTestataEntity.getMuiCanalePromozione().getId());
		assertEquals(promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getMuiCanalePromozione()
				.getCodiceCanale(), promozioneTestataEntity.getMuiCanalePromozione().getCodiceCanale());
		assertNotNull(promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity());
		assertEquals(
				promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getMuiCanalePromozione()
						.getGruppoPromozioneEntity().getId(),
				promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getId());
		assertEquals(
				promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getMuiCanalePromozione()
						.getGruppoPromozioneEntity().getCodiceGruppo(),
				promozioneTestataEntity.getMuiCanalePromozione().getGruppoPromozioneEntity().getCodiceGruppo());
		assertNotNull(promozioneTestataEntity.getPromozioneStatoEntities());
		assertFalse(promozioneTestataEntity.getPromozioneStatoEntities().isEmpty());
		assertEquals(promozioneTestataEntity.getPromozioneStatoEntities().size(), 1);
		assertEquals(
				promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator()
						.next().getId(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getId());
		assertNotNull(promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertEquals(
				promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator()
						.next().getDataFineStato(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getDataFineStato());
		assertNotNull(
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity());
		assertEquals(
				promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator()
						.next().getStatoPromozioneEntity().getId(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity()
						.getId());
		assertEquals(
				promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator()
						.next().getStatoPromozioneEntity().getCodiceStato(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity()
						.getCodiceStato());
		assertEquals(
				promoPubblicazioneTestataEntity1.getPromozioneTestataEntity().getPromozioneStatoEntities().iterator()
						.next().getStatoPromozioneEntity().getDescrizione(),
				promozioneTestataEntity.getPromozioneStatoEntities().iterator().next().getStatoPromozioneEntity()
						.getDescrizione());

	}

	@Test
	public void runFailedUpdateEsitoPubblicazioni() {
		Boolean result = promoPubblicazioneTestataDAO.runUpdateEsitoPubblicazioni();
		assertFalse(result);
	}
}
