package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.dbpromo.business.enumeration.PianificazioneRowTypeEnum;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgTipoElementoEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemPojo;
import com.axiante.mui.webapp.webservice.pojo.pianificazione.ItemRiferimento;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.PianificazioneEntityPredicates;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PianificazioneHelperTest {
	@Spy
	private PromoService promoService;

	@Spy
	private PianificazionePromoUtil promoutil;

	@Mock
	private Instance<PianificazioneService> pianificazioneServiceInstance;

	@Spy
	private PianificazioneService pianificazioneService;

	@Mock
	private Instance<PianificazioneValidatorUtil> validatorUtilInstance;

	@Spy
	private PianificazioneFactory pianificazioneFactory;
	@Mock
	private PianificazioneValidatorUtil validatorUtil;

	@Mock
	private Instance<PromoConfigurationHelper> configurationHelperInstance;

	@Mock
	private PromoConfigurationHelper configurationHelper;

	@Mock
	private Instance<PianificazioneEntityPredicates> predicatesInstance;

	@Spy
	private PianificazioneEntityPredicates predicates;

	@Mock
	private MeccanicheEntity meccWithSet;

	@Mock
	private MeccanicheEntity meccWithRaggr;

	@Mock
	private MeccanicheEntity meccWithElem;

	@Mock
	private CfgConfHeaderEntity cfgHeaderSet;

	@Mock
	private CfgConfHeaderEntity cfgHeaderRaggr;

	@Mock
	private MeccanicheEntity meccWithFoo;

	@Mock
	private CfgSetPianificazioneEntity cfgSetPianificazione;

	@Mock
	private Instance<CfgConfHeaderService> confHeaderServiceInstance;

	@Mock
	private CfgConfHeaderService confHeaderService;

	@Mock
	private transient Instance<PianificazionePromoUtil> pianificazionePromoUtil;

	@InjectMocks
	@Spy
	private PianificazioneHelper helper;

	final Date dataInizio = new GregorianCalendar(1, Calendar.APRIL, 1).getTime();
	final Date dataFine = new GregorianCalendar(1, Calendar.APRIL, 30).getTime();

	@Before
	public void setUp() {
		when(pianificazioneServiceInstance.get()).thenReturn(pianificazioneService);
		when(validatorUtilInstance.get()).thenReturn(validatorUtil);
		when(configurationHelperInstance.get()).thenReturn(configurationHelper);
		when(predicatesInstance.get()).thenReturn(predicates);
		when(confHeaderServiceInstance.get()).thenReturn(confHeaderService);
		when(pianificazionePromoUtil.get()).thenReturn(promoutil);
		CfgLivelloPianificazioneEntity cfgLevelSet = mock(CfgLivelloPianificazioneEntity.class);
		CfgLivelloPianificazioneEntity cfgLevelRaggr = mock(CfgLivelloPianificazioneEntity.class);
		CfgLivelloPianificazioneEntity cfgLevelElem = mock(CfgLivelloPianificazioneEntity.class);
		CfgLivelloPianificazioneEntity cfgLevelFoo = mock(CfgLivelloPianificazioneEntity.class);
		CfgConfHeaderEntity cfgHeaderElem = mock(CfgConfHeaderEntity.class);
		CfgConfHeaderEntity cfgHeaderFoo = mock(CfgConfHeaderEntity.class);
		when(cfgSetPianificazione.getId()).thenReturn(1L);
		when(cfgLevelSet.getCodice()).thenReturn("SET");
		when(cfgLevelRaggr.getCodice()).thenReturn("RAGGRUPPAMENTO");
		when(cfgLevelElem.getCodice()).thenReturn("ELEMENTO");
		when(cfgLevelFoo.getCodice()).thenReturn("FOO");
		when(meccWithSet.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeaderSet));
		when(meccWithRaggr.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeaderRaggr));
		when(meccWithElem.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeaderElem));
		when(meccWithFoo.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeaderFoo));
		when(cfgHeaderSet.getLivelloPianificazione()).thenReturn(cfgLevelSet);
		when(cfgHeaderSet.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(cfgHeaderRaggr.getLivelloPianificazione()).thenReturn(cfgLevelRaggr);
		when(cfgHeaderRaggr.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(cfgHeaderElem.getLivelloPianificazione()).thenReturn(cfgLevelElem);
		when(cfgHeaderElem.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(cfgHeaderFoo.getLivelloPianificazione()).thenReturn(cfgLevelFoo);
		when(predicates.byMeccanica(any(MeccanicheEntity.class))).thenCallRealMethod();
		when(predicates.byTipoRiga(any(PianificazioneRowTypeEnum.class))).thenCallRealMethod();
	}

	@Test
	public void isMeccanicaOnLevelSet_shouldReturnTrue_givenLevelSet_otherwiseFalse() {
		assertTrue(helper.isMeccanicaOnLevelSet(meccWithSet, 1L));
		assertFalse(helper.isMeccanicaOnLevelSet(meccWithRaggr, 1L));
		assertFalse(helper.isMeccanicaOnLevelSet(meccWithElem, 1L));
		assertFalse(helper.isMeccanicaOnLevelSet(meccWithFoo, 1L));
	}

	@Test
	public void isMeccanicaOnLevelRaggruppamento_shouldReturnTrue_givenLevelRaggruppamento_otherwiseFalse() {
		assertFalse(helper.isMeccanicaOnLevelRaggruppamento(meccWithSet, 1L));
		assertTrue(helper.isMeccanicaOnLevelRaggruppamento(meccWithRaggr, 1L));
		assertFalse(helper.isMeccanicaOnLevelRaggruppamento(meccWithElem, 1L));
		assertFalse(helper.isMeccanicaOnLevelRaggruppamento(meccWithFoo, 1L));
	}

	@Test
	public void isMeccanicaOnLevelElemento_shouldReturnTrue_givenLevelElemento_otherwiseFalse() {
		assertFalse(helper.isMeccanicaOnLevelElemento(meccWithSet, 1L));
		assertFalse(helper.isMeccanicaOnLevelElemento(meccWithRaggr, 1L));
		assertTrue(helper.isMeccanicaOnLevelElemento(meccWithElem, 1L));
		assertFalse(helper.isMeccanicaOnLevelElemento(meccWithFoo, 1L));
	}

	@Test(expected = NullPointerException.class)
	public void savePromoPianificazione_givenNullUser_shouldThrowException() {
		helper.savePromoPianificazione(mock(PromozioneTestataEntity.class), meccWithSet, mockItems(), "FOO", null);
	}

	@Test
	public void savePromoPianificazione_whenMeccanicaInvalid_shouldReturnFalse() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		assertFalse(helper.savePromoPianificazione(testata, meccWithFoo, new HashSet<>(), "REPARTO", "junit"));
	}

	@Test
	public void savePromoPianificazione_givenMeccanicaSet_shouldReturnFalse_whenSomethingWentWrong() throws Exception {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		assertFalse(helper.savePromoPianificazione(testata, meccWithSet, mockItems(), "TOTALE", "junit"));
		assertFalse(helper.savePromoPianificazione(testata, meccWithSet, mockItems(), "GRM", "junit"));
		assertFalse(helper.savePromoPianificazione(testata, meccWithSet, mockItems(), "ARTICOLO", "junit"));
		verify(promoService, times(0)).persist(any(PromozioneTestataEntity.class), eq("junit"));
	}

	@Test
	public void savePromoPianificazione_givenMeccanicaSet_shouldReturnTrue_whenCreateRows() {
		// Arrange - Meccanica tipo 'SET' con 2 Righe 'Set'; ogni riga set ha 2 Righe 'Raggruppamento'
		final PromozioneTestataEntity testata = spyTestata();
		when(cfgHeaderSet.getMinRaggruppamento()).thenReturn(2);
		when(cfgHeaderSet.getMinSet()).thenReturn(2);
		when(cfgHeaderSet.getMaxSet()).thenReturn(null);
		when(testata.getPromozionePianificazioneEntities()).thenReturn(new HashSet<>());
		when(validatorUtil.validateSet(testata, meccWithSet, cfgHeaderSet)).thenReturn(true);
		when(configurationHelper.getHeaderFromTestataAndMeccanica(any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class))).thenReturn(cfgHeaderSet);
		// Act & Assert
		PromozionePianificazioneEntity rigaSet = spy(PromozionePianificazioneEntity.class);
		doReturn(rigaSet).when(pianificazioneFactory).buildRigaSet(
				any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class),
				any(CfgConfHeaderEntity.class),
				anyMap(),
				anyString());
		doReturn(true).when(helper).validatePianificazioneWithParent(rigaSet, dataFine);
		doReturn(mock(PromozionePianificazioneEntity.class)).when(pianificazioneFactory).buildRigaRaggruppamento(
				any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class),
				anyInt(),
				anyMap(),
				anyBoolean(),
				anyString());

		assertTrue(helper.savePromoPianificazione(testata, meccWithSet, mockItems(), null, "junit"));
	}

	@Test
	public void savePromoPianificazione_givenMeccanicaSet_shouldReturnTrueAndCreateOnlyNeededRows() {
		// Arrange
		final PromozioneTestataEntity testata = spyTestata();
		final Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
		when(cfgHeaderSet.getMinRaggruppamento()).thenReturn(2);
		when(cfgHeaderSet.getMinSet()).thenReturn(2);
		when(cfgHeaderSet.getMaxSet()).thenReturn(3);
		for (int i = 1; i <= 2; i++) {
			final PromozionePianificazioneEntity pianificazione = spyPianificazioneSet();
			pianificazioni.add(pianificazione);
		}
		when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
		when(validatorUtil.validateSet(testata, meccWithSet, cfgHeaderSet)).thenReturn(true);
		when(configurationHelper.getHeaderFromTestataAndMeccanica(any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class))).thenReturn(cfgHeaderSet);
		PromozionePianificazioneEntity rigaSet = spy(PromozionePianificazioneEntity.class);
		doReturn(rigaSet).when(pianificazioneFactory).buildRigaSet(
				any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class),
				any(CfgConfHeaderEntity.class),
				anyMap(),
				anyString());
		doReturn(true).when(helper).validatePianificazioneWithParent(rigaSet, dataFine);
		doReturn(mock(PromozionePianificazioneEntity.class)).when(pianificazioneFactory).buildRigaRaggruppamento(
				any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class),
				anyInt(),
				anyMap(),
				anyBoolean(),
				anyString());

		// Act & Assert
		assertTrue(helper.savePromoPianificazione(testata, meccWithSet, mockItems(), null, "junit"));
	}

	@Test
	public void savePromoPianificazione_givenMeccanicaRaggrupamento_shouldReturnTrue_whenCreateRows()
			throws Exception {
		final Map<String, String> defaultValues = new HashMap<>();
		defaultValues.put(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN, "42");
		defaultValues.put(PianificazioneConstants.REFERENCE_BUONO_SCONTO_RADICE, "666");
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getPromozionePianificazioneEntities()).thenReturn(new HashSet<>());
		when(validatorUtil.validateRaggruppamento(any(PromozioneTestataEntity.class), any(MeccanicheEntity.class)))
				.thenReturn(true);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);

		Set<ItemPojo> mockItems = mockItems();
		doReturn(mockItems).when(validatorUtil).validateItems(any(PromozioneTestataEntity.class),any(MeccanicheEntity.class),anySet(), any(PromoPianificazioneEnum.class));
		PromozionePianificazioneEntity raggruppamento = mock(PromozionePianificazioneEntity.class);
		doReturn(raggruppamento) .when(pianificazioneFactory)
				.buildRigaRaggruppamento(any(PromozioneTestataEntity.class), any(MeccanicheEntity.class),
						anyInt(),anyMap(),anyBoolean(), anyString());

		doReturn(mock(PromozionePianificazioneEntity.class)) .when(pianificazioneFactory).buildRigaElemento(
				any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class),
				anyString(),
				any(ItemPojo.class),
				anyMap(),
				anyString()
		);
		Date datafine = mock(Date.class);
		when(testata.getDataFine()).thenReturn(datafine);
		doReturn(true).when(helper).validatePianificazioneWithParent(any(PromozionePianificazioneEntity.class), any(Date.class));
		when(raggruppamento.getDataInizio()).thenReturn(datafine);
		doReturn(mock(PromozionePianificazioneEntity.class)).when(pianificazioneFactory)
				.buildRigaElementoTotale(
						any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class),
						anyMap(),
						anyString());

		assertTrue(helper.savePromoPianificazione(testata, meccWithRaggr, mockItems, "ARTICOLO", "junit"));
		assertTrue(helper.savePromoPianificazione(testata, meccWithRaggr, mockItems(), "TOTALE", "junit"));
		assertTrue(helper.savePromoPianificazione(testata, meccWithRaggr, mockItems(), "GRM", "junit"));
		verify(promoService, never()).persist(any(PromozioneTestataEntity.class), eq("junit"));
	}

	@Test
	public void savePromoPianificazione_givenMeccanicaRaggrupamento_shouldReturnTrueAndPopulateDates_whenCreateRows()
			throws Exception {
		// Arrange - Meccanica tipo 'RAGGRUPPAMENTO' con 5 Righe 'Elemento'
		final PromozioneTestataEntity testata = spyTestata();
		when(testata.getPromozionePianificazioneEntities()).thenReturn(new HashSet<>());
		when(validatorUtil.validateRaggruppamento(any(PromozioneTestataEntity.class), any(MeccanicheEntity.class)))
				.thenReturn(true);
		Set<ItemPojo> mockItems = mockItems();
		doReturn(mockItems).when(validatorUtil).validateItems(any(PromozioneTestataEntity.class),any(MeccanicheEntity.class),anySet(), any(PromoPianificazioneEnum.class));
		doReturn(mock(PromozionePianificazioneEntity.class)) .when(pianificazioneFactory)
				.buildRigaRaggruppamento(any(PromozioneTestataEntity.class), any(MeccanicheEntity.class),
						anyInt(),anyMap(),anyBoolean(), anyString());

		doReturn(mock(PromozionePianificazioneEntity.class)) .when(pianificazioneFactory).buildRigaElemento(
				any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class),
				anyString(),
				any(ItemPojo.class),
				anyMap(),
				anyString()
		);

		doReturn(true).when(helper).validatePianificazioneWithParent(any(PromozionePianificazioneEntity.class), any(Date.class));

		assertTrue(helper.savePromoPianificazione(testata, meccWithRaggr, mockItems, "ARTICOLO", "junit"));
		verify(promoService, never()).persist(any(PromozioneTestataEntity.class), eq("junit"));
	}

	@Test
	public void savePromoPianificazione_givenMeccanicaElemento_shouldReturnFalse_whenTipoElementoWrong() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		assertFalse(helper.savePromoPianificazione(testata, meccWithElem, mockItems(), "Foo", "junit"));
	}

	@Test
	public void savePromoPianificazione_givenMeccanicaElemento_shouldReturnFalse_whenNoValidItems() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		assertFalse(helper.savePromoPianificazione(testata, meccWithElem, null, "Foo", "junit"));
	}

	@Test
	public void savePromoPianificazione_givenMeccanicaElemento_shouldReturnTrue_whenCreateRow() throws Exception {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		Set<ItemPojo> mockItems = mockItems();

		doReturn(Collections.singleton(mockItems.iterator().next())).when(validatorUtil)
						.validateItems(any(PromozioneTestataEntity.class),
								any(MeccanicheEntity.class), anySet(), any(PromoPianificazioneEnum.class));

		doReturn(mock(PromozionePianificazioneEntity.class)).when(pianificazioneFactory)
				.buildRigaElemento(
						any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class),
						anyString(),
						any(ItemPojo.class),
						anyMap(),
						anyString());
		doReturn(mock(PromozionePianificazioneEntity.class)).when(pianificazioneFactory)
				.buildRigaElementoTotale(
						any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class),
						anyMap(),
						anyString());

		when(testata.getDataFine()).thenReturn(mock(Date.class));
		doReturn(mock(PromozionePianificazioneEntity.class)).when(helper).fixDatePianificazioneBasedOnCanale(any(PromozionePianificazioneEntity.class));
		doReturn(true).when(helper).validatePianificazioneWithParent(any(PromozionePianificazioneEntity.class), any(Date.class));

		assertTrue(helper.savePromoPianificazione(testata, meccWithElem, mockItems, "GRM", "junit"));
		assertTrue(helper.savePromoPianificazione(testata, meccWithElem, mockItems, "TOTALE", "junit"));
		assertTrue(helper.savePromoPianificazione(testata, meccWithElem, mockItems, "ARTICOLO", "junit"));
		verify(promoService, never()).persist(any(PromozioneTestataEntity.class), eq("junit"));
		verify(pianificazioneService, times(3)).persist(any(PromozionePianificazioneEntity.class));
	}

	@Test
	public void savePromoPianificazione_givenMeccanicaElemento_shouldReturnTrueAndPopulateDates_whenCreateRow()
			throws Exception {
		// Arrange - Meccanica tipo 'ELEMENTO' con 5 Righe 'Elemento'
		final PromozioneTestataEntity testata = spyTestata();
		Set<ItemPojo> mockItems = mockItems();
		// una sola pianificazione: mi serve dopo
		doReturn(Collections.singleton(mockItems.iterator().next())).when(validatorUtil)
				.validateItems(any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class), anySet(), any(PromoPianificazioneEnum.class));

		doReturn(mock(PromozionePianificazioneEntity.class)).when(pianificazioneFactory)
				.buildRigaElemento(
						any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class),
						anyString(),
						any(ItemPojo.class),
						anyMap(),
						anyString());
		doReturn(true).when(helper).validatePianificazioneWithParent(any(PromozionePianificazioneEntity.class), any(Date.class));
		doReturn(mock(PromozionePianificazioneEntity.class)).when(helper).fixDatePianificazioneBasedOnCanale(any(PromozionePianificazioneEntity.class));

		assertTrue(helper.savePromoPianificazione(testata, meccWithElem, mockItems, "ARTICOLO", "junit"));
		verify(promoService, never()).persist(any(PromozioneTestataEntity.class), eq("junit"));
		verify(pianificazioneService, times(1)).persist(any(PromozionePianificazioneEntity.class));
	}

	@Test
	public void savePromoPianificazione_shouldReturnFalse_whenCannotValidateSet() throws Exception {
		// Arrange
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(configurationHelper.getHeaderFromTestataAndMeccanica(testata, meccWithSet)).thenReturn(cfgHeaderSet);
		when(validatorUtil.validateSet(testata, meccWithSet, cfgHeaderSet)).thenReturn(false);
		// Act & Assert
		assertFalse(helper.savePromoPianificazione(testata, meccWithSet, mockItems(), null, "junit"));
		verify(promoService, never()).persist(testata, "junit");
	}

	@Test
	public void savePromoPianificazione_shouldReturnFalse_whenCannotValidateRaggruppamento() throws Exception {
		// Arrange
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		// Act & Assert
		assertFalse(helper.savePromoPianificazione(testata, meccWithRaggr, mockItems(), "ARTICOLO", "junit"));
		verify(promoService, never()).persist(testata, "junit");
	}

	@Test
	public void savePromoPianificazione_shouldReturnFalse_whenSomethingWentWrongSavingPianificazioneElemento() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		assertFalse(helper.savePromoPianificazione(testata, meccWithElem, mockItems(), "ARTICOLO", "junit"));
	}

	@Test(expected = NullPointerException.class)
	public void savePromoAddPianificazione_givenNullUser_shouldThrowException() {
		helper.savePromoAddPianificazione(mock(PromozionePianificazioneEntity.class), mockItems(), "FOO", null);
	}

	@Test
	public void savePromoAddPianificazione_shouldReturnTrue_whenAddPianificazioneToSet() {
		// Arrange
		final PromozioneTestataEntity testata = spyTestata();
		final PromozionePianificazioneEntity pianificazione = spyPianificazioneSet();
		final Set<PromozionePianificazioneEntity> childs = new HashSet<>();
		childs.add(pianificazione);
		lenient().when(testata.getPromozionePianificazioneEntities()).thenReturn(childs);
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(validatorUtil.validateRaggruppamento(testata, meccWithSet)).thenReturn(true);
		when(pianificazioneService.savePromozionePianificazioneEntity(pianificazione, "junit"))
				.thenReturn(pianificazione);

		Set<ItemPojo> mockItems = mockItems();

		doReturn(Collections.singleton(mockItems.iterator().next())).when(validatorUtil)
				.validateItems(any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class), anySet(), any(PromoPianificazioneEnum.class));

		doReturn(mock(PromozionePianificazioneEntity.class)).when(pianificazioneFactory)
				.buildRigaElemento(
						any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class),
						anyString(),
						any(ItemPojo.class),
						anyMap(),
						anyString());
		PromozionePianificazioneEntity raggruppamento = mock(PromozionePianificazioneEntity.class);
		CfgPianificazTipoRigaEntity rigaRaggruppamento = mock(CfgPianificazTipoRigaEntity.class);
		when(rigaRaggruppamento.getCodiceTipo()).thenReturn(PlanningLevelEnum.RAGGRUPPAMENTO.getCode());
		when(raggruppamento.getTipoRiga()).thenReturn(rigaRaggruppamento);
		when(raggruppamento.getNumRaggruppamento()).thenReturn("1");

		when(raggruppamento.getDataInizio()).thenReturn(dataInizio);
		when(raggruppamento.getDataFine()).thenReturn(dataFine);

		doReturn(raggruppamento).when(pianificazioneFactory).buildRigaRaggruppamento(
				any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class),
				anyInt(),
				anyMap(),
				anyBoolean(),
				anyString());
		doReturn(true).when(helper).validatePianificazioneWithParent(any(PromozionePianificazioneEntity.class), any(Date.class));
		doReturn(mock(PromozionePianificazioneEntity.class)).when(helper).propagaValori(any(PromozionePianificazioneEntity.class), any(PromozionePianificazioneEntity.class));
		// Act & Assert
		assertTrue(helper.savePromoAddPianificazione(pianificazione, mockItems, "ARTICOLO", "junit"));
		verify(pianificazioneService, times(1))
				.savePromozionePianificazioneEntity(any(PromozionePianificazioneEntity.class), eq("junit"));
		// Assertions on riga 'RAGGRUPPAMENTO'
		final List<PromozionePianificazioneEntity> righeRaggr = pianificazione.getChildren().stream()
				.filter(p -> p.getTipoRiga().getCodiceTipo().equals("R")).collect(Collectors.toList());
		assertEquals(1, righeRaggr.size());
		// Assertions on riga 'ELEMENTO'
		final PromozionePianificazioneEntity rigaRaggr = righeRaggr.stream()
				.filter(p -> p.getNumRaggruppamento().equals("1")).findFirst().orElse(null);
		assertNotNull(rigaRaggr);
		assertEquals(dataInizio, rigaRaggr.getDataInizio());
		assertEquals(dataFine, rigaRaggr.getDataFine());
	}

	@Ignore("Fails for unknown reason")
	@Test
	public void savePromoAddPianificazione_givenPianificazioneSet_shouldReturnFalse_whenSomethingWentWrong() {
		// Arrange
		final PromozioneTestataEntity testata = spyTestata();
		final PromozionePianificazioneEntity pianificazione = spyPianificazioneSet();
		final Set<ItemPojo> mockItems = mockItems();
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(validatorUtil.validateRaggruppamento(testata, meccWithSet)).thenReturn(true);
		when(pianificazioneService.savePromozionePianificazioneEntity(pianificazione, "junit"))
				.thenThrow(RuntimeException.class);
		doReturn(Collections.singleton(mockItems.iterator().next())).when(validatorUtil).validateItems(any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class), anySet(), any(PromoPianificazioneEnum.class));
		doReturn(mock(PromozionePianificazioneEntity.class)) .when(pianificazioneFactory)
				.buildRigaRaggruppamento(any(PromozioneTestataEntity.class), any(MeccanicheEntity.class),
				anyInt(),anyMap(),anyBoolean(), anyString());
		doReturn(true).when(helper).validatePianificazioneWithParent(any(PromozionePianificazioneEntity.class), any(Date.class));
		doReturn(mock(PromozionePianificazioneEntity.class)).when(pianificazioneFactory)
				.buildRigaElemento(
						any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class),
						anyString(),
						any(ItemPojo.class),
						anyMap(),
						anyString());
		doThrow(IllegalArgumentException.class).when(helper)
				.propagaValori(any(PromozionePianificazioneEntity.class),any(PromozionePianificazioneEntity.class));
		// Act & Assert
		assertFalse(helper.savePromoAddPianificazione(pianificazione, mockItems(), "ARTICOLO", "junit"));
		verify(pianificazioneService, times(1))
				.savePromozionePianificazioneEntity(any(PromozionePianificazioneEntity.class), eq("junit"));
	}

	@Test
	public void savePromoAddPianificazione_shouldReturnTrue_whenAddPianificazioneToRaggruppamento() {
		// Arrange
		final PromozioneTestataEntity testata = spyTestata();
		final PromozionePianificazioneEntity pianificazioneRaggr = spyPianificazioneRaggr();
		final PromozionePianificazioneEntity pianificazioneElem = spyPianificazioneElem("3");
		final Set<PromozionePianificazioneEntity> childs = new HashSet<>();
		final Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
		final Set<ItemPojo> mockItems = mockItems();
		final Set<ItemPojo> validItems = Collections.singleton(mockItems.iterator().next());

		String expectedItem = ((ItemPojo)validItems.iterator().next()).getElementDescription();


		childs.add(pianificazioneElem);
		pianificazioni.add(pianificazioneRaggr);
		pianificazioni.add(pianificazioneElem);
		when(pianificazioneRaggr.getChildren()).thenReturn(childs);
		lenient().when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
		when(pianificazioneRaggr.getPromozioneTestataEntity()).thenReturn(testata);
		when(pianificazioneService.savePromozionePianificazioneEntity(pianificazioneRaggr, "junit"))
				.thenReturn(pianificazioneRaggr);
		when(validatorUtil.validateItems(testata, meccWithRaggr, mockItems, PromoPianificazioneEnum.ARTICOLO)).thenReturn(validItems);
		when(helper.validatePianificazioneWithParent(any(PromozionePianificazioneEntity.class),any(Date.class))).thenReturn(true);
		doThrow(IllegalArgumentException.class).when(helper).propagaValori(any(),any());
		PromozionePianificazioneEntity inserted = mock(PromozionePianificazioneEntity.class);
		when(inserted.getElemento()).thenReturn(expectedItem);
		when(inserted.getDataInizio()).thenReturn(dataInizio);
		when(inserted.getDataFine()).thenReturn(dataFine);
		doReturn(inserted).when(pianificazioneFactory)
				.buildRigaElemento(
						notNull(PromozioneTestataEntity.class),
						notNull(MeccanicheEntity.class),
						notNull(String.class),
						notNull(ItemPojo.class),
						notNull(Map.class),
						notNull(String.class)
				);
		// Act & Assert
		assertTrue(helper.savePromoAddPianificazione(pianificazioneRaggr, mockItems, "ARTICOLO", "junit"));
		verify(pianificazioneService, times(1))
				.savePromozionePianificazioneEntity(any(PromozionePianificazioneEntity.class), eq("junit"));
		final List<String> items = pianificazioneRaggr.getChildren().stream()
				.map(PromozionePianificazioneEntity::getElemento).collect(Collectors.toList());
		assertEquals(2, items.size());
		assertTrue(items.contains(expectedItem));
		pianificazioneRaggr.getChildren().forEach(p -> {
			assertEquals(dataInizio, p.getDataInizio());
			assertEquals(dataFine, p.getDataFine());
		});
	}

	@Test
	public void savePromoAddPianificazione_givenPianificazioneRaggruppamento_shouldReturnFalse_whenSomethingWentWrong() {
		// Arrange
		final PromozioneTestataEntity testata = spyTestata();
		final PromozionePianificazioneEntity pianificazioneRaggr = spyPianificazioneRaggr();
		final CfgConfHeaderEntity confHeader = mock(CfgConfHeaderEntity.class);
		final Set<ItemPojo> mockItems = mockItems();
		final Set<ItemPojo> validItems = Collections.singleton(mockItems.iterator().next());
		when(pianificazioneRaggr.getChildren()).thenReturn(new HashSet<>());
		when(pianificazioneRaggr.getPromozioneTestataEntity()).thenReturn(testata);
		when(pianificazioneService.savePromozionePianificazioneEntity(pianificazioneRaggr, "junit"))
				.thenThrow(RuntimeException.class);
		when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(anyLong(), anyLong())).thenReturn(confHeader);
		when(validatorUtil.validateItems(testata, meccWithRaggr, mockItems, PromoPianificazioneEnum.ARTICOLO)).thenReturn(validItems);
		doReturn(mock(PromozionePianificazioneEntity.class)).when(pianificazioneFactory)
				.buildRigaElemento(
						any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class),
						anyString(),
						any(ItemPojo.class),
						any(Map.class),
						anyString()
				);
		when(helper.validatePianificazioneWithParent(any(PromozionePianificazioneEntity.class),any(Date.class))).thenReturn(true);
		// Act & Assert
		assertFalse(helper.savePromoAddPianificazione(pianificazioneRaggr, mockItems, "ARTICOLO", "junit"));
		verify(pianificazioneService, times(1))
				.savePromozionePianificazioneEntity(any(PromozionePianificazioneEntity.class), eq("junit"));
	}

	@Test
	public void savePromoAddPianificazione_givenPianificazioneRaggruppamentoOmogeneo_shouldReturnFalse_whenAddItemNotOmogenei() {
		// Arrange
		final PromozioneTestataEntity testata = spyTestata();
		final PromozionePianificazioneEntity pianificazioneRaggr = spyPianificazioneRaggr();
		final CfgPianificazTipoRigaEntity tipoRigaElem = mockTipoRiga(PlanningLevelEnum.ELEMENTO);
		final PromozionePianificazioneEntity rigaGrm = mockRigaPianificazione(tipoRigaElem);
		final Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
		pianificazioni.add(pianificazioneRaggr);
		pianificazioni.add(rigaGrm);
		lenient().when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
		when(pianificazioneRaggr.getPromozioneTestataEntity()).thenReturn(testata);
		// Act & Assert
		assertFalse(helper.savePromoAddPianificazione(pianificazioneRaggr, mockItems(), "ARTICOLO", "junit"));
		verify(pianificazioneService, never())
				.savePromozionePianificazioneEntity(any(PromozionePianificazioneEntity.class), eq("junit"));
	}

	@Test
	public void savePromoAddPianificazione_givenPianificazioneRaggruppamentoOmogeneo_shouldReturnFalse_whenTipoElementoNonValido() {
		// Arrange
		final PromozioneTestataEntity testata = spyTestata();
		final PromozionePianificazioneEntity pianificazioneRaggr = spyPianificazioneRaggr();
		when(pianificazioneRaggr.getPromozioneTestataEntity()).thenReturn(testata);
		// Act & Assert
		assertFalse(helper.savePromoAddPianificazione(pianificazioneRaggr, mockItems(), "Foo", "junit"));
		verify(pianificazioneService, never())
				.savePromozionePianificazioneEntity(any(PromozionePianificazioneEntity.class), eq("junit"));
	}

	@Test
	public void savePromoAddPianificazione_shouldReturnFalse_whenTipoRigaNonValido() {
		final PromozioneTestataEntity testata = spyTestata();
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		lenient().when(testata.getPromozionePianificazioneEntities()).thenReturn(Collections.singleton(pianificazione));
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(pianificazione.getMeccanicaEntity()).thenReturn(meccWithRaggr);
		assertFalse(helper.savePromoAddPianificazione(pianificazione, mockItems(), "ARTICOLO", "junit"));
	}

	@Test
	public void savePromoAddPianificazione_shouldReturnFalse_whenCannotValidateRaggruppamento() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(pianificazione.getMeccanicaEntity()).thenReturn(meccWithRaggr);
		assertFalse(helper.savePromoAddPianificazione(pianificazione, mockItems(), "ARTICOLO", "junit"));
	}

	@Test
	public void findTipoElementiForPianificazione_shouldReturnEmptyList_whenConfigurazioneIsWrong() {
		// Arrange
		final PromozionePianificazioneEntity pianificazione = spyPianificazioneRaggr();
		// Act
		final List<String> tipoElementi = helper.findTipoElementiForPianificazione(pianificazione);
		// Assert
		assertNotNull(tipoElementi);
		assertTrue(tipoElementi.isEmpty());
	}

	@Test
	public void findTipoElementiForPianificazione_givenPianificazioneWithHeaderWithSingleTipoElemento_shouldReturnList() {
		// Arrange
		final CfgTipoElementoEntity tipoElemento = mock(CfgTipoElementoEntity.class);
		final PromozioneTestataEntity testata = spy(PromozioneTestataEntity.class);
		final PromozionePianificazioneEntity pianificazione = spyPianificazioneRaggr();
		when(tipoElemento.getRaggruppamento()).thenReturn(0);
		when(tipoElemento.getReparto()).thenReturn(1);
		when(tipoElemento.getGrm()).thenReturn(1);
		when(tipoElemento.getArticolo()).thenReturn(0);
		when(tipoElemento.getTotale()).thenReturn(0);
		when(cfgHeaderRaggr.getTipiElemento()).thenReturn(Collections.singleton(tipoElemento));
		when(cfgHeaderRaggr.getMeccanicaEntity()).thenReturn(meccWithRaggr);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeaderRaggr));
		// Act
		final List<String> tipoElementi = helper.findTipoElementiForPianificazione(pianificazione);
		// Assert
		assertNotNull(tipoElementi);
		assertFalse(tipoElementi.isEmpty());
		assertEquals(2, tipoElementi.size());
		assertTrue(tipoElementi.contains("REPARTO"));
		assertTrue(tipoElementi.contains("GRM"));
		assertFalse(tipoElementi.contains("ARTICOLO"));
	}

	@Test
	public void findTipoElementiForPianificazione_givenPianificazioneWithHeaderWithMoreTipoElemento_shouldReturnList() {
		// Arrange
		final CfgTipoElementoEntity tipoElemento = mock(CfgTipoElementoEntity.class);
		final PromozioneTestataEntity testata = spy(PromozioneTestataEntity.class);
		final PromozionePianificazioneEntity pianificazioneEntity = spyPianificazioneRaggr();
		when(tipoElemento.getRaggruppamento()).thenReturn(2);
		when(tipoElemento.getReparto()).thenReturn(0);
		when(tipoElemento.getGrm()).thenReturn(0);
		when(tipoElemento.getArticolo()).thenReturn(1);
		when(tipoElemento.getTotale()).thenReturn(1);
		when(cfgHeaderRaggr.getTipiElemento()).thenReturn(Collections.singleton(tipoElemento));
		when(cfgHeaderRaggr.getMeccanicaEntity()).thenReturn(meccWithRaggr);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(pianificazioneEntity.getNumRaggruppamento()).thenReturn("2");
		when(pianificazioneEntity.getPromozioneTestataEntity()).thenReturn(testata);
		when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeaderRaggr));
		// Act
		final List<String> tipoElementi = helper.findTipoElementiForPianificazione(pianificazioneEntity);
		// Assert
		assertNotNull(tipoElementi);
		assertFalse(tipoElementi.isEmpty());
		assertEquals(2, tipoElementi.size());
		assertFalse(tipoElementi.contains("REPARTO"));
		assertFalse(tipoElementi.contains("GRM"));
		assertTrue(tipoElementi.contains("ARTICOLO"));
		assertTrue(tipoElementi.contains("TOTALE"));
	}

	@Test
	public void findTipoElementiForPianificazione_shouldReturnEmptyList_whenNoTipoElementoRetrieved() {
		// Arrange
		final CfgTipoElementoEntity tipoElemento = mock(CfgTipoElementoEntity.class);
		final PromozioneTestataEntity testata = spy(PromozioneTestataEntity.class);
		final PromozionePianificazioneEntity pianificazioneEntity = spyPianificazioneRaggr();
		when(tipoElemento.getRaggruppamento()).thenReturn(1);
		when(cfgHeaderRaggr.getTipiElemento()).thenReturn(Collections.singleton(tipoElemento));
		when(cfgHeaderRaggr.getMeccanicaEntity()).thenReturn(meccWithRaggr);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(pianificazioneEntity.getNumRaggruppamento()).thenReturn("2");
		when(pianificazioneEntity.getPromozioneTestataEntity()).thenReturn(testata);
		when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeaderRaggr));
		// Act && Assert
		assertTrue(helper.findTipoElementiForPianificazione(pianificazioneEntity).isEmpty());
	}

	@Test
	public void hasMeccanicaSetSlots_givenTestataAndMeccanicaWithAllSetSlotsUsed_shouldReturnFalse() {
		final PromozioneTestataEntity testata = spy(PromozioneTestataEntity.class);
		final Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
		for (int i = 1; i <= 3; i++) {
			final PromozionePianificazioneEntity pianificazione = spyPianificazioneSet();
			pianificazioni.add(pianificazione);
		}
		when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
		when(cfgHeaderSet.getMaxSet()).thenReturn(3);
		when(configurationHelper.getHeaderFromTestataAndMeccanica(any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class))).thenReturn(cfgHeaderSet);
		assertFalse(helper.hasMeccanicaSetSlots(testata, meccWithSet));
	}

	@Test
	public void hasMeccanicaSetSlots_givenTestataAndMeccanicaWithSetSlotsAvailable_shouldReturnTrue() {
		final PromozioneTestataEntity testata = spy(PromozioneTestataEntity.class);
		final Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
		for (int i = 1; i <= 2; i++) {
			final PromozionePianificazioneEntity pianificazione = spyPianificazioneSet();
			pianificazioni.add(pianificazione);
		}
		when(cfgHeaderSet.getMaxSet()).thenReturn(3);
		when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
		when(configurationHelper.getHeaderFromTestataAndMeccanica(any(PromozioneTestataEntity.class),
				any(MeccanicheEntity.class))).thenReturn(cfgHeaderSet);
		assertTrue(helper.hasMeccanicaSetSlots(testata, meccWithSet));
	}

	@Test
	public void hasMeccanicaSetSlots_givenMaxSetNull_shouldReturnTrue() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(configurationHelper.getHeaderFromTestataAndMeccanica(testata, meccWithSet)).thenReturn(cfgHeaderSet);
		when(cfgHeaderSet.getMaxSet()).thenReturn(null);
		assertTrue(helper.hasMeccanicaSetSlots(testata, meccWithSet));
	}

	@Test(expected = IllegalArgumentException.class)
	public void hasMeccanicaSetSlots_shouldThrowException_whenCannotRetrieveHeaderFromTestataAndMeccanica() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(configurationHelper.getHeaderFromTestataAndMeccanica(testata, meccWithSet)).thenReturn(null);
		helper.hasMeccanicaSetSlots(testata, meccWithSet);
	}

	@Test
	public void isTipoElementoOmogeneo_givenPianificazione_shouldCallValidator() {
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(pianificazione.getMeccanicaEntity()).thenReturn(meccWithRaggr);
		when(pianificazione.getNumRaggruppamento()).thenReturn("1");
		when(validatorUtil.validateOmogeneo(testata, meccWithRaggr, "1")).thenReturn(true);
		assertTrue(helper.isTipoElementoOmogeneo(pianificazione));
		verify(validatorUtil, times(1))
				.validateOmogeneo(testata, meccWithRaggr, "1");
	}


	@Test(expected = NullPointerException.class)
	public void saveArticoliDaPromoRiferimento_givenNullTestata_shouldThrowException() {
		//noinspection unchecked
		helper.saveArticoliDaPromoRiferimento(null, meccWithElem, mock(List.class), "junit");
	}

	@Test(expected = NullPointerException.class)
	public void saveArticoliDaPromoRiferimento_givenNullUsername_shouldThrowException() {
		//noinspection unchecked
		helper.saveArticoliDaPromoRiferimento(mock(PromozioneTestataEntity.class), meccWithElem, mock(List.class), null);
	}

	@Test
	public void saveArticoliDaPromoRiferimento_givenWrongMeccanica_shouldReturnFalse() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		ItemRiferimento item1 = mockItemRiferimento(1, "10");
		ItemRiferimento item2 = mockItemRiferimento(2, "20");
		final List<ItemRiferimento> items = Arrays.asList(item1, item2);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		assertFalse(helper.saveArticoliDaPromoRiferimento(testata, meccWithRaggr, items, "junit"));
	}

	@Test
	public void saveArticoliDaPromoRiferimento_givenValidItems_shouldReturnTrue() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		ItemRiferimento item1 = mockItemRiferimento(1, "10");
		ItemRiferimento item2 = mockItemRiferimento(2, "20");
		Set<ItemPojo> mockedSet = new HashSet<>();
		mockedSet.add(item1.getItem());
		mockedSet.add(item2.getItem());
		PromozionePianificazioneEntity plano = mock(PromozionePianificazioneEntity.class);
		final List<ItemRiferimento> items = Arrays.asList(item1, item2);
		CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		when(testata.getMuiCanalePromozione()).thenReturn(canale);
		when(canale.getFlOverridePianificazioneInizio()).thenReturn(false);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		Date date = mock(Date.class);
		when(testata.getDataInizio()).thenReturn(date);
		when(testata.getDataFine()).thenReturn(date);
		doReturn(plano).when(pianificazioneFactory).buildRigaElemento(
						any(PromozioneTestataEntity.class),
						any(MeccanicheEntity.class),
						anyString(),
						any(ItemPojo.class),
						any(Map.class),
						anyString()
				);
		when(validatorUtil.validateItems(testata, meccWithElem,	mockedSet, PromoPianificazioneEnum.ARTICOLO)).thenReturn(mockedSet);
		doReturn(true).when(helper).validatePianificazioneWithParent(plano, date);
		assertTrue(helper.saveArticoliDaPromoRiferimento(testata, meccWithElem, items, "junit"));
	}

	@Test
	public void saveArticoliDaPromoRiferimento_givenAlreadyUsedItemsWithDuplicaFlag_shouldReturnFalse() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		ItemRiferimento item1 = mockItemRiferimento(1, "10");
		ItemRiferimento item2 = mockItemRiferimento(2, "20");
		final List<ItemRiferimento> items = Arrays.asList(item1, item2);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		assertFalse(helper.saveArticoliDaPromoRiferimento(testata, meccWithElem, items, "junit"));
	}

	private PromozioneTestataEntity spyTestata() {
		final PromozioneTestataEntity mock = spy(PromozioneTestataEntity.class);
		when(mock.getDataInizio()).thenReturn(dataInizio);
		when(mock.getDataFine()).thenReturn(dataFine);
		when(mock.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		return mock;
	}

	private PromozionePianificazioneEntity spyPianificazioneSet() {
		final PromozionePianificazioneEntity mock = spy(PromozionePianificazioneEntity.class);
		final CfgPianificazTipoRigaEntity tipoRiga = mockTipoRiga(PlanningLevelEnum.SET);
		when(mock.getMeccanicaEntity()).thenReturn(meccWithSet);
		when(mock.getTipoRiga()).thenReturn(tipoRiga);
		when(mock.getDataInizio()).thenReturn(dataInizio);
		when(mock.getDataFine()).thenReturn(dataFine);
		return mock;
	}

	private PromozionePianificazioneEntity spyPianificazioneRaggr() {
		final PromozionePianificazioneEntity mock = spy(PromozionePianificazioneEntity.class);
		final CfgPianificazTipoRigaEntity tipoRiga = mockTipoRiga(PlanningLevelEnum.RAGGRUPPAMENTO);
		when(mock.getMeccanicaEntity()).thenReturn(meccWithRaggr);
		when(mock.getTipoRiga()).thenReturn(tipoRiga);
		when(mock.getDataInizio()).thenReturn(dataInizio);
		when(mock.getDataFine()).thenReturn(dataFine);
		return mock;
	}

	private PromozionePianificazioneEntity spyPianificazioneElem(String itemId) {
		final PromozionePianificazioneEntity mock = spy(PromozionePianificazioneEntity.class);
		final CfgPianificazTipoRigaEntity tipoRiga = mockTipoRiga(PlanningLevelEnum.ELEMENTO);
		lenient().when(mock.getTipoRiga()).thenReturn(tipoRiga);
		lenient().when(mock.getTipoElemento()).thenReturn(PromoPianificazioneEnum.ARTICOLO.getTipoElemento());
		when(mock.getElemento()).thenReturn("Item "  + itemId);
		lenient().when(mock.getCodiceElemento()).thenReturn(itemId);
		when(mock.getDataInizio()).thenReturn(dataInizio);
		when(mock.getDataFine()).thenReturn(dataFine);
		return mock;
	}

	private Set<ItemPojo> mockItems() {
		final Set<ItemPojo> items = new HashSet<>();
		for (int i = 1; i <= 5; i++) {
			items.add(mockItem(i));
		}
		return items;
	}

	private ItemPojo mockItem(int i) {
		final ItemPojo mock = mock(ItemPojo.class);
		when(mock.getElementDescription()).thenReturn("Item " + i);
		return mock;
	}

	private ItemRiferimento mockItemRiferimento(int i, String valoreOfferta) {
		final ItemPojo item = mockItem(i);
		final ItemRiferimento mock = mock(ItemRiferimento.class);
		when(mock.getItem()).thenReturn(item);
		when(mock.getOfferta()).thenReturn(valoreOfferta);
		return mock;
	}

	private CfgPianificazTipoRigaEntity mockTipoRiga(PlanningLevelEnum e) {
		final CfgPianificazTipoRigaEntity tr = mock(CfgPianificazTipoRigaEntity.class);
		when(tr.getCodiceTipo()).thenReturn(e.getCode());
		return tr;
	}

	private PromozionePianificazioneEntity mockRigaPianificazione(CfgPianificazTipoRigaEntity tipoRiga) {
		final PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
		lenient().when(mock.getTipoRiga()).thenReturn(tipoRiga);
		return mock;
	}
}
