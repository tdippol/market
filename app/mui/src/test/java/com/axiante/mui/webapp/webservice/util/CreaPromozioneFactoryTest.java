package com.axiante.mui.webapp.webservice.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.utils.ComboBoxFactory;
import com.axiante.mui.dbpromo.persistence.entities.CanaleLastProgEntity;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgAbilitaMeccCanaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgCanaleNegoziEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiCanaleConsentEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgStatiTransizioniEntity;
import com.axiante.mui.dbpromo.persistence.entities.CreaPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.NegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiConsentitiEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoStatiTransizioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.service.CanalePromozioneService;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.GruppoPromozioniService;
import com.axiante.mui.dbpromo.persistence.service.StatoPromoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.NoResultException;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CreaPromozioneFactoryTest {
	@Mock
	private GruppoPromozioniService gruppoPromozioniService;

	@Mock
	private CanalePromozioneService canalePromozioneService;

	@Mock
	private CreatePromotionService creaPromozioneService;

	@Mock
	private StatoPromoService statoPromoService;

	@SuppressWarnings("unused")
	@Mock
	private ComboBoxFactory comboFactory;

	@Spy
	@InjectMocks
	private CreaPromozioneFactory factory;

	@Test
	public void build_givenInvalidJsonNode_shouldReturnNull() throws IOException {
		assertNull(factory.build(readJson("promo/creaPromoInvalid.json")));
	}

	@Test
	public void build_givenJsonNode_shouldReturnEntity_whenNotManagedException() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001")).thenThrow(RuntimeException.class);
		// Act
		final CreaPromozioneEntity entity = factory.build(jsonNode);
		// Assert
		assertNotNull(entity);
		assertEquals("mauro.giaco", entity.getUserId());
		assertEquals("001", entity.getSlotId());
		verify(creaPromozioneService, times(1)).findByUserIdAndSlotId("mauro.giaco", "001");
		verify(creaPromozioneService, never()).persist(any(CreaPromozioneEntity.class), eq("mauro.giaco"));
	}

	@Test
	public void build_givenJsonNode_shouldReturnEntity() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final GruppoPromozioneEntity gruppoPromozioneEntity = spy(GruppoPromozioneEntity.class);
		gruppoPromozioneEntity.setId(2L);
		gruppoPromozioneEntity.setDescrizione("GRUPPO PROMO");
		final CanalePromozioneEntity canalePromozioneEntity = spy(CanalePromozioneEntity.class);
		canalePromozioneEntity.setId(1L);
		canalePromozioneEntity.setDescrizione("CANALE PROMO");
		when(gruppoPromozioniService.findById(2L)).thenReturn(gruppoPromozioneEntity);
		when(canalePromozioneService.findById(1L)).thenReturn(canalePromozioneEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001")).thenThrow(NoResultException.class);
		// Act
		final CreaPromozioneEntity entity = factory.build(jsonNode);
		// Assert
		assertNotNull(entity);
		assertEquals("PROMO 42", entity.getDescrizione());
		assertEquals(1, (long) entity.getCanalePromozioneEntity().getId());
		assertEquals("CANALE PROMO", entity.getCanalePromozioneEntity().getDescrizione());
		assertEquals("001", entity.getSlotId());
		assertEquals("mauro.giaco", entity.getUserId());
		assertEquals(2, (long) entity.getGruppoPromozioneEntity().getId());
		assertEquals("GRUPPO PROMO", entity.getGruppoPromozioneEntity().getDescrizione());
		assertEquals("2021", entity.getAnno());
		assertEquals(new GregorianCalendar(2021, Calendar.JULY, 1).getTime(), entity.getDataInizio());
		assertEquals(new GregorianCalendar(2021, Calendar.DECEMBER, 31).getTime(), entity.getDataFine());
	}

	@Test
	public void build_givenJsonNodeAndExistingEntity_shouldReturnEntity() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final GruppoPromozioneEntity gruppoPromozioneEntity = spy(GruppoPromozioneEntity.class);
		gruppoPromozioneEntity.setId(2L);
		gruppoPromozioneEntity.setDescrizione("GRUPPO PROMO");
		final CanalePromozioneEntity canalePromozioneEntity = spy(CanalePromozioneEntity.class);
		canalePromozioneEntity.setId(1L);
		canalePromozioneEntity.setDescrizione("CANALE PROMO");
		final CreaPromozioneEntity creaPromoEntity = spy(CreaPromozioneEntity.class);
		creaPromoEntity.setSlotId("001");
		creaPromoEntity.setUserId("mauro.giaco");
		when(gruppoPromozioniService.findById(2L)).thenReturn(gruppoPromozioneEntity);
		when(canalePromozioneService.findById(1L)).thenReturn(canalePromozioneEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001")).thenReturn(creaPromoEntity);
		// Act
		final CreaPromozioneEntity entity = factory.build(jsonNode);
		// Assert
		assertNotNull(entity);
		assertEquals("PROMO 42", entity.getDescrizione());
		assertEquals(1, (long) entity.getCanalePromozioneEntity().getId());
		assertEquals("CANALE PROMO", entity.getCanalePromozioneEntity().getDescrizione());
		assertEquals("001", entity.getSlotId());
		assertEquals("mauro.giaco", entity.getUserId());
		assertEquals(2, (long) entity.getGruppoPromozioneEntity().getId());
		assertEquals("GRUPPO PROMO", entity.getGruppoPromozioneEntity().getDescrizione());
		assertEquals("2021", entity.getAnno());
		assertEquals(new GregorianCalendar(2021, Calendar.JULY, 1).getTime(), entity.getDataInizio());
		assertEquals(new GregorianCalendar(2021, Calendar.DECEMBER, 31).getTime(), entity.getDataFine());
	}

	@Test(expected = NullPointerException.class)
	public void build_givenNullTestata_shouldThrowException() {
		final JsonNode jsonNode = mock(JsonNode.class);
		factory.build(null, jsonNode, "junit");
	}

	@Test(expected = NullPointerException.class)
	public void build_givenNullJsonNode_shouldThrowException() {
		final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
		factory.build(entity, null, "junit");
	}

	@Test(expected = NullPointerException.class)
	public void build_givenNullUser_shouldThrowException() {
		final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
		final JsonNode jsonNode = mock(JsonNode.class);
		factory.build(entity, jsonNode, (String)null);
	}

	@Test
	public void build_givenTestataJsonNodeUser_shouldReturnEntity() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(statoPromoService.findByCode("10"))
		.thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		assertEquals("2021_24", promoTestataEntity.getCodicePromozione());
		final Set<PromozioneStatoEntity> promozioneStatoEntities = promoTestataEntity.getPromozioneStatoEntities();
		assertNotNull(promozioneStatoEntities);
		assertFalse(promozioneStatoEntities.isEmpty());
		final PromozioneStatoEntity statoEntity = promozioneStatoEntities.stream().findFirst().orElse(null);
		assertNotNull(statoEntity);
		assertEquals("10", statoEntity.getStatoPromozioneEntity().getCodiceStato());
	}

	@Test
	public void build_givenTestataJsonNodeUserAndGroup_shouldReturnEntity() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(statoPromoService.findByCode("10"))
		.thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		assertEquals("2021_24", promoTestataEntity.getCodicePromozione());
		final Set<PromozioneStatoEntity> promozioneStatoEntities = promoTestataEntity.getPromozioneStatoEntities();
		assertNotNull(promozioneStatoEntities);
		assertFalse(promozioneStatoEntities.isEmpty());
		final PromozioneStatoEntity statoEntity = promozioneStatoEntities.stream().findFirst().orElse(null);
		assertNotNull(statoEntity);
		assertEquals("10", statoEntity.getStatoPromozioneEntity().getCodiceStato());
	}

	@Test
	public void build_givenTestataJsonNodeUser_shouldReturnEntityWithEmptyStato_whenCodiceStatoIsInvalid() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(statoPromoService.findByCode(anyString()))
		.thenReturn(null);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		final Set<PromozioneStatoEntity> promozioneStatoEntities = promoTestataEntity.getPromozioneStatoEntities();
		assertNotNull(promozioneStatoEntities);
		assertFalse(promozioneStatoEntities.isEmpty());
		final PromozioneStatoEntity statoEntity = promozioneStatoEntities.stream().findFirst().orElse(null);
		assertNull(statoEntity.getStatoPromozioneEntity());
	}

	@Test(expected = NullPointerException.class)
	public void build_givenTestataJsonNodeUser_shouldThrowException_whenCannotCalculareCodicePromozione() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(statoPromoService.findByCode(anyString()))
		.thenReturn(null);
		when(creaPromozioneService.persistCanaleLastProgEntity(any(CanaleLastProgEntity.class), anyString()))
		.thenThrow(RuntimeException.class);
		// Act
		factory.build(entity, jsonNode, "junit");
	}

	@Test
	public void build_givenTestataWithCanaliJsonNodeUser_shouldReturnEntityWithShop() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		final List<CfgCanaleNegoziEntity> canaleNegoziEntities = new ArrayList<>();
		IntStream.range(1, 6).forEach(i -> {
			final CfgCanaleNegoziEntity en = new CfgCanaleNegoziEntity();
			en.setId((long) i);
			en.setMuiCanalePromozione(canalePromoEntity);
			en.setNegozioEntity(withNegozio(i));
			canaleNegoziEntities.add(en);
		});
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(creaPromozioneService.findChannelShopsByChannel(any(CanalePromozioneEntity.class)))
		.thenReturn(canaleNegoziEntities);
		when(statoPromoService.findByCode("10"))
		.thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		final Set<PromozioneNegozioEntity> promoNegoziEntities = promoTestataEntity.getPromozioneNegozioEntities();
		assertNotNull(promoNegoziEntities);
		assertFalse(promoNegoziEntities.isEmpty());
	}

	@Test
	public void build_givenTestataWithCanaliJsonNodeUser_shouldReturnEntityWithoutShop_whenThereIsException() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(creaPromozioneService.findChannelShopsByChannel(any(CanalePromozioneEntity.class)))
		.thenThrow(RuntimeException.class);
		when(statoPromoService.findByCode("10"))
		.thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		assertNull(promoTestataEntity.getPromozioneNegozioEntities());
	}

	@Test
	public void build_givenTestataWithMeccanicheJsonNodeUser_shouldReturnEntityWithMeccaniche() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		final List<CfgAbilitaMeccCanaleEntity> meccanicheEntities = new ArrayList<>();
		IntStream.range(1, 6).forEach(i -> {
			final CfgAbilitaMeccCanaleEntity en = new CfgAbilitaMeccCanaleEntity();
			en.setId((long) i);
			meccanicheEntities.add(en);
		});
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(creaPromozioneService.findEnabledChannelMechanicByChannel(any(CanalePromozioneEntity.class)))
		.thenReturn(meccanicheEntities);
		when(statoPromoService.findByCode("10"))
		.thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		final Set<PromozioneMeccanicheEntity> promoMeccanicheEntities = promoTestataEntity.getPromozioneMeccanicheEntities();
		assertNotNull(promoMeccanicheEntities);
		assertFalse(promoMeccanicheEntities.isEmpty());
	}

	@Test
	public void build_givenTestataWithMeccanicheJsonNodeUser_shouldReturnEntityWithoutMeccaniche_whenThereIsException() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(creaPromozioneService.findEnabledChannelMechanicByChannel(any(CanalePromozioneEntity.class)))
		.thenThrow(RuntimeException.class);
		when(statoPromoService.findByCode("10"))
		.thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		assertNull(promoTestataEntity.getPromozioneMeccanicheEntities());
	}

	@Test
	public void build_givenTestataJsonNodeUser_shouldReturnEntityWithStatiConsensiti_whenThereIsSetPianificazione() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		final CfgSetPianificazioneEntity setPianificazioneEntity = mock(CfgSetPianificazioneEntity.class);
		final List<CfgStatiCanaleConsentEntity> statiConsentitiEntities = new ArrayList<>();
		IntStream.range(1, 3).forEach(i -> {
			final CfgStatiCanaleConsentEntity en = new CfgStatiCanaleConsentEntity();
			en.setId((long) i);
			statiConsentitiEntities.add(en);
		});
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(creaPromozioneService.findByLockedAndDataInizio(any(Date.class)))
		.thenReturn(setPianificazioneEntity);
		when(creaPromozioneService.findAllPromoAllowedStateByChannel(any(CanalePromozioneEntity.class)))
		.thenReturn(statiConsentitiEntities);
		when(statoPromoService.findByCode("10"))
		.thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		final Set<PromoStatiConsentitiEntity> entities = promoTestataEntity.getPromoStatiConsentitiEntities();
		assertNotNull(entities);
		assertFalse(entities.isEmpty());
	}

	@Test
	public void build_givenTestataJsonNodeUser_shouldReturnEntityWithoutStatiConsensiti_whenThereIsException() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		final CfgSetPianificazioneEntity setPianificazioneEntity = mock(CfgSetPianificazioneEntity.class);
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(creaPromozioneService.findByLockedAndDataInizio(any(Date.class)))
		.thenReturn(setPianificazioneEntity);
		when(creaPromozioneService.findAllPromoAllowedStateByChannel(any(CanalePromozioneEntity.class)))
		.thenThrow(RuntimeException.class);
		when(statoPromoService.findByCode("10"))
		.thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		assertNull(promoTestataEntity.getPromoStatiConsentitiEntities());
	}

	@Test
	public void build_givenTestataJsonNodeUser_shouldReturnEntityWithStatiTransizione_whenThereIsSetPianificazione()
			throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		final CfgSetPianificazioneEntity setPianificazioneEntity = mock(CfgSetPianificazioneEntity.class);
		final List<CfgStatiTransizioniEntity> statiTransizioniEntities = new ArrayList<>();
		IntStream.rangeClosed(1, 3).forEach(i -> {
			final CfgStatiTransizioniEntity en = new CfgStatiTransizioniEntity();
			en.setId((long) i);
			if (i == 1) {
				en.setFlagAutomatico(true);
			}
			statiTransizioniEntities.add(en);
		});
		when(canalePromozioneService.findById(anyLong())).thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001")).thenReturn(creaPromoEntity);
		when(creaPromozioneService.findByLockedAndDataInizio(any(Date.class))).thenReturn(setPianificazioneEntity);
		when(creaPromozioneService.findAllPromoTransitionByChannel(any(CanalePromozioneEntity.class)))
				.thenReturn(statiTransizioniEntities);
		when(statoPromoService.findByCode("10")).thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		final Set<PromoStatiTransizioneEntity> entities = promoTestataEntity.getPromoStatiTransizioneEntities();
		assertNotNull(entities);
		assertEquals(3, entities.size());
		final List<Boolean> flags = entities.stream().map(PromoStatiTransizioneEntity::getFlagAutomatico)
				.filter(Objects::nonNull).collect(Collectors.toList());
		assertEquals(1, flags.size());
		assertTrue(flags.get(0));
	}

	@Test
	public void build_givenTestataJsonNodeUser_shouldReturnEntityWithoutStatiTransizione_whenThereIsException() throws Exception {
		// Arrange
		final JsonNode jsonNode = readJson("promo/creaPromo.json");
		final PromozioneTestataEntity entity = mockPromoTestata();
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final CanalePromozioneEntity canalePromoEntity = withCanale();
		final StatoPromozioneEntity statoPromoEntity = mockStatoPromo();
		final CfgSetPianificazioneEntity setPianificazioneEntity = mock(CfgSetPianificazioneEntity.class);
		when(canalePromozioneService.findById(anyLong()))
		.thenReturn(canalePromoEntity);
		when(creaPromozioneService.findByUserIdAndSlotId("mauro.giaco", "001"))
		.thenReturn(creaPromoEntity);
		when(creaPromozioneService.findByLockedAndDataInizio(any(Date.class)))
		.thenReturn(setPianificazioneEntity);
		when(creaPromozioneService.findAllPromoTransitionByChannel(any(CanalePromozioneEntity.class)))
		.thenThrow(RuntimeException.class);
		when(statoPromoService.findByCode("10"))
		.thenReturn(statoPromoEntity);
		// Act
		final PromozioneTestataEntity promoTestataEntity = factory.build(entity, jsonNode, "junit");
		// Assert
		assertNotNull(promoTestataEntity);
		assertNull(promoTestataEntity.getPromoStatiTransizioneEntities());
	}

	@Test
	public void serialize_givenValidPromozione_shouldReturnJsonNode() {
		// Arrange
		final CreaPromozioneEntity creaPromoEntity = mockCreaPromo();
		final List<GruppoPromozioneEntity> gruppiPromEntities = mockGruppiPromo();
		// Act & Assert
		assertNotNull(factory.serialize(creaPromoEntity, gruppiPromEntities, null));
	}

	@Test
	public void serialize_givenValidFullPromozione_shouldReturnJsonNode() {
		// Arrange
		final int currYear = Calendar.getInstance().get(Calendar.YEAR);
		final CreaPromozioneEntity creaPromoEntity = mockCreaFullPromo(currYear);
		final List<GruppoPromozioneEntity> gruppiPromEntities = mockGruppiPromo();
		// Act
		final ObjectNode node = factory.serialize(creaPromoEntity, gruppiPromEntities, null);
		// Assert
		assertNotNull(node);
		final DocumentContext ctx = JsonPath.parse(node.toString());
		assertEquals("MESSAGE", ctx.read("$.messaggio.value", String.class));
		assertEquals("001", ctx.read("$.slotId.value", String.class));
		assertEquals("mauro.giaco", ctx.read("$.userId.value", String.class));
		assertEquals("FOO", ctx.read("$.descrizione.value", String.class));
		assertEquals("1", ctx.read("$.gruppo.value", String.class));
		assertEquals(String.valueOf(currYear), ctx.read("$.anno.value", String.class));
		assertEquals(String.valueOf(currYear), ctx.read("$.anno.picklistValues[0]", String.class));
		assertEquals(String.valueOf(currYear + 1), ctx.read("$.anno.picklistValues[1]", String.class));
		assertTrue(ctx.read("$.anno.picklistValues[2]", String.class).isEmpty());
	}

	@Test
	public void createRowData_givenValidParameters_shouldReturnJsonStringWithValues() throws Exception {
		// Arrange
		final int currYear = Calendar.getInstance().get(Calendar.YEAR);
		final List<CreaPromozioneEntity> promoEntities = Arrays.asList(mockCreaFullPromo(currYear), mockCreaPromo());
		// Act
		final String data = factory.createRowData("2", promoEntities, "junit", null, null);
		// Assert
		assertNotNull(data);
		assertFalse(data.isEmpty());
		assertFalse(((JSONArray) JsonPath.read(data, "$.rowData[0].*.value")).isEmpty());
	}

	@Test
	public void createRowData_givenInvalidSlot_shouldReturnNull() throws Exception {
		// Arrange
		final int currYear = Calendar.getInstance().get(Calendar.YEAR);
		final List<CreaPromozioneEntity> promoEntities = Arrays.asList(mockCreaFullPromo(currYear), mockCreaPromo());
		// Act
		String data = factory.createRowData(null, promoEntities, "junit", null, null);
		// Assert
		assertNull(data);

		// Act
		data = factory.createRowData("", promoEntities, "junit", null, null);
		// Assert
		assertNull(data);
	}

	@Test
	public void createRowData_givenInvalidPromo_shouldReturnJsonStringWithoutValues() throws Exception {
		// Arrange
		final CreaPromozioneEntity promoEntity = mockCreaPromo();
		promoEntity.setSlotId("foo");
		// Act
		final String data = factory.createRowData("2", Collections.singletonList(promoEntity),
				"junit", null, null);
		// Assert
		assertNotNull(data);
		// The row data has only values for userId and slotId
		assertEquals(2, ((JSONArray) JsonPath.read(data, "$.rowData[0].*.value")).size());
	}

	@Test
	public void isRowNodeEmpty_givenEmptyNode_shouldReturnTrue() throws IOException {
		final JsonNode node = readJson("promo/creaPromoEmpty.json");
		assertTrue(factory.isRowNodeEmpty(node));
	}

	@Test
	public void isRowNodeEmpty_givenNotEmptyNode_shouldReturnFalse() throws IOException {
		final JsonNode node = readJson("promo/creaPromo.json");
		assertFalse(factory.isRowNodeEmpty(node));
	}

	@Test
	public void getEmptyCells_givenJsonNode_shouldReturnSizeOfEmptyCells() throws IOException {
		final JsonNode node = readJson("promo/creaPromo.json");
		assertTrue(factory.getEmptyCells(node, true).isEmpty());
	}

	@Test
	public void isAllCellsPopulated_givenPopulatedJsonNode_shouldReturnTrue() throws IOException {
		final JsonNode node = readJson("promo/creaPromo.json");
		assertTrue(factory.isAllCellsPopulated(node));
	}

	@Test
	public void isAllCellsPopulated_givenEmptyJsonNode_shouldReturnFalse() throws IOException {
		final JsonNode node = readJson("promo/creaPromoEmpty.json");
		assertFalse(factory.isAllCellsPopulated(node));
	}

	@Test(expected = NullPointerException.class)
	public void deleteRow_givenNullJsonNode_shouldThrowException() throws Exception {
		factory.deleteRow(null, "junit");
	}

	@Test(expected = NullPointerException.class)
	public void deleteRow_givenNullUser_shouldThrowException() throws Exception {
		factory.deleteRow(mock(JsonNode.class), null);
	}

	@Test
	public void deleteRow_givenJsonNode_shouldDeleteRow() throws Exception {
		final JsonNode node = readJson("promo/creaPromo.json");
		factory.deleteRow(node, "junit");
	}

	private CreaPromozioneEntity mockCreaFullPromo(int year) {
		final CreaPromozioneEntity entity = mockCreaPromo();
		entity.setAnno(String.valueOf(year));
		entity.setCodiceUtenteAggiornamento("foo");
		entity.setCodiceUtenteInserimento("bar");
		entity.setDataAggiornamento(new Date());
		entity.setDataInserimento(new GregorianCalendar(2021, Calendar.FEBRUARY, 22).getTime());
		entity.setDataInizio(new GregorianCalendar(2021, Calendar.JANUARY, 1).getTime());
		entity.setDataFine(new GregorianCalendar(2021, Calendar.JUNE, 30).getTime());
		entity.setDescrizione("FOO");
		entity.setGruppoPromozioneEntity(mockGruppiPromo().get(0));
		entity.setMessaggio("MESSAGE");
		entity.setPromozioneTestataEntities(Collections.singleton(mockPromoTestata()));
		return entity;
	}

	private List<GruppoPromozioneEntity> mockGruppiPromo() {
		List<GruppoPromozioneEntity> entities = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			final GruppoPromozioneEntity entity = spy(GruppoPromozioneEntity.class);
			entity.setId((long) i);
			entity.setDescrizione("Gruppo " + i);
			entity.setMuiCanalePromoziones(Collections.singleton(withCanale()));
			entities.add(entity);
		}
		return entities;
	}

	private JsonNode readJson(String filename) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final ClassLoader classLoader = getClass().getClassLoader();
		final File file = new File(classLoader.getResource(filename).getFile());
		return mapper.readTree(file);
	}

	private CreaPromozioneEntity mockCreaPromo() {
		final CreaPromozioneEntity entity = spy(CreaPromozioneEntity.class);
		entity.setSlotId("001");
		entity.setUserId("mauro.giaco");
		entity.setCanalePromozioneEntity(withCanale());
		return entity;
	}

	private PromozioneTestataEntity mockPromoTestata() {
		final PromozioneTestataEntity entity = spy(PromozioneTestataEntity.class);
		entity.setAnno("2021");
		entity.setDescrizione("TESTATA JUNIT");
		entity.setMuiCanalePromozione(withCanale());
		return entity;
	}

	private StatoPromozioneEntity mockStatoPromo() {
		final StatoPromozioneEntity entity = spy(StatoPromozioneEntity.class);
		entity.setCodiceStato("10");
		entity.setDescrizione("Non pubblicata");
		return entity;
	}

	private CanalePromozioneEntity withCanale() {
		final CanalePromozioneEntity canalePromoEntity = spy(CanalePromozioneEntity.class);
		canalePromoEntity.setCodeRangeMin(23L);
		canalePromoEntity.setCodeRangeMax(42L);
		return canalePromoEntity;
	}

	private NegozioEntity withNegozio(int id) {
		final NegozioEntity entity = new NegozioEntity();
		entity.setId((long) id);
		entity.setDescrizione("Negozio " + id);
		return entity;
	}
}
