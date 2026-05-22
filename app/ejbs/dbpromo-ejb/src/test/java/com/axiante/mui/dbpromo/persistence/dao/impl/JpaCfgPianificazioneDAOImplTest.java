package com.axiante.mui.dbpromo.persistence.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.persistence.DbPromoTestsEntityManagerProducer;
import com.axiante.mui.dbpromo.persistence.EntityManagerFactoryProducer;
import com.axiante.mui.dbpromo.persistence.dao.AbstractDaoTest;
import com.axiante.mui.dbpromo.persistence.dao.CfgPianificazioneDAO;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.hamcrest.CoreMatchers;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JpaCfgPianificazioneDAOImplTest extends AbstractDaoTest {

	@Inject
	private CfgPianificazioneDAO dao;

	@Rule
	public WeldInitiator weld = WeldInitiator.from(DbPromoTestsEntityManagerProducer.class,
				EntityManagerFactoryProducer.class, JpaCfgPianificazioneDAOImpl.class)
			.activate(RequestScoped.class)
			.inject(this).build();

	@Rule
	public ExpectedException ex = ExpectedException.none();

	// Useful entities
	private PromozioneTestataEntity testata;
	private MeccanicheEntity meccanica;
	private CfgPianificazTipoRigaEntity tipoRigaElemento;
	private CanalePromozioneEntity canale;

	@Mock
	private CanalePromozioneEntity canaleMock;

	@Mock
	private MeccanicheEntity meccanicaMock;

	@Spy
	private CfgSetPianificazioneEntity cfgSetPianificazioneSpy;

	@Before
	public void setUp() throws Exception {
		canale = createCanalePromozione(1L, 10L, 100L,
				"descrizione test", createGruppoPromozioneEntity("GR1", "gruppo 1"));
		testata = createPromozioneTestata(1L, "Semestre I", "2020",
				new GregorianCalendar(2020, Calendar.FEBRUARY, 1).getTime(),
				new GregorianCalendar(2020, Calendar.JULY, 30).getTime(),
				"COD000", canale);
		meccanica = createMeccanica("meccanica", "M01");
		cfgSetPianificazioneSpy = createCfgSetPianificazione(
				11L, "descrizione", 0L);
		final CfgLivelloPianificazioneEntity livello = createCfgLivelloPianificazione(42L, PlanningLevelEnum.SET);
		final CfgConfHeaderEntity cfgCongHeader = createCfgCongHeader(1L, meccanica, livello, cfgSetPianificazioneSpy);
		meccanica.setMuiCfgConfHeaders(new HashSet<>());
		meccanica.addMuiCfgConfHeaderEntity(cfgCongHeader);
		cfgSetPianificazioneSpy.setPromozioneTestataEntities(new HashSet<>());
		cfgSetPianificazioneSpy.addPromozioneTestataEntity(testata);
		cfgSetPianificazioneSpy.setMuiCfgConfHeaders(new HashSet<>());
		cfgSetPianificazioneSpy.addMuiCfgConfHeaderEntity(cfgCongHeader);

		final CfgPianificazioneEntity cfgPianificazione1 = createCfgPianificazione(1L, "cfg pianificazione1");
		final CfgPianificazioneCampiEntity cfgCampo1 = createCfgPianificazioneCampi(1L, "ELEMENTO",
				"NomeElemento", "NOME ELEMENTO");
		final CfgPianificazTipoRigaEntity tipoRiga1 = createCfgPianificazTipoRiga(1L, "S", "SET");
		cfgCampo1.setMuiCfgPianificaziones(new HashSet<>());
		cfgCampo1.addMuiCfgPianificazione(cfgPianificazione1);
		cfgPianificazione1.setMuiCfgPianificazioneCampi(cfgCampo1);
		cfgPianificazione1.setMuiCfgPianificazTipoRiga(tipoRiga1);
		cfgPianificazione1.setMuiCfgConfHeader(cfgCongHeader);
		final CfgPianificazioneEntity cfgPianificazione2 = createCfgPianificazione(2L,
				"cfg pianificazione2", "{Promozione.DataInizio}");
		final CfgPianificazioneCampiEntity cfgCampo2 = createCfgPianificazioneCampi(2L, "DATA_INIZIO",
				"DataInizio", "DATA INIZIO");
		final CfgPianificazTipoRigaEntity tipoRiga2 = createCfgPianificazTipoRiga(2L, "R", "RAGGRUPPAMENTO");
		cfgCampo2.setMuiCfgPianificaziones(new HashSet<>());
		cfgCampo2.addMuiCfgPianificazione(cfgPianificazione2);
		cfgPianificazione2.setMuiCfgPianificazioneCampi(cfgCampo2);
		cfgPianificazione2.setMuiCfgPianificazTipoRiga(tipoRiga2);
		cfgPianificazione2.setMuiCfgConfHeader(cfgCongHeader);
		final CfgPianificazioneEntity cfgPianificazione3 = createCfgPianificazione(3L,
				"cfg pianificazione3", "EUR");
		final CfgPianificazioneCampiEntity cfgCampo3 = createCfgPianificazioneCampi(3L, "TIPO TAGLIO",
				"TipoTaglio", "TIPO TAGLIO");
		tipoRigaElemento = createCfgPianificazTipoRiga(3L, "E", "ELEMENTO");
		cfgCampo3.setMuiCfgPianificaziones(new HashSet<>());
		cfgCampo3.addMuiCfgPianificazione(cfgPianificazione3);
		cfgPianificazione3.setMuiCfgPianificazioneCampi(cfgCampo3);
		cfgPianificazione3.setMuiCfgPianificazTipoRiga(tipoRigaElemento);
		cfgPianificazione3.setMuiCfgConfHeader(cfgCongHeader);
		final CfgPianificazioneEntity cfgPianificazione4 = createCfgPianificazione(4L,
				"cfg pianificazione4", "EUR");
		final CfgPianificazioneCampiEntity cfgCampo4 = createCfgPianificazioneCampi(4L, "FOOBAR",
				"Foobar", "FOOBAR");
		cfgCampo4.setMuiCfgPianificaziones(new HashSet<>());
		cfgCampo4.addMuiCfgPianificazione(cfgPianificazione4);
		cfgPianificazione4.setMuiCfgPianificazioneCampi(cfgCampo4);
		cfgPianificazione4.setMuiCfgPianificazTipoRiga(tipoRigaElemento);
		cfgPianificazione4.setMuiCfgConfHeader(cfgCongHeader);
		final CfgPianificazioneEntity cfgPianificazione5 = createCfgPianificazione(5L,
				"cfg pianificazione4", "EUR");
		cfgPianificazione5.setMuiCfgPianificazioneCampi(cfgCampo4);
		cfgPianificazione5.setMuiCfgPianificazTipoRiga(tipoRigaElemento);
		cfgPianificazione5.setMuiCfgConfHeader(cfgCongHeader);
		cfgCampo4.addMuiCfgPianificazione(cfgPianificazione5);

		openTransaction();
		dao.persist(cfgPianificazione1);
		dao.persist(cfgPianificazione2);
		dao.persist(cfgPianificazione3);
		dao.persist(cfgPianificazione4);
		dao.persist(cfgPianificazione5);
		commitTransaction();
	}

	@Test
	public void shouldFindById() {
		final CfgPianificazioneEntity cfgPianificazioneById = dao.findById(1L);
		assertNotNull(cfgPianificazioneById);
		assertEquals(1L, (long) cfgPianificazioneById.getId());
		assertEquals(cfgPianificazioneById.getDescrizione(), "cfg pianificazione1");
		assertNull(cfgPianificazioneById.getDefValue());
	}

	@Test
	public void shouldReadAll() {
		final List<CfgPianificazioneEntity> cfgPianificazioneList = dao.findAll();
		assertNotNull(cfgPianificazioneList);
		assertFalse(cfgPianificazioneList.isEmpty());
	}

	@Test
	public void shouldFindByCfgSetPianificazione() {
		final List<CfgPianificazioneEntity> allByMuiCfgSetPianificazione = dao
				.findAllByMuiCfgSetPianificazione(testata);
		assertNotNull(allByMuiCfgSetPianificazione);
	}

	@Test
	public void shouldFindTipoElemento() {
		final List<String>  tipiElementi = dao
				.findListaByPromoIdAndMeccanicaIdAndCampoTipoElemento(testata.getId(), meccanica.getId());
		assertNotNull(tipiElementi);
	}

	@Test
	public void shouldFindPianificazioneByCanaleMeccanica() {
		final List<CfgPianificazioneEntity>  cfgPianificazioneList = dao
				.findAllDistinctByCanaleAndMeccanica(canaleMock,
						meccanicaMock);
		assertNotNull(cfgPianificazioneList);
	}

	@Test
	public void shouldFindPianificazioneByCanaleMeccanicaField() {
		final List<CfgPianificazioneEntity>  cfgPianificazioneList = dao
				.findAllByCanaleAndMeccanicaAndField(canaleMock,
						meccanicaMock, "Field");
		assertNotNull(cfgPianificazioneList);
	}

	@Test
	public void shouldFindPianificazioneBySetAndMeccanicaAndCampo() {
		final List<CfgPianificazioneEntity>  cfgPianificazioneList = dao
				.findBySetAndMeccanicaAndCampo(cfgSetPianificazioneSpy,
						meccanicaMock, "Campo");
		assertNotNull(cfgPianificazioneList);
	}

	@Test
	public void shouldFindAllByMuiCfgSetPianificazioneByPromoMeccanica(){
		final List<CfgPianificazioneEntity> allByMuiCfgSetPianificazioneByPromoMeccanica = dao
				.findAllByPromoAndMeccanicaId(testata, meccanica.getId());
		assertNotNull(allByMuiCfgSetPianificazioneByPromoMeccanica);
	}

	@Test
	public void shouldFindDefaultValues() {
		final Map<String, String> defaultValues = dao
				.getDefaultValues(testata.getId(), meccanica.getId(), "TipoRiga");
		assertNotNull(defaultValues);
	}

	@Test
	public void removeMuiCfgConfHeaderEntityThenEntityNotInList() {
		CfgSetPianificazioneEntity entity = new CfgSetPianificazioneEntity();
		entity.setMuiCfgConfHeaders(new HashSet<>());
		int size = entity.getMuiCfgConfHeaders().size();
		CfgConfHeaderEntity e = new CfgConfHeaderEntity();

		e = entity.addMuiCfgConfHeaderEntity(e);
		assertThat(e.getMuiCfgSetPianificazione(), CoreMatchers.equalTo(entity));
		assertThat(entity.getMuiCfgConfHeaders().size(), CoreMatchers.equalTo(size + 1));

		e = entity.removeMuiCfgConfHeaderEntity(e);
		assertNull(e.getMuiCfgSetPianificazione());
		assertThat(entity.getMuiCfgConfHeaders().size(), CoreMatchers.equalTo(size));
	}

	@Test
	public void removePromozioneTestataEntityThenEntityNotInList() {
		CfgSetPianificazioneEntity entity = new CfgSetPianificazioneEntity();
		entity.setPromozioneTestataEntities(new HashSet<>());
		int size = entity.getPromozioneTestataEntities().size();
		PromozioneTestataEntity e = new PromozioneTestataEntity();

		e = entity.addPromozioneTestataEntity(e);
		assertThat(e.getMuiCfgSetPianificazione(), CoreMatchers.equalTo(entity));
		assertThat(entity.getPromozioneTestataEntities().size(), CoreMatchers.equalTo(size + 1));

		e = entity.removePromozioneTestataEntity(e);
		assertNull(e.getMuiCfgSetPianificazione());
		assertThat(entity.getPromozioneTestataEntities().size(), CoreMatchers.equalTo(size));
	}

	@Test
	public void findBySetAndMeccanicaAndCampoAndTipoRiga_givenNullSetPianificazione_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findBySetAndMeccanicaAndCampoAndTipoRiga(null, mock(MeccanicheEntity.class), "foo",
				PianificazioneRowTypeEnum.ELEMENTO);
	}

	@Test
	public void findBySetAndMeccanicaAndCampoAndTipoRiga_givenNullMeccanica_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findBySetAndMeccanicaAndCampoAndTipoRiga(mock(CfgSetPianificazioneEntity.class), null, "foo",
				PianificazioneRowTypeEnum.ELEMENTO);
	}

	@Test
	public void findBySetAndMeccanicaAndCampoAndTipoRiga_givenNullField_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findBySetAndMeccanicaAndCampoAndTipoRiga(mock(CfgSetPianificazioneEntity.class), mock(MeccanicheEntity.class),
				null, PianificazioneRowTypeEnum.ELEMENTO);
	}

	@Test
	public void findBySetAndMeccanicaAndCampoAndTipoRiga_givenNullRowType_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findBySetAndMeccanicaAndCampoAndTipoRiga(mock(CfgSetPianificazioneEntity.class), mock(MeccanicheEntity.class),
				"foo", null);
	}

	@Test
	public void findBySetAndMeccanicaAndCampoAndTipoRiga_givenNotNullParams_shouldReturnEntities() {
		final CfgPianificazioneEntity pianificazione = createCfgPianificazione(42L,"FOO", "EUR");
		final CfgPianificazioneCampiEntity campo = createCfgPianificazioneCampi(42L, "FOO",
				"foo", "FOO");
		final CfgLivelloPianificazioneEntity livello = createCfgLivelloPianificazione(42L, PlanningLevelEnum.ELEMENTO);
		final CfgConfHeaderEntity header = createCfgCongHeader(42L, meccanica, livello, cfgSetPianificazioneSpy);
		campo.setMuiCfgPianificaziones(new HashSet<>());
		campo.addMuiCfgPianificazione(pianificazione);
		pianificazione.setMuiCfgPianificazioneCampi(campo);
		pianificazione.setMuiCfgPianificazTipoRiga(tipoRigaElemento);
		pianificazione.setMuiCfgConfHeader(header);

		openTransaction();
		dao.persist(pianificazione);
		commitTransaction();

		final List<CfgPianificazioneEntity> entities = dao.findBySetAndMeccanicaAndCampoAndTipoRiga(
				cfgSetPianificazioneSpy, meccanica, "FOO", PianificazioneRowTypeEnum.ELEMENTO);
		assertEquals(1, entities.size());
	}

	@Test
	public void findTipiRigaByHeaderAndCampo_givenNullHeaderId_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findTipiRigaByHeaderAndCampo(null, "foo");
	}

	@Test
	public void findTipiRigaByHeaderAndCampo_givenNullField_shouldThrowException() {
		ex.expect(NullPointerException.class);
		dao.findTipiRigaByHeaderAndCampo(1L, null);
	}

	@Test
	public void findTipiRigaByHeaderAndCampo() {
		List<String> tipiRiga = dao.findTipiRigaByHeaderAndCampo(1L, "ELEMENTO");
		assertEquals(1, tipiRiga.size());
		tipiRiga = dao.findTipiRigaByHeaderAndCampo(1L, "UNKNOWN");
		assertTrue(tipiRiga.isEmpty());
	}

	@Test
	public void findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo_whenNoResult_shouldReturnNull() {
		assertNull(dao.findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo(canale, meccanica, tipoRigaElemento,
				"ELEMENTO", testata));
	}

	@Test
	public void findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo_whenNonUniqueResult_shouldReturnNull() {
		assertNull(dao.findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo(canale, meccanica, tipoRigaElemento,
				"FOOBAR", testata));
	}

	@Test
	public void findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo() {
		CfgPianificazioneEntity result = dao.findByCanaleAndMeccanicaAndTipoRigaAndFieldAndPromo(canale, meccanica,
				tipoRigaElemento, "TIPO TAGLIO", testata);
		assertNotNull(result);
		assertEquals(3L, (long) result.getId());
		assertEquals("cfg pianificazione3", result.getDescrizione());
		assertEquals("EUR", result.getDefValue());
	}

	private MeccanicheEntity createMeccanica(String descrizione, String codiceMeccanica) {
		final MeccanicheEntity meccaniche = new MeccanicheEntity();
		meccaniche.setId(100L);
		meccaniche.setDescrizione(descrizione);
		meccaniche.setCodiceMeccanica(codiceMeccanica);
		return meccaniche;
	}

	private CanalePromozioneEntity createCanalePromozione(Long codiceCanale, Long codeRangeMin, Long codeRangeMax,
			String descrizione, GruppoPromozioneEntity gruppoPromozioneEntity) {
		final CanalePromozioneEntity canalePromozione = new CanalePromozioneEntity();
		canalePromozione.setId(2L);
		canalePromozione.setCodiceCanale(codiceCanale);
		canalePromozione.setCodeRangeMin(codeRangeMin);
		canalePromozione.setCodeRangeMax(codeRangeMax);
		canalePromozione.setDescrizione(descrizione);
		canalePromozione.setGruppoPromozioneEntity(gruppoPromozioneEntity);
		return canalePromozione;
	}

	private CfgPianificazioneEntity createCfgPianificazione(Long id, String descrizione) {
		final CfgPianificazioneEntity pianificazione = new CfgPianificazioneEntity();
		pianificazione.setId(id);
		pianificazione.setDescrizione(descrizione);
		return pianificazione;
	}

	private CfgPianificazioneEntity createCfgPianificazione(Long id, String descrizione, String defValue) {
		final CfgPianificazioneEntity pianificazione = createCfgPianificazione(id, descrizione);
		pianificazione.setDefValue(defValue);
		return pianificazione;
	}

	private PromozioneTestataEntity createPromozioneTestata(Long id, String semestre, String year, Date start, Date end,
			String promoCode, CanalePromozioneEntity canalePromozione) {
		final PromozioneTestataEntity promozioneTestata = new PromozioneTestataEntity();
		promozioneTestata.setId(id);
		promozioneTestata.setSemestre(semestre);
		promozioneTestata.setAnno(year);
		promozioneTestata.setDataInizio(start);
		promozioneTestata.setDataFine(end);
		promozioneTestata.setCodicePromozione(promoCode);
		promozioneTestata.setMuiCanalePromozione(canalePromozione);
		return promozioneTestata;
	}

	private GruppoPromozioneEntity createGruppoPromozioneEntity(String codiceGruppo, String descrizione) {
		final GruppoPromozioneEntity gruppoPromozione = new GruppoPromozioneEntity();
		gruppoPromozione.setId(2L);
		gruppoPromozione.setCodiceGruppo(codiceGruppo);
		gruppoPromozione.setDescrizione(descrizione);
		return gruppoPromozione;
	}

	private CfgPianificazioneCampiEntity createCfgPianificazioneCampi(Long id, String campo, String codice, String desc) {
		final CfgPianificazioneCampiEntity pianificazioneCampi = new CfgPianificazioneCampiEntity();
		pianificazioneCampi.setId(id);
		pianificazioneCampi.setCampo(campo);
		pianificazioneCampi.setCodiceCampo(codice);
		pianificazioneCampi.setDescrizione(desc);
		return pianificazioneCampi;
	}

	private CfgSetPianificazioneEntity createCfgSetPianificazione(Long id, String descrizione, Long locked) {
		final CfgSetPianificazioneEntity setPianificazione = new CfgSetPianificazioneEntity();
		setPianificazione.setId(id);
		setPianificazione.setDescrizione(descrizione);
		setPianificazione.setLocked(locked);
		setPianificazione.setDataInizio(new GregorianCalendar(2020, Calendar.MARCH, 1).getTime());
		setPianificazione.setDataFine(new GregorianCalendar(2020, Calendar.SEPTEMBER, 30).getTime());
		return setPianificazione;
	}

	private CfgLivelloPianificazioneEntity createCfgLivelloPianificazione(Long id, PlanningLevelEnum level) {
		final CfgLivelloPianificazioneEntity entity = new CfgLivelloPianificazioneEntity();
		entity.setId(id);
		entity.setCodice(level.getCode());
		return entity;
	}

	private CfgConfHeaderEntity createCfgCongHeader(Long id, MeccanicheEntity meccanica,
													CfgLivelloPianificazioneEntity livello,
													CfgSetPianificazioneEntity setPianificazione) {
		final CfgConfHeaderEntity entity = new CfgConfHeaderEntity();
		entity.setId(id);
		entity.setMeccanicaEntity(meccanica);
		entity.setLivelloPianificazione(livello);
		entity.setMuiCfgSetPianificazione(setPianificazione);
		return entity;
	}

	private CfgPianificazTipoRigaEntity createCfgPianificazTipoRiga(Long id, String code, String desc) {
		final CfgPianificazTipoRigaEntity riga = new CfgPianificazTipoRigaEntity();
		riga.setId(id);
		riga.setCodiceTipo(code);
		riga.setDescrizione(desc);
		return riga;
	}
}
