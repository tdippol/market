package com.axiante.mui.webapp.webservice.util;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.entities.CheckTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTipoTerminaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;
import com.axiante.mui.webapp.business.OwnershipService;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SchedaPromoUtilTest {

	@Mock
	private Instance<OwnershipService> ownershipServiceInstance;

	@Mock
	private OwnershipService ownershipService;

	@InjectMocks
	private SchedaPromoUtil schedaPromoUtil;

	@Before
	public void setUp() {
		when(ownershipServiceInstance.get()).thenReturn(ownershipService);
	}

	@Test
	public void testCreateStatiRowData() {
		Set<PromozioneStatoEntity> setPromozioneStatoEntities = new HashSet<>();
		PromozioneStatoEntity promozioneStato = new PromozioneStatoEntity();
		PromozioneStatoEntity promozioneStato2 = new PromozioneStatoEntity();
		StatoPromozioneEntity statoPromozione = new StatoPromozioneEntity();
		statoPromozione.setCodiceStato("1");
		statoPromozione.setLabel("Test");
		promozioneStato.setStatoPromozioneEntity(statoPromozione);
		promozioneStato.setDataInizioStato(new Date());
		promozioneStato.setDataFineStato(new Date());
		promozioneStato2.setDataInizioStato(new Date());
		setPromozioneStatoEntities.add(promozioneStato);
		setPromozioneStatoEntities.add(promozioneStato2);
		assertNotNull(schedaPromoUtil.createStatiRowData(setPromozioneStatoEntities));
	}

	@Test
	public void TestCreateMeccanicheRowData() {
		PromozioneTestataEntity testata = new PromozioneTestataEntity();
		Set<PromozioneStatoEntity> setPromozioneStatoEntities = new HashSet<>();
		Set<PromozioneMeccanicheEntity> setPromozioneMeccanicheEntities = new HashSet<>();
		Set<PromozionePianificazioneEntity> setPromozionePianificazioneEntity = new HashSet<>();
		PromozionePianificazioneEntity promozionePianificazioneEntity=new PromozionePianificazioneEntity();
		PromozionePianificazioneEntity promozionePianificazioneEntity2=new PromozionePianificazioneEntity();
		PromozioneStatoEntity promozioneStato = new PromozioneStatoEntity();
		PromozioneStatoEntity promozioneStato2 = new PromozioneStatoEntity();
		StatoPromozioneEntity statoPromozione = new StatoPromozioneEntity();
		StatoPromozioneEntity statoPromozione2 = new StatoPromozioneEntity();
		PromozioneMeccanicheEntity promozioneMeccaniche=new PromozioneMeccanicheEntity();
		PromozioneMeccanicheEntity promozioneMeccaniche2=new PromozioneMeccanicheEntity();
		MeccanicheEntity meccanicheEntity = new MeccanicheEntity();
		meccanicheEntity.setId((long) 1);
		meccanicheEntity.setCodiceMeccanica("Auto");
		promozionePianificazioneEntity.setMeccanicaEntity(meccanicheEntity);
		MeccanicheEntity meccanicheEntity2 = new MeccanicheEntity();
		meccanicheEntity2.setId((long) 2);
		meccanicheEntity2.setCodiceMeccanica("Moto");
		promozionePianificazioneEntity2.setMeccanicaEntity(meccanicheEntity2);
		setPromozionePianificazioneEntity.add(promozionePianificazioneEntity);
		setPromozionePianificazioneEntity.add(promozionePianificazioneEntity2);
		promozioneMeccaniche.setMeccanicheEntity(meccanicheEntity);
		promozioneMeccaniche.setPromozioneTestataEntity(testata);
		promozioneMeccaniche.setId((long) 1);
		promozioneMeccaniche2.setMeccanicheEntity(meccanicheEntity2);
		promozioneMeccaniche2.setPromozioneTestataEntity(testata);
		promozioneMeccaniche2.setId((long) 2);
		setPromozioneMeccanicheEntities.add(promozioneMeccaniche);
		setPromozioneMeccanicheEntities.add(promozioneMeccaniche2);
		statoPromozione.setCodiceStato("1");
		statoPromozione.setLabel("Test");
		promozioneStato.setStatoPromozioneEntity(statoPromozione);
		promozioneStato2.setStatoPromozioneEntity(statoPromozione2);
		promozioneStato.setDataInizioStato(new Date());
		promozioneStato.setDataFineStato(new Date());
		promozioneStato2.setDataInizioStato(new Date());
		setPromozioneStatoEntities.add(promozioneStato);
		setPromozioneStatoEntities.add(promozioneStato2);
		testata.setPromozioneStatoEntities(setPromozioneStatoEntities);
		testata.setPromozioneMeccanicheEntities(setPromozioneMeccanicheEntities);
		testata.setPromozionePianificazioneEntities(setPromozionePianificazioneEntity);
		assertNotNull(schedaPromoUtil.createMeccanicheRowData(testata, Collections.singletonList("FOO"), false));
	}

	@Test
	public void testCreatePubblicazioniRowData() {
		List<PromoPubblicazioneTestataEntity> promoPubblicazioneTestataEntities = new ArrayList<>();
		PromoPubblicazioneTestataEntity promoPubblicazioneTestataEntity = new PromoPubblicazioneTestataEntity();
		promoPubblicazioneTestataEntity.setDataPubblicazione(new Date());
		promoPubblicazioneTestataEntity.setFlagEsito(BigDecimal.valueOf(400));
		promoPubblicazioneTestataEntity.setExportId("Export1");
		PromoPubblicazioneTestataEntity promoPubblicazioneTestataEntity2 = new PromoPubblicazioneTestataEntity();
		promoPubblicazioneTestataEntity2.setDataPubblicazione(new Date());
		promoPubblicazioneTestataEntity2.setFlagEsito(BigDecimal.valueOf(401));
		promoPubblicazioneTestataEntity2.setExportId("Export2");
		StatoPromozioneEntity statoPromozione = new StatoPromozioneEntity();
		statoPromozione.setCodiceStato("1");
		statoPromozione.setLabel("Test");
		StatoPromozioneEntity statoPromozione2 = new StatoPromozioneEntity();
		statoPromozione2.setCodiceStato("2");
		statoPromozione2.setLabel("Test2");
		promoPubblicazioneTestataEntity.setStatoPromozioneEntity(statoPromozione);
		promoPubblicazioneTestataEntity2.setStatoPromozioneEntity(statoPromozione2);
		promoPubblicazioneTestataEntities.add(promoPubblicazioneTestataEntity);
		promoPubblicazioneTestataEntities.add(promoPubblicazioneTestataEntity2);
		assertNotNull(schedaPromoUtil.createPubblicazioniRowData(promoPubblicazioneTestataEntities));
	}

	@Test(expected = NullPointerException.class)
	public void createTipoCassaRowData_givenNullTestata_shouldThrowException() {
		schedaPromoUtil.createTipoCassaRowData(null, null, false);
	}

	@Test
	public void createTipoCassaRowData_givenTestata_shouldReturnJsonString_withTipoCassa() {
		// Arrange
		final List<String> groups = Collections.singletonList("FOO");
		final PromozioneTestataEntity testata = mockTestata();
		final PromozioneStatoEntity promoStatoEntity = new PromozioneStatoEntity();
		promoStatoEntity.setDataInizioStato(new GregorianCalendar(2021, Calendar.MARCH, 1).getTime());
		promoStatoEntity.setStatoPromozioneEntity(withStatoPromo(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA));
		testata.addPromozioneStatoEntity(promoStatoEntity);
		when(ownershipService.hasOwnership(testata, groups)).thenReturn(true);
		// Act
		final String json = schedaPromoUtil.createTipoCassaRowData(testata, groups, false);
		// Assert
		assertNotNull(json);
		final DocumentContext cxt = JsonPath.parse(json);
		assertEquals(5, (int) cxt.read("$.rowData.length()"));
		assertEquals("1", cxt.read("$.rowData[0].tipoTerminale.value"));
		assertEquals("Cassa 1", cxt.read("$.rowData[0].descrizione.value"));
		assertEquals("A", cxt.read("$.rowData[0].tipo.value"));
		assertEquals("true", cxt.read("$.rowData[0].removeEnabled.value"));
		assertEquals("2", cxt.read("$.rowData[1].tipoTerminale.value"));
		assertEquals("Cassa 2", cxt.read("$.rowData[1].descrizione.value"));
		assertEquals("B", cxt.read("$.rowData[1].tipo.value"));
		assertEquals("true", cxt.read("$.rowData[1].removeEnabled.value"));
		assertEquals("3", cxt.read("$.rowData[2].tipoTerminale.value"));
		assertEquals("Cassa 3", cxt.read("$.rowData[2].descrizione.value"));
		assertEquals("C", cxt.read("$.rowData[2].tipo.value"));
		assertEquals("true", cxt.read("$.rowData[2].removeEnabled.value"));
		assertEquals("4", cxt.read("$.rowData[3].tipoTerminale.value"));
		assertEquals("Cassa 4", cxt.read("$.rowData[3].descrizione.value"));
		assertEquals("D", cxt.read("$.rowData[3].tipo.value"));
		assertEquals("true", cxt.read("$.rowData[3].removeEnabled.value"));
		assertEquals("5", cxt.read("$.rowData[4].tipoTerminale.value"));
		assertEquals("Cassa 5", cxt.read("$.rowData[4].descrizione.value"));
		assertEquals("E", cxt.read("$.rowData[4].tipo.value"));
		assertEquals("true", cxt.read("$.rowData[4].removeEnabled.value"));
	}

	@Test
	public void createTipoCassaRowData_givenTestata_shouldReturnJsonString_withTipoCassaNotDeletable() {
		// Arrange
		final PromozioneTestataEntity testata = mockTestata();
		final PromozioneStatoEntity promoStatoEntity = new PromozioneStatoEntity();
		promoStatoEntity.setDataInizioStato(new GregorianCalendar(2021, Calendar.MARCH, 1).getTime());
		promoStatoEntity.setStatoPromozioneEntity(withStatoPromo(PromoStatusEnum.PIANIFICAZIONE_FINALIZZATA));
		testata.addPromozioneStatoEntity(promoStatoEntity);
		// Act
		final String json = schedaPromoUtil.createTipoCassaRowData(testata, null,false);
		// Assert
		assertNotNull(json);
		final DocumentContext cxt = JsonPath.parse(json);
		assertEquals(5, (int) cxt.read("$.rowData.length()"));
		assertEquals("1", cxt.read("$.rowData[0].tipoTerminale.value"));
		assertEquals("Cassa 1", cxt.read("$.rowData[0].descrizione.value"));
		assertEquals("A", cxt.read("$.rowData[0].tipo.value"));
		assertEquals("false", cxt.read("$.rowData[0].removeEnabled.value"));
		assertEquals("2", cxt.read("$.rowData[1].tipoTerminale.value"));
		assertEquals("Cassa 2", cxt.read("$.rowData[1].descrizione.value"));
		assertEquals("B", cxt.read("$.rowData[1].tipo.value"));
		assertEquals("false", cxt.read("$.rowData[1].removeEnabled.value"));
		assertEquals("3", cxt.read("$.rowData[2].tipoTerminale.value"));
		assertEquals("Cassa 3", cxt.read("$.rowData[2].descrizione.value"));
		assertEquals("C", cxt.read("$.rowData[2].tipo.value"));
		assertEquals("false", cxt.read("$.rowData[2].removeEnabled.value"));
		assertEquals("4", cxt.read("$.rowData[3].tipoTerminale.value"));
		assertEquals("Cassa 4", cxt.read("$.rowData[3].descrizione.value"));
		assertEquals("D", cxt.read("$.rowData[3].tipo.value"));
		assertEquals("false", cxt.read("$.rowData[3].removeEnabled.value"));
		assertEquals("5", cxt.read("$.rowData[4].tipoTerminale.value"));
		assertEquals("Cassa 5", cxt.read("$.rowData[4].descrizione.value"));
		assertEquals("E", cxt.read("$.rowData[4].tipo.value"));
		assertEquals("false", cxt.read("$.rowData[4].removeEnabled.value"));
	}

	@Test(expected = NullPointerException.class)
	public void createControlliRowData_givenNullTestata_shouldThrowException() {
		schedaPromoUtil.createControlliRowData(null);
	}

	@Test
	public void createControlliRowData_givenTestataWithoutChecks_shouldReturnEmptyRowData() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getChecks()).thenReturn(Collections.emptySet());
		final String rowData = schedaPromoUtil.createControlliRowData(testata);
		assertNotNull(rowData);
		assertThat(rowData, isJson(withJsonPath("$.rowData", hasSize(0))));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void createControlliRowData_givenTestataWithChecks_shouldReturnRowDataWithChecks() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final Set<CheckTestataEntity> checks = new HashSet<>();
		final CheckTestataEntity check1 = mockCheck(1L, "Negozi Associati",
				"Nessun Negozio associato alla Promozione", null, "Errore");
		final CheckTestataEntity check2 = mockCheck(2L, "Offerta",
				"Alcuni Raggruppamenti del SET foo non hanno offerta", "[S42] LUKE SKYWALKER", "Warning");
		checks.add(check1);
		checks.add(check2);
		when(testata.getChecks()).thenReturn(checks);
		final String rowData = schedaPromoUtil.createControlliRowData(testata);
		assertNotNull(rowData);
		assertThat(rowData, isJson(allOf(
				withJsonPath("$.rowData", hasSize(2)),
				withJsonPath("$.rowData[0].tipoControllo.value", equalTo("Offerta")),
				withJsonPath("$.rowData[0].descrizione.value", equalTo("Alcuni Raggruppamenti del SET foo non hanno offerta")),
				withJsonPath("$.rowData[0].compratore.value", equalTo("[S42] LUKE SKYWALKER")),
				withJsonPath("$.rowData[0].severita.value", equalTo("Warning")),
				withJsonPath("$.rowData[1].tipoControllo.value", equalTo("Negozi Associati")),
				withJsonPath("$.rowData[1].tipoControllo.mandatory", equalTo(true)),
				withJsonPath("$.rowData[1].descrizione.value", equalTo("Nessun Negozio associato alla Promozione")),
				withJsonPath("$.rowData[1].descrizione.mandatory", equalTo(true)),
				withJsonPath("$.rowData[1].compratore.mandatory", equalTo(true)),
				withJsonPath("$.rowData[1].severita.value", equalTo("Errore")),
				withJsonPath("$.rowData[1].severita.mandatory", equalTo(true))
		)));
	}

	@Test(expected = NullPointerException.class)
	public void createOwnerRowData_givenNullTestata_shouldThrowException() {
		schedaPromoUtil.createOwnerRowData(null);
	}

	@Test
	public void createOwnerRowData_givenTestataWithoutOwners_shouldReturnEmptyRowData() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getOwners()).thenReturn(Collections.emptySet());
		final String rowData = schedaPromoUtil.createOwnerRowData(testata);
		assertNotNull(rowData);
		assertThat(rowData, isJson(withJsonPath("$.rowData", hasSize(0))));
	}

	@Test
	public void createOwnerRowData_givenTestataWithOwners_shouldReturnRowDataWithChecks() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final Set<CompratoreEntity> buyers = new HashSet<>();
		final CompratoreEntity buyer1 = mockBuyer("S42", "Obi Wan Kenobi");
		final CompratoreEntity buyer2 = mockBuyer("S01", "Yoda");
		final CompratoreEntity buyer3 = mockBuyer("S07", "Mace Windu");
		buyers.add(buyer1);
		buyers.add(buyer2);
		buyers.add(buyer3);
		when(testata.getOwners()).thenReturn(buyers);
		final String rowData = schedaPromoUtil.createOwnerRowData(testata);
		assertNotNull(rowData);
		assertThat(rowData, isJson(allOf(
				withJsonPath("$.rowData", hasSize(3)),
				withJsonPath("$.rowData[0].owner.value", equalTo("S01 - Yoda")),
				withJsonPath("$.rowData[1].owner.value", equalTo("S07 - Mace Windu")),
				withJsonPath("$.rowData[2].owner.value", equalTo("S42 - Obi Wan Kenobi"))
		)));
	}

	private CompratoreEntity mockBuyer(String code, String desc) {
		final CompratoreEntity buyer = mock(CompratoreEntity.class);
		when(buyer.getCodiceCompratore()).thenReturn(code);
		when(buyer.getDescrizione()).thenReturn(desc);
		return buyer;
	}

	private CheckTestataEntity mockCheck(Long id, String tipoControllo, String descrizione, String compratore, String severity) {
		final CheckTestataEntity check = mock(CheckTestataEntity.class);
		when(check.getId()).thenReturn(id);
		when(check.getTipoControllo()).thenReturn(tipoControllo);
		when(check.getDescrizioneControllo()).thenReturn(descrizione);
		when(check.getDescrizioneCompratore()).thenReturn(compratore);
		when(check.getSeverita()).thenReturn(severity);
		return check;
	}

	private PromozioneTestataEntity mockTestata() {
		final PromozioneTestataEntity entity = new PromozioneTestataEntity();
		entity.setPromozioneTipiTerminaleCassa(new HashSet<>());
		entity.setPromozioneStatoEntities(new HashSet<>());
		for (int i = 1; i <= 5; i++) {
			entity.addPromozioneTipoTerminale(mockPromozioneTerminaleCassa(i));
			entity.addPromozioneStatoEntity(mockPromozioneStato(i));
		}
		return entity;
	}

	private PromozioneTipoTerminaleEntity mockPromozioneTerminaleCassa(int i) {
		final PromozioneTipoTerminaleEntity entity = new PromozioneTipoTerminaleEntity();
		entity.setId((long) i);
		entity.setTipoTerminaleCassaEntity(mockTerminaleCassa(i));
		return entity;
	}

	private TipoTerminaleCassaEntity mockTerminaleCassa(int i) {
		final List<String> types = Arrays.asList("A", "B", "C", "D", "E");
		final TipoTerminaleCassaEntity entity = new TipoTerminaleCassaEntity();
		entity.setId((long) i);
		entity.setTipo(types.get(i - 1));
		entity.setDescrizione("Cassa " + i);
		entity.setTipoTerminale(i);
		return entity;
	}

	private PromozioneStatoEntity mockPromozioneStato(int i) {
		final PromozioneStatoEntity entity = new PromozioneStatoEntity();
		entity.setId((long) i);
		entity.setDataInizioStato(new GregorianCalendar(2021, Calendar.MARCH, 1).getTime());
		entity.setDataFineStato(new GregorianCalendar(2021, Calendar.MARCH, 31).getTime());
		entity.setStatoPromozioneEntity(withStatoPromo(PromoStatusEnum.PROMOZIONE_IN_ESECUZIONE));
		return entity;
	}

	private StatoPromozioneEntity withStatoPromo(PromoStatusEnum code) {
		final StatoPromozioneEntity entity = new StatoPromozioneEntity();
		entity.setCodiceStato(code.getKey());
		entity.setDescrizione(code.getDescription());
		return entity;
	}
}
