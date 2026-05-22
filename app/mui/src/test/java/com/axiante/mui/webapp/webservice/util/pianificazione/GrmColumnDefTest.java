package com.axiante.mui.webapp.webservice.util.pianificazione;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasNoJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.withJsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CategoriaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgLivelloPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GrmEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.CfgConfHeaderService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PianificazioneHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.util.PromoPianificazioneEnum;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.ArrayList;
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
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GrmColumnDefTest extends ColumnDefTest {

	@Mock
	private Instance<CfgConfHeaderService> confHeaderServiceInstance;

	@Mock
	private CfgConfHeaderService confHeaderService;

	@Mock
	private Instance<PianificazioneService> pianificazioneServiceInstance;

	@Mock
	private PianificazioneService pianificazioneService;

	@Mock
	private Instance<PianificazioneHelper> pianificazioneHelperInstance;

	@Mock
	private PianificazioneHelper pianificazioneHelper;

	@InjectMocks
	private GrmColumnDef columnDef;

	@Before
	public void setUp() {
		when(pianificazioneServiceInstance.get()).thenReturn(pianificazioneService);
		when(pianificazioneHelperInstance.get()).thenReturn(pianificazioneHelper);
	}

	@Test
	public void generateColumnDefByPromoConfiguration_givenPromozioneTestata_shouldReturnJsonString()
			throws IOException {
		// Good path
		final PromozioneTestataEntity entity = mock(PromozioneTestataEntity.class);
		final String hiddenCols = readJsonAsString("hiddenColsForGrm.json");
		String json = columnDef.generateColumnDefByPromoConfiguration(entity, hiddenCols, "grid1", null);
		assertNotNull(json);
		assertThat(json, isJson(allOf(
				withJsonPath("$.columnDef[0].hide", equalTo(false)),
				withJsonPath("$.columnDef[3].hide", equalTo(true))
		)));
		// Unable to apply filter
		json = columnDef.generateColumnDefByPromoConfiguration(entity, "wrong", "grid2", null);
		assertNotNull(json);
		assertThat(json, isJson(allOf(
				withJsonPath("$.columnDef[0].hide", equalTo(false)),
				withJsonPath("$.columnDef[3].hide", equalTo(false))
		)));
	}

	@Test
	public void generateRowDataByPromoConfiguration_shouldReturnRowData() {
		// Arrange
		final List<GrmEntity> grmEntities = mockGrm();
		when(pianificazioneService.getAllGrmEntity()).thenReturn(grmEntities);
		// Act
		final String json = columnDef.generateRowDataByPromoConfiguration("1", "bar");
		// Assert
		assertNotNull(json);
		assertThat(json, isJson(withJsonPath("$.rowData", hasSize(10))));
	}

	@Test
	public void generateRowDataByPromoPianificazioneMaster_shouldReturnJsonNode() {
		// Arrange
		final PromozioneTestataEntity testata = mockTestata();
		final MeccanicheEntity meccanica = mockMeccanica(1);
		final PromozionePianificazioneEntity entity = mockPianificazione(testata, meccanica);
		final List<GrmEntity> grmEntities = mockGrm();
		when(pianificazioneService.getAllGrmEntity()).thenReturn(grmEntities);
		// Act
		final ObjectNode node = columnDef.generateRowDataByPromoPianificazioneMaster(entity);
		// Assert
		assertNotNull(node);
		assertThat(node.toString(), isJson(withJsonPath("$.rowData", hasSize(10))));
	}

    @Test
    public void generateRowDataByPromoConfiguration_shouldReturnNull() {
        assertNull(columnDef.generateRowDataByPromoConfiguration(null, null, null, true, null));
    }

    @Test
    public void generateRowDataFilteredByPromoConfiguration_shouldReturnNull() {
        assertNull(columnDef.generateRowDataFilteredByPromoConfiguration(null, null));
    }

    @Test(expected = NullPointerException.class)
    public void generateRowDataByPromoElementMechanic_givenNullTestata_shouldThrowException() {
        columnDef.generateRowDataByPromoElementMechanic(null, 1L, "42", "FOO");
    }

    @Test(expected = NullPointerException.class)
    public void generateRowDataByPromoElementMechanic_givenNullMeccanica_shouldThrowException() {
        columnDef.generateRowDataByPromoElementMechanic(mock(PromozioneTestataEntity.class), null, "42", "FOO");
    }

	@Test
	public void generateRowDataByPromoElementMechanic_shouldDiscardDuplicateItems_whenHeaderFlagIsFalse() {
		final List<GrmEntity> grm = new ArrayList<>();
		final GrmEntity grm1 = mockGrm(1);
		final GrmEntity grm2 = mockGrm(2);
		grm.add(grm1);
		grm.add(grm2);
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		final CfgConfHeaderEntity confHeaderEntity = mock(CfgConfHeaderEntity.class);
		final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
		final CfgLivelloPianificazioneEntity planningLevel = mock(CfgLivelloPianificazioneEntity.class);
		when(canale.getDuplicaGrm()).thenReturn(true);
		when(cfgSetPianificazione.getId()).thenReturn(1L);
		when(planningLevel.getCodice()).thenReturn("RAGGRUPPAMENTO");
		when(confHeaderServiceInstance.get()).thenReturn(confHeaderService);
		when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
				.thenReturn(confHeaderEntity);
		when(confHeaderEntity.getDuplicaGrm()).thenReturn(false);
		when(confHeaderEntity.getLivelloPianificazione()).thenReturn(planningLevel);
		when(pianificazioneService.getAllGrmEntity()).thenReturn(grm);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(testata.getMuiCanalePromozione()).thenReturn(canale);
		when(pianificazioneHelper.getUsedItems(testata, 1L, "RAGGRUPPAMENTO",
				PromoPianificazioneEnum.GRM.getTipoElemento())).thenReturn(Collections.singletonList(2L));
		final ObjectNode node = columnDef.generateRowDataByPromoElementMechanic(testata, 1L, "foo", "bar");
		assertThat(node.toString(), isJson(allOf(
				withJsonPath("$.rowData", hasSize(1)),
				withJsonPath("$.rowData[0].id.value", equalTo("1")),
				withJsonPath("$.rowData[0].grm.value", equalTo("[GRM_G1] GRM1")),
				withJsonPath("$.rowData[0].elemento.value", equalTo("G1 - Grm1"))
		)));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void generateRowDataByPromoElementMechanic_shouldAllowDuplicateItems_whenHeaderFlagIsTrue() {
		final List<GrmEntity> grm = new ArrayList<>();
		final GrmEntity grm1 = mockGrm(1);
		final GrmEntity grm2 = mockGrm(2);
		grm.add(grm1);
		grm.add(grm2);
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		final CfgConfHeaderEntity confHeaderEntity = mock(CfgConfHeaderEntity.class);
		final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
		final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
		when(canale.getDuplicaGrm()).thenReturn(true);
		when(cfgLivello.getCodice()).thenReturn("ELEMENTO");
		when(confHeaderEntity.getLivelloPianificazione()).thenReturn(cfgLivello);
		when(cfgSetPianificazione.getId()).thenReturn(1L);
		when(confHeaderServiceInstance.get()).thenReturn(confHeaderService);
		when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
				.thenReturn(confHeaderEntity);
		when(confHeaderEntity.getDuplicaGrm()).thenReturn(true);
		when(pianificazioneService.getAllGrmEntity()).thenReturn(grm);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(testata.getMuiCanalePromozione()).thenReturn(canale);
		final ObjectNode node = columnDef.generateRowDataByPromoElementMechanic(testata, 1L, "foo", "bar");
		assertThat(node.toString(), isJson(allOf(
				withJsonPath("$.rowData", hasSize(2)),
				withJsonPath("$.rowData[0].id.value", equalTo("1")),
				withJsonPath("$.rowData[0].grm.value", equalTo("[GRM_G1] GRM1")),
				withJsonPath("$.rowData[0].elemento.value", equalTo("G1 - Grm1")),
				withJsonPath("$.rowData[1].id.value", equalTo("2")),
				withJsonPath("$.rowData[1].grm.value", equalTo("[GRM_G2] GRM2")),
				withJsonPath("$.rowData[1].elemento.value", equalTo("G2 - Grm2"))
		)));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void generateRowDataByPromoElementMechanic_shouldSetBackgroundStyleYellowOnItemsAlreadyPlanned_whenDuplicaFlagSetted() {
		final List<GrmEntity> grm = new ArrayList<>();
		final GrmEntity grm1 = mockGrm(1);
		final GrmEntity grm2 = mockGrm(2);
		grm.add(grm1);
		grm.add(grm2);
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
		final CfgConfHeaderEntity confHeaderEntity = mock(CfgConfHeaderEntity.class);
		final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
		final CfgLivelloPianificazioneEntity cfgLivello = mock(CfgLivelloPianificazioneEntity.class);
		when(cfgLivello.getCodice()).thenReturn("ELEMENTO");
		when(confHeaderEntity.getLivelloPianificazione()).thenReturn(cfgLivello);
		when(cfgSetPianificazione.getId()).thenReturn(1L);
		when(canale.getDuplicaGrm()).thenReturn(true);
		when(confHeaderServiceInstance.get()).thenReturn(confHeaderService);
		when(confHeaderService.findByMeccanicaIdAndSetPianificazioneId(1L, 1L))
				.thenReturn(confHeaderEntity);
		when(confHeaderEntity.getDuplicaGrm()).thenReturn(true);
		when(pianificazioneService.getAllGrmEntity()).thenReturn(grm);
		when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
		when(testata.getMuiCanalePromozione()).thenReturn(canale);
		when(pianificazioneHelper.getUsedItems(testata, 1L, "ELEMENTO", "GRM"))
				.thenReturn(Collections.singletonList(1L));
		final ObjectNode node = columnDef.generateRowDataByPromoElementMechanic(testata, 1L, "foo", "bar");
		assertThat(node.toString(), isJson(allOf(
				withJsonPath("$.rowData", hasSize(2)),
				withJsonPath("$.rowData[0].id.value", equalTo("1")),
				withJsonPath("$.rowData[0].grm.value", equalTo("[GRM_G1] GRM1")),
				withJsonPath("$.rowData[0].elemento.value", equalTo("G1 - Grm1")),
				withJsonPath("$.rowData[0].grm.warning", equalTo(true)),
				withJsonPath("$.rowData[1].id.value", equalTo("2")),
				withJsonPath("$.rowData[1].grm.value", equalTo("[GRM_G2] GRM2")),
				withJsonPath("$.rowData[1].elemento.value", equalTo("G2 - Grm2")),
				hasNoJsonPath("$.rowData[1].grm.styleParams.backgroundColor")
		)));
	}

	private PromozionePianificazioneEntity mockPianificazione(PromozioneTestataEntity testata, MeccanicheEntity meccanica) {
		final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
		when(pianificazione.getPromozioneTestataEntity()).thenReturn(testata);
		when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
		return pianificazione;
	}

	private PromozioneTestataEntity mockTestata() {
		final PromozioneTestataEntity testata = mock(PromozioneTestataEntity.class);
		final Set<PromozionePianificazioneEntity> pianificazioni = new HashSet<>();
		for (int i = 1; i <= 5; i++) {
			final PromozionePianificazioneEntity p = mock(PromozionePianificazioneEntity.class);
			final MeccanicheEntity meccanica = mockMeccanica(i);
			when(p.getMeccanicaEntity()).thenReturn(meccanica);
			when(p.getTipoElemento()).thenReturn(i == 3 ? "GRM" : "FOO");
			when(p.getParent()).thenReturn(mock(PromozionePianificazioneEntity.class));
			pianificazioni.add(p);
		}
		when(testata.getPromozionePianificazioneEntities()).thenReturn(pianificazioni);
		return testata;
	}

	private MeccanicheEntity mockMeccanica(int i) {
		final MeccanicheEntity m = mock(MeccanicheEntity.class);
		when(m.getId()).thenReturn((long) i);
		return m;
	}

	private List<GrmEntity> mockGrm() {
		List<GrmEntity> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			final GrmEntity grm = mock(GrmEntity.class);
			when(grm.getId()).thenReturn((long) i);
			if (i % 2 == 0) {
				final CategoriaEntity categoria = mock(CategoriaEntity.class);
				when(grm.getCodiceGrm()).thenReturn(String.format("GRM-%d", i));
				when(grm.getMuiCategoria()).thenReturn(categoria);
			}
			if (i % 3 == 0) {
				when(grm.getDescrizione()).thenReturn(String.format("GRM numero %d", i));
			}
			list.add(grm);
		}
		return list;
	}

	private GrmEntity mockGrm(int id) {
		final GrmEntity grm = mock(GrmEntity.class);
		when(grm.getId()).thenReturn((long) id);
		when(grm.getCodiceGrm()).thenReturn(String.format("G%d", id));
		when(grm.getDescrizione()).thenReturn(String.format("Grm%d", id));
		return grm;
	}
}
