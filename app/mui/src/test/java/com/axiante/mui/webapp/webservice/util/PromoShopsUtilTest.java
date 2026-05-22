package com.axiante.mui.webapp.webservice.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.backing.ApplicationProperties;
import com.axiante.mui.common.promo.ShopUpdateTypeEnum;
import com.axiante.mui.common.promo.UserFilterUtils;
import com.axiante.mui.common.promo.params.PromoShopBulkParam;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.business.service.UploadExcelService;
import com.axiante.mui.dbpromo.business.service.impl.data.ShopItemUpload;
import com.axiante.mui.dbpromo.business.utils.pojo.PromoShopUpdateDto;
import com.axiante.mui.dbpromo.business.utils.pojo.PromoShopUpdateLevel;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.NegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoNegozioEntity;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.webapp.business.OwnershipService;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.enterprise.inject.Instance;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class PromoShopsUtilTest {

	private static final UserFilterUtils userFilterUtils = new UserFilterUtils();

	@Mock
	private Instance<OwnershipService> ownershipServiceInstance;

	@Mock
	private OwnershipService ownershipService;

	@Mock
	ApplicationProperties applicationProperties;
	
	@Spy
	@InjectMocks
	public PromoShopsUtil promoShopsUtil;

	@Spy
	PromoService promoService;

	@Spy
	UploadExcelService uploadExcelService;


	private Date today = Date.from(Instant.now());
	private Date yesterday = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
	private Date tomorrow = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

	@Mock
	PromozioneTestataEntity testata1;

	@Mock
	PromozioneTestataEntity testata2;

	@Mock
	TipoNegozioEntity tipoNegozio1;
	
	@Mock
	TipoNegozioEntity tipoNegozio2;

	@Spy
	PromozioneNegozioEntity promoNegozio1;

	@Spy
	PromozioneNegozioEntity promoNegozio2;

	private List<CanalePromozioneEntity> listaCanali = new ArrayList<>();

	private List<PromozioneTestataEntity> testate = new ArrayList<>();

	private Set<PromozioneStatoEntity> promozioneStati = new HashSet<>();

	private List<TipoNegozioEntity> tipiNegozi = new ArrayList<>();
	
	private static final String dbFiltersJson = "{ " + "\"glossary\": { " + "\"title\": \"example glossary\", "
			+ "\"GlossDiv\": { " + "\"title\": \"S\", " + "\"GlossList\": { " + "\"selectedValues\": { "
			+ "\"ID\": \"SGML\", " + "\"SortAs\": \"SGML\", "
			+ "\"GlossTerm\": \"Standard Generalized Markup Language\", " + "\"Acronym\": \"SGML\", "
			+ "\"Abbrev\": \"ISO 8879:1986\", " + "\"Attribute\": { "
			+ " \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\", "
			+ "\"GlossSeeAlso\": [\"GML\", \"XML\"] " + "}, " + "\"GlossSee\": \"markup\" " + "} " + "} " + "} " + "} "
			+ "} ";
	
	private Map<String, String> userFiltersMap= new TreeMap<>();

	@Before
	public void setUp() {
		listaCanali.clear();
		testate.clear();
		promozioneStati.clear();
		tipiNegozi.clear();
		PromozioneStatoEntity promoStato1 = mock(PromozioneStatoEntity.class);
		StatoPromozioneEntity statoPromo1 = mock(StatoPromozioneEntity.class);
		when(statoPromo1.getCodiceStato()).thenReturn("30");
		// questo stato e' attivo
		when(promoStato1.getDataFineStato()).thenReturn(null);
		when(promoStato1.getStatoPromozioneEntity()).thenReturn(statoPromo1);
		promozioneStati.add(promoStato1);

		PromozioneStatoEntity promoStato2 = mock(PromozioneStatoEntity.class);
		when(promoStato2.getDataFineStato()).thenReturn(yesterday);
		promozioneStati.add(promoStato2);

		PromozioneStatoEntity promoStato3 = mock(PromozioneStatoEntity.class);
		when(promoStato3.getDataFineStato()).thenReturn(today);
		promozioneStati.add(promoStato3);

		Set<PromozioneNegozioEntity> promoNegozi = new HashSet<>();
		promoNegozio1 = mock(PromozioneNegozioEntity.class);
		when(promoNegozio1.getDefaultFlag()).thenReturn("true");
		when(promoNegozio1.getSelezioneFlag()).thenReturn("false");
		when(promoNegozio1.getDataInizio()).thenReturn(today);
		when(promoNegozio1.getDataFine()).thenReturn(tomorrow);
		NegozioEntity negozio1 = mock(NegozioEntity.class);
		when(negozio1.getCodiceNegozio()).thenReturn("1");
		when(negozio1.getSigla()).thenReturn("Sigla1");
		when(negozio1.getTipoNegozioEntity()).thenReturn(tipoNegozio1);
		when(negozio1.getTipoConsegna()).thenReturn("FOO");
		when(negozio1.getSocieta()).thenReturn("1");
		when(negozio1.getCodiceZona()).thenReturn("01");
		when(promoNegozio1.getNegozioEntity()).thenReturn(negozio1);
		when(tipoNegozio1.getId()).thenReturn((long) 1);

		promoNegozio2 = mock(PromozioneNegozioEntity.class);
		when(promoNegozio2.getDefaultFlag()).thenReturn("false");
		when(promoNegozio2.getSelezioneFlag()).thenReturn("true");
		when(promoNegozio2.getDataInizio()).thenReturn(yesterday);
		when(promoNegozio2.getDataFine()).thenReturn(tomorrow);
		NegozioEntity negozio2 = mock(NegozioEntity.class);
		when(negozio2.getCodiceNegozio()).thenReturn("2");
		when(negozio2.getSigla()).thenReturn("Sigla2");
		when(negozio2.getTipoNegozioEntity()).thenReturn(tipoNegozio2);
		when(negozio2.getTipoConsegna()).thenReturn("BAR");
		when(negozio2.getSocieta()).thenReturn("2");
		when(negozio2.getCodiceZona()).thenReturn("02");
		when(promoNegozio2.getNegozioEntity()).thenReturn(negozio2);

		when(testata1.getPromozioneStatoEntities()).thenReturn(promozioneStati);
		when(testata1.getDataInizio()).thenReturn(today);
		when(testata1.getDataFine()).thenReturn(tomorrow);
		
		when(testata2.getPromozioneStatoEntities()).thenReturn(promozioneStati);
		when(testata2.getDataInizio()).thenReturn(yesterday);
		when(testata2.getDataFine()).thenReturn(today);
		
		when(promoNegozio1.getPromozioneTestataEntity()).thenReturn(testata1);
		when(promoNegozio2.getPromozioneTestataEntity()).thenReturn(testata2);
		promoNegozi.add(promoNegozio1);
		promoNegozi.add(promoNegozio2);
		when(testata1.getPromozioneNegozioEntities()).thenReturn(promoNegozi);
		when(testata2.getPromozioneNegozioEntities()).thenReturn(promoNegozi);
		testate.add(testata1);
		testate.add(testata2);

		CanalePromozioneEntity canale1 = mock(CanalePromozioneEntity.class);
		CanalePromozioneEntity canale2 = mock(CanalePromozioneEntity.class);
		listaCanali.add(canale1);
		listaCanali.add(canale2);
		when(tipoNegozio1.getId()).thenReturn((long) 1);
		when(tipoNegozio2.getId()).thenReturn((long) 2);
		tipiNegozi.add(tipoNegozio1);
		tipiNegozi.add(tipoNegozio2);
		userFiltersMap = userFilterUtils.createUserFiltersMapToQueryString(dbFiltersJson);
		lenient().when(ownershipServiceInstance.get()).thenReturn(ownershipService);
	}

	@Test
	public void testStreamRowDataWithPromoSelected(){
		when(promoShopsUtil.streamRowData("1", dbFiltersJson, listaCanali, null, true)).thenCallRealMethod();
		when(promoService.findById(anyLong())).thenReturn(testata1);
		StreamingOutput out = promoShopsUtil.streamRowData("1", dbFiltersJson, listaCanali, null, true);
		try {
			out.write(System.out);
		}catch(WebApplicationException e){
			log.error("WebApplicationException error", e);
		} catch (IOException e) {
			log.error("IOException error", e);
		}
		assertNotNull(promoShopsUtil.streamRowData("1", dbFiltersJson, listaCanali, null, true));
	}
	
	@Test
	public void testStreamRowDataWithoutPromoSelected() throws Exception{
		when(promoShopsUtil.streamRowData(null, dbFiltersJson, listaCanali, null, true)).thenCallRealMethod();
		when(promoService.findAllNotCancelled(userFiltersMap, listaCanali)).thenReturn(testate);
		StreamingOutput out = promoShopsUtil.streamRowData(null, dbFiltersJson, listaCanali, null, true);
		try {
			out.write(System.out);
		}catch(WebApplicationException e){
			log.error("WebApplicationException error", e);
		} catch (IOException e) {
			log.error("IOException error", e);
		}
		assertNotNull(promoShopsUtil.streamRowData(null, dbFiltersJson, listaCanali, null, true));
	}

	@Test
	public void testPopulateRowData() {
		when(promoService.findById(anyLong())).thenReturn(testata1);
		assertNotNull(promoShopsUtil.populateRowData("1", dbFiltersJson, listaCanali, null, true));
	}

	@Test
	public void testPopulateRowDataWithPromoSelectedNULL() {
		assertNotNull(promoShopsUtil.populateRowData(null, dbFiltersJson, listaCanali, null, true));
	}

	@Test
	public void validateUpdatedRowReturnsFalseWhenWrongJson() throws IOException {
		String json = "{\"dataInizio\":\"01.01.2021\",\"dataFine\":\"30.10.2021\",\"promoHeaderId\":\"100\"}";
		JsonNode node = JsonUtils.getMapper().readTree(json);
		assertFalse(promoShopsUtil.validateUpdatedRow(node));
	}
	
	@Test
	public void validateUpdatedRowReturnsFalseWhenDataInizioBiggerThanDataFine() throws IOException {
		String json = "{\"dataInizio\": {\n"
				+ "    \"value\":44408.0 ,\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "  \"dataFine\": {\n"
				+ "    \"value\": 44393,\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "\"promoHeaderId\": {\n"
				+ "    \"value\": \"2\"\n }}";
		JsonNode node = JsonUtils.getMapper().readTree(json);
		when(promoService.findById(anyLong())).thenReturn(testata1);
		assertFalse(promoShopsUtil.validateUpdatedRow(node));
	}

	@Test
	public void validateUpdatedRowReturnsFalseWhenDataInizioSmallerThanDataFine() throws IOException {
		String json = "{\"dataInizio\": {\n"
				+ "    \"value\": 44408.0,\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "  \"dataFine\": {\n"
				+ "    \"value\": 44593,\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "\"promoHeaderId\": {\n"
				+ "    \"value\": \"2\"\n }}";
		JsonNode node = JsonUtils.getMapper().readTree(json);
		when(promoService.findById(anyLong())).thenReturn(testata1);
		assertFalse(promoShopsUtil.validateUpdatedRow(node));
	}
	
	@Test
	public void validateUpdatedRowReturnsFalseWhenPromozioneTestataError() throws IOException {
		String json = "{\"dataInizio\": {\n"
				+ "    \"value\": 44408.0,\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "  \"dataFine\": {\n"
				+ "    \"value\": 44593,\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "\"promoHeaderId\": {\n"
				+ "    \"value\": \"2\"\n }}";
		JsonNode node = JsonUtils.getMapper().readTree(json);
		assertFalse(promoShopsUtil.validateUpdatedRow(node));
	}

	@Test
	public void validateUpdatedRowTrueWhenConditionsOK() throws IOException {
		String json = "{\"dataInizio\": {\n"
				+ "    \"value\": 44393,\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "  \"dataFine\": {\n"
				+ "    \"value\": \"44408.0\",\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "\"promoHeaderId\": {\n"
				+ "    \"value\": \"2\"\n }}";
		JsonNode node = JsonUtils.getMapper().readTree(json);
		when(promoService.findById(anyLong())).thenReturn(testata1);
		Calendar inizioCalendar = new GregorianCalendar(2021, Calendar.JULY, 10);
		Date testataDataInizio = inizioCalendar.getTime();
		Calendar fineCalendar = new GregorianCalendar(2021, Calendar.AUGUST, 15);
		Date testataDataFine = fineCalendar.getTime();
		when(testata1.getDataInizio()).thenReturn(testataDataInizio);
		when(testata1.getDataFine()).thenReturn(testataDataFine);
		assertTrue(promoShopsUtil.validateUpdatedRow(node));
	}

	@Test
	public void validateUpdatedRowTrueWhenConditionsAndCodiceMeccanicaOK() throws IOException {
		String json = "{\"dataInizio\": {\n"
				+ "    \"value\": 44393,\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "  \"dataFine\": {\n"
				+ "    \"value\": \"44408.0\",\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "  \"codiceMeccanica\": {\n"
				+ "    \"value\": \"M800\",\n"
				+ "    \"type\": \"picklist\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "\"promoHeaderId\": {\n"
				+ "    \"value\": \"2\"\n }}";
		JsonNode node = JsonUtils.getMapper().readTree(json);
		when(promoService.findById(anyLong())).thenReturn(testata1);
		Calendar inizioCalendar = new GregorianCalendar(2021, Calendar.JULY, 10);
		Date testataDataInizio = inizioCalendar.getTime();
		Calendar fineCalendar = new GregorianCalendar(2021, Calendar.AUGUST, 15);
		Date testataDataFine = fineCalendar.getTime();
		when(testata1.getDataInizio()).thenReturn(testataDataInizio);
		when(testata1.getDataFine()).thenReturn(testataDataFine);
		assertTrue(promoShopsUtil.validateUpdatedRow(node));
	}

	@Test
	public void validateUpdatedRowFalseWhenWrongCodiceMeccanica() throws IOException {
		String json = "{\"dataInizio\": {\n"
				+ "    \"value\": 44393,\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "  \"dataFine\": {\n"
				+ "    \"value\": \"44408.0\",\n"
				+ "    \"type\": \"date\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"dataTypeParams\": {\n"
				+ "      \"dateFormat\": \"DDD dd/mm/yyyy\"\n"
				+ "    },\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "  \"codiceMeccanica\": {\n"
				+ "    \"value\": \"M666\",\n"
				+ "    \"type\": \"picklist\",\n"
				+ "    \"editable\": true,\n"
				+ "    \"nullable\": true\n"
				+ "  },"
				+ "\"promoHeaderId\": {\n"
				+ "    \"value\": \"2\"\n }}";
		JsonNode node = JsonUtils.getMapper().readTree(json);
		when(promoService.findById(anyLong())).thenReturn(testata1);
		Calendar inizioCalendar = new GregorianCalendar(2021, Calendar.JULY, 10);
		Date testataDataInizio = inizioCalendar.getTime();
		Calendar fineCalendar = new GregorianCalendar(2021, Calendar.AUGUST, 15);
		Date testataDataFine = fineCalendar.getTime();
		when(testata1.getDataInizio()).thenReturn(testataDataInizio);
		when(testata1.getDataFine()).thenReturn(testataDataFine);
		assertFalse(promoShopsUtil.validateUpdatedRow(node));
	}

	@Test
	public void testUpdatePromoNegozioSelectedFlag() throws Exception {
		when(promoService.findShopTypeById(anyLong())).thenReturn(tipoNegozio1);
		when(promoService.findById(any(Serializable.class))).thenReturn(testata1);
		assertNotNull(promoShopsUtil.updatePromoNegozioSelectedFlag((long) 5, "0", (long)3, "Utente",
				listaCanali, null, true));
	}

	@Test
	public void updatePromoNegozioSelectedFlagListaCanaliNullReturnsJson() throws Exception {
		when(promoService.findShopTypeById(anyLong())).thenReturn(tipoNegozio2);
		when(promoService.findById(any(Serializable.class))).thenReturn(testata2);
		assertNotNull(promoShopsUtil.updatePromoNegozioSelectedFlag((long) 5, "1", (long)3, "Utente",
				null, null, true));
	}
	
	@Test
	public void updatePromoNegozioSelectedFlagNUllPromoIdRetursnJson() throws Exception {
		when(promoService.findShopTypeById(anyLong())).thenReturn(tipoNegozio1);
		when(promoService.findAllNotCancelled(listaCanali)).thenReturn(testate);
		assertNotNull(promoShopsUtil.updatePromoNegozioSelectedFlag((long) 5, "0", null, "Utente",
				listaCanali, null, true));
	}

	@Test
	public void testResetDefaultRowDataWithPromoSelectedNull() throws Exception {
		when(promoService.findAllNotCancelled(listaCanali)).thenReturn(testate);
		assertNotNull(promoShopsUtil.resetDefaultRowData(null, "Utente", listaCanali, null, true));
	}

	@Test
	public void testResetDefaultRowData() throws Exception {
		assertNotNull(promoShopsUtil.resetDefaultRowData((long)1, "Utente", listaCanali, null, true));
	}

	@Test
	public void testPopulateModifiedRowData() {
		when(promoService.findById(anyLong())).thenReturn(testata1);
		assertNotNull(promoShopsUtil.populateModifiedRowData("1",null,
				"Utente", listaCanali, null, true));
	}

	@Test
	public void testPopulateModifiedRowDataRadioButtonChanged() {
		when(promoService.findById(anyLong())).thenReturn(testata1);
		assertNotNull(promoShopsUtil.populateModifiedRowData("1","CHANGED",
				"Utente", listaCanali, null, true));
	}

	@Test
	public void testPopulateModifiedRowDataRadiButtonSi() {
		when(promoService.findById(anyLong())).thenReturn(testata1);
		assertNotNull(promoShopsUtil.populateModifiedRowData("1","SI",
				"Utente", listaCanali, null, true));
	}

	@Test
	public void testPopulateModifiedRowDataRadiButtonNo() {
		when(promoService.findById(anyLong())).thenReturn(testata1);
		assertNotNull(promoShopsUtil.populateModifiedRowData("1","NO",
				"Utente", listaCanali, null, true));
	}
	
	@Test
	public void testPopulateModifiedRowDatacodiceTestataSelezionatoNULL() {
		assertNotNull(promoShopsUtil.populateModifiedRowData(null,null,
				"Utente", listaCanali, null, true));
	}

	@Test
	public void testGetShopTypesReturnsListWhenShopTypesAreFound() {
		assertNotNull(promoShopsUtil.getShopTypes());
	}
	
	@Test
	public void testGetShopTypesCatchesExceptionWhenErrorOccures() {
		promoShopsUtil.promoService=null;
		assertNull(promoShopsUtil.getShopTypes());
	}
	
	@Test
	public void testGetShopTypesReturnsNullWhenShopTypesAreNotFound() throws Exception {
		when(promoService.findAllTipoNegozioEntity()).thenReturn(null);
		assertNull(promoShopsUtil.getShopTypes());
	}
	
	@Test
	public void testGetShopTypesReturnsListOfShopTypesWhenShopTypesAreFound() throws Exception {
		when(promoService.findAllTipoNegozioEntity()).thenReturn(tipiNegozi);
		assertNotNull(promoShopsUtil.getShopTypes());
	}

	@Test
	public void testGetTipiConsegnaReturnsListWhenShopTypesAreFound() {
		assertNotNull(promoShopsUtil.getTipiConsegna());
	}

	@Test
	public void testGetTipiConsegnaReturnsNullWhenShopTypesAreNotFound() {
		when(promoService.findAllTipoConsegna()).thenReturn(null);
		assertNull(promoShopsUtil.getTipiConsegna());
	}
	
	@Test
	public void testGetTipoConsegnaCatchesExceptionWhenErrorOccures() {
		promoShopsUtil.promoService=null;
		assertNull(promoShopsUtil.getTipiConsegna());
	}

	@Test
	public void testStreamModifiedRowDataRadioVARIAZIONE_SU_DEFAULT() {
		when(promoShopsUtil.streamModifiedRowData("1", "CHANGED", dbFiltersJson,
				listaCanali, null, true)).thenCallRealMethod();
		when(promoService.findById(anyLong())).thenReturn(testata1);
		StreamingOutput out = promoShopsUtil.streamModifiedRowData("1", "CHANGED",
				dbFiltersJson, listaCanali, null, true);
		try {
			out.write(System.out);
		}catch(WebApplicationException e){
			log.error("WebApplicationException error", e);
		} catch (IOException e) {
			log.error("IOException error", e);
		}		
		assertNotNull(promoShopsUtil.streamModifiedRowData("1", "CHANGED",
				dbFiltersJson, listaCanali, null, true));
	}
	
	@Test
	public void testStreamModifiedRowDataRadioVISUALIZZA_NO() {
		when(promoShopsUtil.streamModifiedRowData("1", "NO", dbFiltersJson,
				listaCanali, null, true)).thenCallRealMethod();
		when(promoService.findById(anyLong())).thenReturn(testata1);
		StreamingOutput out = promoShopsUtil.streamModifiedRowData("1", "NO",
				dbFiltersJson, listaCanali, null, true);
		try {
			out.write(System.out);
		}catch(WebApplicationException e){
			log.error("WebApplicationException error", e);
		} catch (IOException e) {
			log.error("IOException error", e);
		}			
		assertNotNull(promoShopsUtil.streamModifiedRowData("1", "NO", dbFiltersJson,
				listaCanali, null, true));
	}
	
	@Test
	public void testStreamModifiedRowDataRadioVISUALIZZA_SI() {
		when(promoShopsUtil.streamModifiedRowData("1", "SI", dbFiltersJson,
				listaCanali, null, true)).thenCallRealMethod();
		when(promoService.findById(anyLong())).thenReturn(testata1);
		StreamingOutput out = promoShopsUtil.streamModifiedRowData("1", "SI",
				dbFiltersJson, listaCanali, null, true);
		try {
			out.write(System.out);
		}catch(WebApplicationException e){
			log.error("WebApplicationException error", e);
		} catch (IOException e) {
			log.error("IOException error", e);
		}			
		assertNotNull(promoShopsUtil.streamModifiedRowData("1", "SI", dbFiltersJson,
				listaCanali, null, true));
	}
	
	@Test
	public void testStreamModifiedRowDataSenzaCodiceTestataSelezionato() throws Exception {
		when(promoShopsUtil.streamModifiedRowData(null, "CHANGED", dbFiltersJson,
				listaCanali, null, true)).thenCallRealMethod();
		when(promoService.findAllNotCancelled(userFiltersMap, listaCanali)).thenReturn(testate);
		StreamingOutput out = promoShopsUtil.streamModifiedRowData(null, "CHANGED",
				dbFiltersJson, listaCanali, null, true);
		try {
			out.write(System.out);
		}catch(WebApplicationException e){
			log.error("WebApplicationException error", e);
		} catch (IOException e) {
			log.error("IOException error", e);
		}			
		assertNotNull(promoShopsUtil.streamModifiedRowData(null, "CHANGED",
				dbFiltersJson, listaCanali, null, true));
	}

	@Test
	public void testReadAndUploadFileUntilEmptyRowReturnsWhenTestataExists() throws IOException {
		File file = new File("file.xlsx");
		file.createNewFile();
		when(promoService.findById(anyLong())).thenReturn(testata1);
		when(applicationProperties.getProperty(anyString(), any(Integer.class))).thenReturn(100);
		final PromoShopUpdateDto result = promoShopsUtil.readAndUploadFileUntilEmptyRow(file, (long) 100, "User");
		assertEquals("Il file risulta vuoto", result.getMessage());
		assertEquals(PromoShopUpdateLevel.WARNING, result.getLevel());
		if (file.exists()) {
			file.delete();
		}
	}
	
	@Test
	public void testReadAndUploadFileUntilEmptyRowWhenTestataExistsWithShopItemUploads() throws IOException {
		File file = new File("file.xlsx");
		file.createNewFile();
		Set<String> promoNegoziSigles= new HashSet<>();
		Set<ShopItemUpload> setShopItemUploads = new HashSet<>();
		ShopItemUpload shopItemUpload1 = mock(ShopItemUpload.class);
		ShopItemUpload shopItemUpload2 = mock(ShopItemUpload.class);
		setShopItemUploads.add(shopItemUpload1);
		setShopItemUploads.add(shopItemUpload2);
		when(shopItemUpload1.isValid()).thenReturn(true);
		when(shopItemUpload2.isValid()).thenReturn(true);
		when(shopItemUpload1.getSigla()).thenReturn("Sigla1");
		when(shopItemUpload2.getSigla()).thenReturn("Sigla2");
		promoNegoziSigles.add("Sigla1");
		promoNegoziSigles.add("Sigla2");
		when(uploadExcelService.readFileUntilEmptyRow(file, promoNegoziSigles, 100)).thenReturn(setShopItemUploads);
		when(applicationProperties.getProperty(ApplicationProperties.MAX_UPLOAD_RECORDS, ApplicationProperties.DEFAULT_MAX_UPLOAD_RECORDS)).thenReturn(100);
		when(promoService.findById(anyLong())).thenReturn(testata1);
		final PromoShopUpdateDto result = promoShopsUtil.readAndUploadFileUntilEmptyRow(file, (long) 100, "User");
		assertEquals("Caricamento lista negozi effettuato", result.getMessage());
		assertEquals(PromoShopUpdateLevel.INFO, result.getLevel());
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void testReadAndUploadFileUntilEmptyRowWhenUploadDisabledExistsWithShopItemUploads() throws IOException {
		File file = new File("file.xlsx");
		file.createNewFile();
		when(applicationProperties.getProperty(ApplicationProperties.MAX_UPLOAD_RECORDS, ApplicationProperties.DEFAULT_MAX_UPLOAD_RECORDS)).thenReturn(0);
		when(promoService.findById(anyLong())).thenReturn(testata1);
		final PromoShopUpdateDto result = promoShopsUtil.readAndUploadFileUntilEmptyRow(file, (long) 100, "User");
		assertEquals("L'upload di record da file e' stato disattivato: contattare l'assistenza", result.getMessage());
		assertEquals(PromoShopUpdateLevel.WARNING, result.getLevel());
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void readAndUploadFileUntilEmptyRowReturnsNullWhenTestataNotExists() throws IOException {
		File file = new File("file.xlsx");
		file.createNewFile();
		when(promoService.findById(anyLong())).thenReturn(null);
		assertNull(promoShopsUtil.readAndUploadFileUntilEmptyRow(file, (long) 100, "User"));
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void readAndUploadFileUntilEmptyRow_returnsMessage_whenTestataInStatoNonConsentito() throws IOException {
		File file = new File("file.xlsx");
		file.createNewFile();
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final PromozioneStatoEntity promoStato = mock(PromozioneStatoEntity.class);
		final StatoPromozioneEntity statoCorrente = mock(StatoPromozioneEntity.class);
		when(testata.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
		when(testata.getDescrizione()).thenReturn("FOO");
		when(promoStato.getStatoPromozioneEntity()).thenReturn(statoCorrente);
		when(statoCorrente.getCodiceStato()).thenReturn(PromoStatusEnum.PIANIFICAZIONE_FINALIZZATA.getKey());
		when(promoService.findById(1L)).thenReturn(testata);
		final PromoShopUpdateDto result = promoShopsUtil.readAndUploadFileUntilEmptyRow(file, 1L, "junit");
		assertEquals("Lo stato della promozione FOO non consente il caricamento dei negozi", result.getMessage());
		assertEquals(PromoShopUpdateLevel.WARNING, result.getLevel());
		// Cleanup file
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void bulkUpdate_whenResetToDefault_shouldUpdateSelezioneFlagToDefault() {
		final PromoShopBulkParam param = mock(PromoShopBulkParam.class);
		when(param.getType()).thenReturn(ShopUpdateTypeEnum.RESET);
		when(param.getIdTipoNegozio()).thenReturn(null);
		when(param.getIdPromozione()).thenReturn(1L);
		when(promoService.findById(1L)).thenReturn(testata1);
		assertEquals(2, promoShopsUtil.bulkUpdate(param, "junit", null));
		verify(promoNegozio1, times(1)).setSelezioneFlag("true");
		verify(promoNegozio1, times(1)).setDataInizio(any(Date.class));
		verify(promoNegozio1, times(1)).setDataFine(any(Date.class));
		verify(promoNegozio2, times(1)).setSelezioneFlag("false");
		verify(promoNegozio2, times(1)).setDataInizio(any(Date.class));
		verify(promoNegozio2, times(1)).setDataFine(any(Date.class));
	}

	@Test
	public void bulkUpdate_whenUpdateTipoNegozioToNo_shouldUpdateOnlySpecificPromoNegozioFlagToFalse() throws Exception {
		final PromoShopBulkParam param = mock(PromoShopBulkParam.class);
		when(param.getType()).thenReturn(ShopUpdateTypeEnum.NEGOZIO);
		when(param.getFlag()).thenReturn(false);
		when(param.getIdTipoNegozio()).thenReturn(1L);
		when(param.getIdPromozione()).thenReturn(1L);
		when(promoService.findById(1L)).thenReturn(testata1);
		when(promoService.findShopTypeById(1L)).thenReturn(tipoNegozio1);
		assertEquals(1, promoShopsUtil.bulkUpdate(param, "junit", null));
		verify(promoNegozio1, times(1)).setSelezioneFlag("0");
		verify(promoNegozio2, never()).setSelezioneFlag(anyString());
	}

	@Test
	public void bulkUpdate_whenUpdateTipoNegozioToSi_shouldUpdateOnlySpecificPromoNegozioFlagToTrue() throws Exception {
		final PromoShopBulkParam param = mock(PromoShopBulkParam.class);
		when(param.getType()).thenReturn(ShopUpdateTypeEnum.NEGOZIO);
		when(param.getFlag()).thenReturn(true);
		when(param.getIdTipoNegozio()).thenReturn(1L);
		when(param.getIdPromozione()).thenReturn(1L);
		when(promoService.findById(1L)).thenReturn(testata1);
		when(promoService.findShopTypeById(1L)).thenReturn(tipoNegozio1);
		assertEquals(1, promoShopsUtil.bulkUpdate(param, "junit", null));
		verify(promoNegozio1, times(1)).setSelezioneFlag("1");
		verify(promoNegozio2, never()).setSelezioneFlag(anyString());
	}

	@Test
	public void bulkUpdate_whenUpdateCanaleConsegnaToNo_shouldUpdateOnlySpecificPromoNegozioFlagToFalse() {
		final PromoShopBulkParam param = mock(PromoShopBulkParam.class);
		when(param.getType()).thenReturn(ShopUpdateTypeEnum.CONSEGNA);
		when(param.getFlag()).thenReturn(false);
		when(param.getIdTipoNegozio()).thenReturn(null);
		when(param.getTipoConsegna()).thenReturn("FOO");
		when(param.getIdPromozione()).thenReturn(1L);
		when(promoService.findById(1L)).thenReturn(testata1);
		assertEquals(1, promoShopsUtil.bulkUpdate(param, "junit", null));
		verify(promoNegozio1, times(1)).setSelezioneFlag("0");
		verify(promoNegozio2, never()).setSelezioneFlag(anyString());
	}

	@Test
	public void bulkUpdate_whenUpdateCanaleConsegnaToSi_shouldUpdateOnlySpecificPromoNegozioFlagToTrue() {
		final PromoShopBulkParam param = mock(PromoShopBulkParam.class);
		when(param.getType()).thenReturn(ShopUpdateTypeEnum.CONSEGNA);
		when(param.getFlag()).thenReturn(true);
		when(param.getIdTipoNegozio()).thenReturn(null);
		when(param.getTipoConsegna()).thenReturn("BAR");
		when(param.getIdPromozione()).thenReturn(1L);
		when(promoService.findById(1L)).thenReturn(testata1);
		assertEquals(1, promoShopsUtil.bulkUpdate(param, "junit", null));
		verify(promoNegozio1, never()).setSelezioneFlag(anyString());
		verify(promoNegozio2, times(1)).setSelezioneFlag("1");
	}

	@Test
	public void bulkUpdate_whenUpdateZonaToNo_shouldUpdateOnlySpecificPromoNegozioFlagToFalse() {
		final PromoShopBulkParam param = mock(PromoShopBulkParam.class);
		when(param.getType()).thenReturn(ShopUpdateTypeEnum.ZONA);
		when(param.getFlag()).thenReturn(false);
		when(param.getIdTipoNegozio()).thenReturn(null);
		when(param.getTipoConsegna()).thenReturn(null);
		when(param.getZonaSocieta()).thenReturn("1");
		when(param.getZonaCodice()).thenReturn("01");
		when(param.getIdPromozione()).thenReturn(1L);
		when(promoService.findById(1L)).thenReturn(testata1);
		assertEquals(1, promoShopsUtil.bulkUpdate(param, "junit", null));
		verify(promoNegozio1, times(1)).setSelezioneFlag("0");
		verify(promoNegozio2, never()).setSelezioneFlag(anyString());
	}

	@Test
	public void bulkUpdate_whenUpdateZonaToSi_shouldUpdateOnlySpecificPromoNegozioFlagToFalse() {
		final PromoShopBulkParam param = mock(PromoShopBulkParam.class);
		when(param.getType()).thenReturn(ShopUpdateTypeEnum.ZONA);
		when(param.getFlag()).thenReturn(true);
		when(param.getIdTipoNegozio()).thenReturn(null);
		when(param.getTipoConsegna()).thenReturn(null);
		when(param.getZonaSocieta()).thenReturn("2");
		when(param.getZonaCodice()).thenReturn("02");
		when(param.getIdPromozione()).thenReturn(1L);
		when(promoService.findById(1L)).thenReturn(testata1);
		assertEquals(1, promoShopsUtil.bulkUpdate(param, "junit", null));
		verify(promoNegozio1, never()).setSelezioneFlag(anyString());
		verify(promoNegozio2, times(1)).setSelezioneFlag("1");
	}
}
