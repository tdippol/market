package com.axiante.mui.webapp.webservice.util.pianificazione.helper;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasNoJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.enumeration.ElementType;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneCampiEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.ItemEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.GrmService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.RepartoService;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.inject.Instance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PromoConfigurationHelperTest {

	@Mock
	private PianificazioneService pianificazioneService;

	@Mock
	private Instance<PianificazioneService> pianificazioneServiceInstance;

	@Mock
	private ItemService itemService;
	
	@Mock
	private Instance<ItemService> itemServiceInstance;

	@Mock
	private GrmService grmService;

	@Mock
	private Instance<GrmService> grmServiceInstance;
	
	@Mock
	private RepartoService repartoService;
	
	@Mock
	private Instance<RepartoService> repartoServiceInstance;

	@Mock
	private Instance<PromoService> promoServiceInstance;

	@Mock
	private PromoService promoService;

	@Mock
	private Instance<CfgConfHeaderService> confHeaderServiceInstance;

	@Mock
	private CfgConfHeaderService confHeaderService;

	@Mock
	private Instance<PianificazioneHelper> pianificazioneHelperInstance;

	@Mock
	private PianificazioneHelper pianificazioneHelper;

	@Mock
	private Instance<PianificazioneSecurityUtil> securityUtilInstance;

	@Mock
	private PianificazioneSecurityUtil securityUtil;

	@Spy
	@InjectMocks
	private PromoConfigurationHelper helper;

	private PromozioneTestataEntity testata;
	
	@Before
	public void init() {
		testata = mockTestata();
		when(pianificazioneServiceInstance.get()).thenReturn(pianificazioneService);
		when(itemServiceInstance.get()).thenReturn(itemService);
		when(grmServiceInstance.get()).thenReturn(grmService);
		when(repartoServiceInstance.get()).thenReturn(repartoService);
		when(promoServiceInstance.get()).thenReturn(promoService);
		when(confHeaderServiceInstance.get()).thenReturn(confHeaderService);
		when(pianificazioneHelperInstance.get()).thenReturn(pianificazioneHelper);
		when(securityUtilInstance.get()).thenReturn(securityUtil);
		when(promoService.findById(1L)).thenReturn(testata);
	}

	@Test(expected = NullPointerException.class)
	public void validate_givenNullPromozione_shouldThrowException() {
		helper.validate(ElementType.ARTICOLO, "foo", null, 1L, null, null);
	}

	@Test(expected = NullPointerException.class)
	public void validate_givenNullMeccanica_shouldThrowException() {
		helper.validate(ElementType.ARTICOLO, "foo", 1L, null, null, null);
	}

	@Test
	public void validate_givenTypeArticolo_shouldReturnBasicJsonNode_whenCannotFindElement() {
		// Arrange
		when(itemService.findByCode("foo")).thenReturn(null);
		// Act
		final ObjectNode node = helper.validate(ElementType.ARTICOLO, "foo", 1L, 1L, null, null);
		// Assert
		assertNotNull(node);
		assertThat(node.toString(), isJson(allOf(
				withJsonPath("$.codice", equalTo("foo")),
				withJsonPath("$.descrizione", equalTo("")),
				withJsonPath("$.isValid", equalTo(false))
		)));
	}

	@Test
    @SuppressWarnings({ "rawtypes"})
	public void validate_givenTypeArticolo_shouldReturnCompleteJsonNode_whenFoundElement() {
		// Arrange
		final ItemEntity element = mockItemEntity();
		final CfgConfHeaderEntity confHeader = mockCfgConfHeader(true);
		when(itemService.findByCode("foo")).thenReturn(element);
		when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
				.thenReturn(confHeader);
		// Act
		final ObjectNode node = helper.validate(ElementType.ARTICOLO, "foo", 1L, 1L, null, null);
		// Assert
		assertNotNull(node);
		assertThat(node.toString(), isJson(allOf(
				withJsonPath("$.idItem", equalTo(1)),
				withJsonPath("$.codice", equalTo("IT-001")),
				withJsonPath("$.descrizione", equalTo("IT-001 - The first item")),
				withJsonPath("$.isValid", equalTo(true))
		)));
	}

	@Test
    @SuppressWarnings({ "rawtypes"})
	public void validate_givenTypeArticolo_shouldReturnJsonNodeWithIsValidFalse_whenElementIsDuplicatedAndFlagDuplicaIsFalse() {
		// Arrange
		final ItemEntity element = mockItemEntity();
		final CompratoreEntity compratoreEntity = mock(CompratoreEntity.class);
		final CfgConfHeaderEntity confHeader = mockCfgConfHeader(false);
		when(element.getCompratoreEntity()).thenReturn(compratoreEntity);
		when(itemService.findByCode("foo")).thenReturn(element);
		when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
				.thenReturn(confHeader);
		when(pianificazioneHelper.getUsedItems(eq(testata), eq(1L), anyString(), anyString()))
				.thenReturn(Collections.singletonList(1L));
		when(compratoreEntity.getId()).thenReturn(42L);
		// Act
		final ObjectNode node = helper.validate(ElementType.ARTICOLO, "foo", 1L, 1L, null, null);
		// Assert
		assertNotNull(node);
		assertThat(node.toString(), isJson(allOf(
				withJsonPath("$.idItem", equalTo(1)),
				withJsonPath("$.codice", equalTo("IT-001")),
				withJsonPath("$.descrizione", equalTo("elemento duplicato in pianificazione")),
				withJsonPath("$.isValid", equalTo(false)),
				withJsonPath("$.itemBuyerId", equalTo(42))
		)));
	}

	@Test
    @SuppressWarnings({ "rawtypes"})
	public void validate_givenTypeArticolo_shouldReturnJsonNodeWithIsValidTrue_whenElementIsDuplicatedAndFlagDuplicaIsTrue() {
		// Arrange
		final ItemEntity element = mockItemEntity();
		final CompratoreEntity compratoreEntity = mock(CompratoreEntity.class);
		final CfgConfHeaderEntity confHeader = mockCfgConfHeader(true);
		when(element.getCompratoreEntity()).thenReturn(compratoreEntity);
		when(itemService.findByCode("foo")).thenReturn(element);
		when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
				.thenReturn(confHeader);
		when(compratoreEntity.getId()).thenReturn(42L);
		// Act
		final ObjectNode node = helper.validate(ElementType.ARTICOLO, "foo", 1L, 1L, null, null);
		// Assert
		assertNotNull(node);
		assertThat(node.toString(), isJson(allOf(
				withJsonPath("$.idItem", equalTo(1)),
				withJsonPath("$.codice", equalTo("IT-001")),
				withJsonPath("$.descrizione", equalTo("IT-001 - The first item")),
				withJsonPath("$.isValid", equalTo(true)),
				withJsonPath("$.itemBuyerId", equalTo(42))
		)));
	}

	@Test
	@SuppressWarnings({ "rawtypes"})
	public void validate_givenTypeArticolo_shouldReturnJsonNodeWithIsValidFalse_whenElementIsNotWritableForUser() {
		// Arrange
		final ItemEntity element = mockItemEntity();
		final CompratoreEntity compratoreEntity = mock(CompratoreEntity.class);
		when(element.getCompratoreEntity()).thenReturn(compratoreEntity);
		when(itemService.findByCode("foo")).thenReturn(element);
		// Act
		final ObjectNode node = helper.validate(ElementType.ARTICOLO, "foo", 1L, 1L,
				false, Collections.singletonList("FOO"));
		// Assert
		assertNotNull(node);
		assertThat(node.toString(), isJson(allOf(
				hasNoJsonPath("$.idItem"),
				withJsonPath("$.codice", equalTo("foo")),
				withJsonPath("$.descrizione", equalTo("")),
				withJsonPath("$.isValid", equalTo(false)),
				hasNoJsonPath("$.itemBuyerId")
		)));
	}

	@Test
	public void validate_givenTypeGrm_shouldReturnBasicJsonNode() {
		// Arrange
		when(grmService.findByCode("foo")).thenReturn(null);
		// Act
		final ObjectNode node = helper.validate(ElementType.GRM, "foo", 1L, 1L, null, null);
		// Assert
		assertNotNull(node);
		assertThat(node.toString(), isJson(allOf(
				withJsonPath("$.codice", equalTo("foo")),
				withJsonPath("$.descrizione", equalTo("")),
				withJsonPath("$.isValid", equalTo(false))
		)));
	}

	@Test
	public void validate_givenTypeReparto_shouldReturnCompleteJsonNode() {
		// Arrange
		final RepartoEntity element = mockRepartoEntity();
		final CfgConfHeaderEntity confHeader = mockCfgConfHeader(true);
		when(repartoService.findByCode("07")).thenReturn(element);
		when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
				.thenReturn(confHeader);
		// Act
		final ObjectNode node = helper.validate(ElementType.REPARTO, "7", 1L, 1L, null, null);
		// Assert
		assertNotNull(node);
		assertThat(node.toString(), isJson(allOf(
				withJsonPath("$.idItem", equalTo(42)),
				withJsonPath("$.codice", equalTo("7")),
				withJsonPath("$.descrizione", equalTo("7 - Foo Department")),
				withJsonPath("$.isValid", equalTo(true))
		)));
	}

	@Test
	public void testGetConfigurazioniFiltrateReturnsEmptyWhenNoConfiguration() {
		final PromozioneTestataEntity testataMock = mock(PromozioneTestataEntity.class);
		final List<CfgPianificazioneEntity> configurazioniFiltrate = helper.getConfigurazioniFiltrate(testataMock);
		verify(helper, times(1)).getConfigurazioniFiltrate(testataMock);
		assertNotNull(configurazioniFiltrate);
		assertTrue(configurazioniFiltrate.isEmpty());
	}

	@Test
	public void getConfigurazioniFiltrate_givenTestataWithPianificazione_shouldReturnRelatedCfgSorted() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final PromozionePianificazioneEntity pianificazione1 = mock(PromozionePianificazioneEntity.class);
		final PromozionePianificazioneEntity pianificazione2 = mock(PromozionePianificazioneEntity.class);
		final CfgPianificazioneEntity cfgPianificazione1 = mock(CfgPianificazioneEntity.class);
		final CfgPianificazioneEntity cfgPianificazione2 = mock(CfgPianificazioneEntity.class);
		final CfgPianificazioneEntity cfgPianificazione3 = mock(CfgPianificazioneEntity.class);
		final CfgPianificazioneCampiEntity cfgPianificazioneCampo1 = mock(CfgPianificazioneCampiEntity.class);
		final CfgPianificazioneCampiEntity cfgPianificazioneCampo2 = mock(CfgPianificazioneCampiEntity.class);
		final Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
		pianificazioni.add(pianificazione1);
		pianificazioni.add(pianificazione2);
		final List<CfgPianificazioneEntity> cfgPianificazioni = new ArrayList<>();
		cfgPianificazioni.add(cfgPianificazione1);
		cfgPianificazioni.add(cfgPianificazione2);
		cfgPianificazioni.add(cfgPianificazione3);
		when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
		when(pianificazioneService.findAllCfgPianificazioneEntitiesByPromozione(testata)).thenReturn(cfgPianificazioni);
		when(cfgPianificazione1.getMuiCfgPianificazioneCampi()).thenReturn(cfgPianificazioneCampo1);
		when(cfgPianificazione2.getMuiCfgPianificazioneCampi()).thenReturn(cfgPianificazioneCampo2);
		when(cfgPianificazione1.getOrdinamento()).thenReturn(20);
		when(cfgPianificazione2.getOrdinamento()).thenReturn(10);
		when(cfgPianificazioneCampo1.getDescrizione()).thenReturn("CFG_CAMPO1");
		when(cfgPianificazioneCampo2.getDescrizione()).thenReturn("CFG_CAMPO2");
		final List<CfgPianificazioneEntity> list = helper.getConfigurazioniFiltrate(testata);
		assertEquals(2, list.size());
		assertEquals(cfgPianificazione2, list.get(0));
		assertEquals(cfgPianificazione1, list.get(1));
	}

	@Test
	public void testGetConfigurazioniSorted() {
		final PromozioneTestataEntity testataMock = mock(PromozioneTestataEntity.class);
		final CfgPianificazioneEntity configurazioneMock1 = mock(CfgPianificazioneEntity.class);
		final CfgPianificazioneEntity configurazioneMock2 = mock(CfgPianificazioneEntity.class);
		final CfgPianificazioneCampiEntity pianificazioneCampiMock = mock(CfgPianificazioneCampiEntity.class);
		when(pianificazioneCampiMock.getDescrizione()).thenReturn("campo");
		when(configurazioneMock1.getOrdinamento()).thenReturn(2);
		when(configurazioneMock1.getMuiCfgPianificazioneCampi()).thenReturn(pianificazioneCampiMock);
		when(configurazioneMock2.getOrdinamento()).thenReturn(1);
		when(configurazioneMock2.getMuiCfgPianificazioneCampi()).thenReturn(pianificazioneCampiMock);
		final List<CfgPianificazioneEntity> configurazioni = Arrays.asList(configurazioneMock1, configurazioneMock2);
		when(pianificazioneService.findAllCfgPianificazioneEntitiesByPromozione(testataMock))
				.thenReturn(configurazioni);
		final List<CfgPianificazioneEntity> configurazioniSorted = helper.getConfigurazioniSorted(testataMock);
		verify(helper, times(1)).getConfigurazioniSorted(testataMock);
		assertNotNull(configurazioniSorted);
		assertFalse(configurazioniSorted.isEmpty());
		assertEquals(configurazioneMock2, configurazioniSorted.get(0));
		assertEquals(configurazioneMock1, configurazioniSorted.get(1));
	}

	@Test
	public void getConfigurazioniSorted_givenTestataWithoutCfgPianificazioni_shouldReturnEmptyArrayList() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(pianificazioneService.findAllCfgPianificazioneEntitiesByPromozione(testata)).thenReturn(null);
		assertTrue(helper.getConfigurazioniSorted(testata).isEmpty());
	}

	@Test(expected = NullPointerException.class)
	public void getHeaderFromTestataAndMeccanica_givenNullTestata_shouldThrowException() {
		helper.getHeaderFromTestataAndMeccanica(null, mock(MeccanicheEntity.class));
	}

	@Test(expected = NullPointerException.class)
	public void getHeaderFromTestataAndMeccanica_givenNullMeccanica_shouldThrowException() {
		helper.getHeaderFromTestataAndMeccanica(mock(PromozioneTestataEntity.class), null);
	}

	@Test
	public void getHeaderFromTestataAndMeccanica_shouldReturnNull_whenCfgSetPianificazioneHasNotGivenMeccanica() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final MeccanicheEntity meccanica1 = mock(MeccanicheEntity.class);
		final MeccanicheEntity meccanica2 = mock(MeccanicheEntity.class);
		final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
		final CfgConfHeaderEntity cfgHeader = mock(CfgConfHeaderEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeader));
		when(cfgHeader.getMeccanicaEntity()).thenReturn(meccanica2);
		when(meccanica1.getId()).thenReturn(1L);
		when(meccanica2.getId()).thenReturn(2L);
		assertNull(helper.getHeaderFromTestataAndMeccanica(testata, meccanica1));
	}

	@Test
	public void getHeaderFromTestataAndMeccanica_shouldReturnHeader_whenCfgSetPianificazioneHasGivenMeccanica() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final MeccanicheEntity meccanica1 = mock(MeccanicheEntity.class);
		final MeccanicheEntity meccanica2 = mock(MeccanicheEntity.class);
		final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
		final CfgConfHeaderEntity cfgHeader = mock(CfgConfHeaderEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(cfgHeader));
		when(cfgHeader.getMeccanicaEntity()).thenReturn(meccanica2);
		when(meccanica1.getId()).thenReturn(1L);
		when(meccanica2.getId()).thenReturn(1L);
		assertNotNull(helper.getHeaderFromTestataAndMeccanica(testata, meccanica1));
	}

	private CfgConfHeaderEntity mockCfgConfHeader(boolean flag) {
		final CfgConfHeaderEntity confHeader = mock(CfgConfHeaderEntity.class);
		final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
		when(pianificazioneHelper.getDuplicaFlagForHeader(eq(confHeader), any(ElementType.class)))
				.thenReturn(flag);
		when(confHeader.getLivelloPianificazione()).thenReturn(cfgLivello);
		when(cfgLivello.getCodice()).thenReturn("FOO");
		return confHeader;
	}

	private PromozioneTestataEntity mockTestata() {
		final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(cfgSetPianificazione.getId()).thenReturn(1L);
		return testata;
	}

	private ItemEntity mockItemEntity() {
		final ItemEntity mock = mock(ItemEntity.class);
		when(mock.getId()).thenReturn(1L);
		when(mock.getCodiceItem()).thenReturn("IT-001");
		when(mock.getDescrizione()).thenReturn("The first item");
		when(mock.getKey()).thenCallRealMethod();
		when(mock.getLabel()).thenCallRealMethod();
		return mock;
	}

	private RepartoEntity mockRepartoEntity() {
		final RepartoEntity mock = mock(RepartoEntity.class);
		when(mock.getId()).thenReturn(42L);
		when(mock.getCodiceReparto()).thenReturn("7");
		when(mock.getDescrizione()).thenReturn("Foo Department");
		when(mock.getKey()).thenCallRealMethod();
		when(mock.getLabel()).thenCallRealMethod();
		return mock;
	}
}
