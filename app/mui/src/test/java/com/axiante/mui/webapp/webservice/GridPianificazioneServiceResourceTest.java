package com.axiante.mui.webapp.webservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.common.PianificazioneConstants;
import com.axiante.mui.common.PianificazioneSecurityEnum;
import com.axiante.mui.common.promo.grid.DBPromoAgCell;
import com.axiante.mui.common.utility.JsonUtils;
import com.axiante.mui.dbpromo.business.enumeration.PlanningLevelEnum;
import com.axiante.mui.dbpromo.business.service.CfgPianificazioneService;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgConfHeaderEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazTipoRigaEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CheckCompratoriEntity;
import com.axiante.mui.dbpromo.persistence.entities.CompratoreEntity;
import com.axiante.mui.dbpromo.persistence.entities.MeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.UploadFidayEntity;
import com.axiante.mui.dbpromo.persistence.service.ClusterClienteService;
import com.axiante.mui.dbpromo.persistence.service.ConfigurationService;
import com.axiante.mui.dbpromo.persistence.service.ItemService;
import com.axiante.mui.dbpromo.persistence.service.PianificazioneService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.dbpromo.persistence.service.PromozioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.UploadFidatyService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.PianificazioneSecurityUtil;
import com.axiante.mui.webapp.utils.security.PromoSecurity;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoRowDataUtil;
import com.axiante.mui.webapp.webservice.util.PianificazionePromoUtil;
import com.axiante.mui.webapp.webservice.util.pianificazione.ComplementaryColumnDef;
import com.axiante.mui.webapp.webservice.util.pianificazione.DynamicColumnDefFactory;
import com.axiante.mui.webapp.webservice.util.pianificazione.PlanningColumnDef;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.NumSetUtils;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromoConfigurationHelper;
import com.axiante.mui.webapp.webservice.util.pianificazione.helper.PromozionePianificazioneEntityHelper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.enterprise.inject.Instance;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GridPianificazioneServiceResourceTest {

    private final String contesto = "testContext";
    private final List<String> codiciGruppo = Arrays.asList("FOO", "BAR");
    private final List<String> codiciCompratori = Arrays.asList("S01", "S02");

    @Mock
    HttpSession session;

    @Mock
    UserService userService;

    @Mock
    private UserDTO userDTO;

    @Mock
    private UsersEntity usersEntity;

    @Mock
    private HttpServletRequest mockedRequest;

    @Mock
    private PromoService promoService;

    @Mock
    private DynamicColumnDefFactory dynamicColumnDefFactory;

    @Mock
    private PlanningColumnDef planningColumnDef;

    @Mock
    private PianificazionePromoRowDataUtil pianificazionePromoRowDataUtil;

    @Mock
    private PianificazioneService pianificazioneService;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private ClusterClienteService clusterClienteService;

    @Mock
    private PianificazionePromoUtil pianificazionePromoUtil;

    @Mock
    private CfgPianificazioneService cfgPianificazioneService;

    @Mock
    private UploadFidatyService uploadFidatyService;

    @Mock
    private ComplementaryColumnDef complementariUtils;

    @Mock
    private PromoSecurity promoSecurity;

    @Mock
    private Instance<PianificazioneSecurityUtil> securityUtilInstance;

    @Mock
    private PianificazioneSecurityUtil securityUtil;

    @Mock
    private Instance<ItemService> itemServiceInstance;

    @Mock
    private PromozionePianificazioneEntityHelper promoPianificazioneEntityHelper;

    @Mock
    private Instance<PromozioneTestataService> promozioneTestataService;

    @Mock
    private PromozioneTestataService promoTestataService;

    @Mock
    private PromoConfigurationHelper promoConfigurationHelper;

    @Mock
    private Instance<NumSetUtils> numSetUtilsInstance;

    @Mock
    private NumSetUtils numSetUtils;

    @Spy
    @InjectMocks
    private GridPianificazioneServiceResource serviceResource = new GridPianificazioneServiceResource();

    @Before
    public void setUp() throws Exception {
        when(userDTO.getGruppi()).thenReturn(codiciGruppo);
        when(serviceResource.getCurrentUser()).thenReturn(usersEntity);
        when(serviceResource.getApplicationUser(contesto)).thenReturn(userDTO);
        when(serviceResource.getApplicationUser(nullable(String.class))).thenReturn(userDTO);
        when(securityUtilInstance.get()).thenReturn(securityUtil);
        when(dynamicColumnDefFactory.getDynamicColumnDefGridName(anyString())).thenReturn("grid");
        when(numSetUtilsInstance.get()).thenReturn(numSetUtils);
    }

    @Test
    @Ignore("Not yet")
    public void getColumnDef() {
    }

    @Test
    @Ignore("Not yet")
    public void getRowData() {
    }

    @Test
    public void getPlanningColumnDef_badPromoId() throws Exception {
        final Response response = serviceResource.getPlanningColumnDef("foo", "bar", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getPlanningColumnDef_promoNotFound() throws Exception {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getPlanningColumnDef("foo", "1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getPlanningColumnDef_promoNotAccessible() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getPlanningColumnDef("foo", "1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getPlanningColumnDef_badElementType() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(dynamicColumnDefFactory.getDynamicColumnDef("foo")).thenReturn(null);
        final Response response = serviceResource.getPlanningColumnDef("foo", "1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getPlanningColumnDef_ok() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(dynamicColumnDefFactory.getDynamicColumnDef("foo")).thenReturn(planningColumnDef);
        when(planningColumnDef.generateColumnDefByPromoConfiguration(eq(promozione), nullable(String.class), anyString(), eq(contesto)))
                .thenReturn("ok");
        final Response response = serviceResource.getPlanningColumnDef("foo", "1", contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(planningColumnDef, times(1))
                .generateColumnDefByPromoConfiguration(eq(promozione), nullable(String.class), anyString(), eq(contesto));
    }

    @Test
    public void getPlanningRowData_badPromoId() throws Exception {
        final Response response = serviceResource.getPlanningRowData("bad", "bad", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(planningColumnDef, never()).generateRowDataByPromoConfiguration(anyString(), nullable(String.class));
    }

    @Test
    public void getPlanningRowData_promoNotFound() throws Exception {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getPlanningRowData("bad", "1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(planningColumnDef, never()).generateRowDataByPromoConfiguration(anyString(), nullable(String.class));
    }

    @Test
    public void getPlanningRowData_promoNotAccessible() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getPlanningRowData("bad", "1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(planningColumnDef, never()).generateRowDataByPromoConfiguration(anyString(), nullable(String.class));
    }

    @Test
    public void getPlanningRowData_ok() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(dynamicColumnDefFactory.getDynamicColumnDef("pianificazione")).thenReturn(planningColumnDef);
        when(planningColumnDef.generateRowDataByPromoConfiguration("1", null)).thenReturn("ok");
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getPlanningRowData("pianificazione", "1", contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(planningColumnDef, times(1)).generateRowDataByPromoConfiguration("1", null);
    }

    @Test
    public void getPlanningCompratoriColumnDef() throws Exception {
        final Response response = serviceResource.getPlanningCompratoriColumnDef(contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(serviceResource, times(1)).loadColumnDefFromFile(
                "pianificazione_compratori_columnDef.json", "db_pianificazione_compratori", contesto);
    }

    @Test
    public void getPlanningCompratoriRowData_badPromoId() throws Exception {
        final Response response = serviceResource.getPlanningCompratoriRowData("bad", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(pianificazionePromoRowDataUtil, never()).createCheckCompratoriRowData(anyList());
    }

    @Test
    public void getPlanningCompratoriRowData_promoNotFound() throws Exception {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getPlanningCompratoriRowData("1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(pianificazionePromoRowDataUtil, never()).createCheckCompratoriRowData(anyList());
    }

    @Test
    public void getPlanningCompratoriRowData_promoNotAccessible() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getPlanningCompratoriRowData("1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(pianificazionePromoRowDataUtil, never()).createCheckCompratoriRowData(anyList());
    }

    @Test
    public void getPlanningCompratoriRowData_ok() throws Exception {
        final CompratoreEntity compratore = mock(CompratoreEntity.class);
        final CheckCompratoriEntity checkCompratore = mock(CheckCompratoriEntity.class);
        final Set<CheckCompratoriEntity> checkCompratori = Collections.singleton(checkCompratore);
        final PromozioneTestataEntity promozione = mockPromozione();
        when(checkCompratore.getCompratoreEntity()).thenReturn(compratore);
        when(compratore.getCodiceCompratore()).thenReturn("S01");
        when(promozione.getCheckCompratori()).thenReturn(checkCompratori);
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(securityUtil.getReadableCompratori(promozione, codiciGruppo)).thenReturn(codiciCompratori);
        when(pianificazionePromoRowDataUtil.createCheckCompratoriRowData(anyList())).thenReturn("ok");
        final Response response = serviceResource.getPlanningCompratoriRowData("1", contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(pianificazionePromoRowDataUtil, times(1)).createCheckCompratoriRowData(anyList());
    }

    @Test
    public void getPlanningControlliColumnDef() throws Exception {
        final Response response = serviceResource.getPlanningControlliColumnDef(contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(serviceResource, times(1)).loadColumnDefFromFile(
                "pianificazione_controlli_columnDef.json", "db_pianificazione_controlli", contesto);
    }

    @Test
    @Ignore("Not yet")
    public void getPlanningControlliRowData() {
    }

    @Test
    @Ignore("Not yet")
    public void getPlanningOverlapColumnDef() {
    }

    @Test
    @Ignore("Not yet")
    public void getPlanningOverlapRowData() {
    }

    @Test
    public void removePlanningRow() {
        // TODO
    }

    @Test
    public void emptyRaggruppamento() {
        // TODO
    }

    @Test
    @Ignore("Not yet")
    public void getCopyPasteColumnDef() {
    }

    @Test
    @Ignore("Not yet")
    public void getCopyPasteRowData() {
    }

    @Test
    public void validateCopyPasteItemCode() {
        // TODO
    }

    @Test
    @Ignore("Non so perche' ObjectNode e' sempre null")
    public void updatePlanning() {
        // Prepare payload
        final Map<String, String> meccanicaMap = new HashMap<>();
        meccanicaMap.put("value", "M000 - Foo");
        final Map<String, String> promoPianificazioneMap = new HashMap<>();
        promoPianificazioneMap.put("value", "24");
        final Map<String, String> columnToBeUpdatedMap = new HashMap<>();
        columnToBeUpdatedMap.put("value", "FOOBAR");
        final Map<String, String> valueToBeUpdatedMap = new HashMap<>();
        valueToBeUpdatedMap.put("value", "HELLO");
        final Map<String, String> columnHeaderToBeUpdatedMap = new HashMap<>();
        columnHeaderToBeUpdatedMap.put("value", "FOOBAR");
        final Map<String, Object> payload = new HashMap<>();
        payload.put("ID_MECCANICA", meccanicaMap);
        payload.put("idPromoPianificazione", promoPianificazioneMap);
        payload.put("columnToBeUpdated", columnToBeUpdatedMap);
        payload.put("valueToBeUpdated", valueToBeUpdatedMap);
        payload.put("columnHeaderToBeUpdated", columnHeaderToBeUpdatedMap);
        // Mocking
        PromozioneTestataEntity testata = mockPromozione();
        PromozionePianificazioneEntity pianificazione = mockPianificazione(testata);
        CfgPianificazTipoRigaEntity tipoRiga = mock(CfgPianificazTipoRigaEntity.class);
        CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        CfgPianificazioneEntity cfgPianificazione = mock(CfgPianificazioneEntity.class);
        CfgConfHeaderEntity cfgConfHeader = mock(CfgConfHeaderEntity.class);
        Set<CfgPianificazioneEntity> cfgPianificazioni = Collections.singleton(mock(CfgPianificazioneEntity.class));
        List<PromozionePianificazioneEntity> pianificazioniUpdated = Collections.singletonList(pianificazione);
        ObjectNode objectNode = mock(ObjectNode.class);
        CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        Set<CfgConfHeaderEntity> cfgConfHeaders = Collections.singleton(cfgConfHeader);
        when(tipoRiga.getCodiceTipo()).thenReturn(PlanningLevelEnum.ELEMENTO.getCode());
        when(meccanica.getCodiceMeccanica()).thenReturn("M024");
        when(canale.getAbilitaUpload()).thenReturn(0);
        when(pianificazione.getTipoRiga()).thenReturn(tipoRiga);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(testata.getMuiCanalePromozione()).thenReturn(canale);
        when(testata.getCanalePromozioneEntity()).thenReturn(canale);
        when(testata.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(cfgPianificazione.getMuiCfgConfHeader()).thenReturn(cfgConfHeader);
        when(cfgPianificazione.getSicurezza()).thenReturn(PianificazioneSecurityEnum.WRITE.getSecurity());
        when(cfgConfHeader.getMuiCfgPianificaziones()).thenReturn(cfgPianificazioni);
        when(cfgConfHeader.getMeccanicaEntity()).thenReturn(meccanica);
        when(cfgSetPianificazione.getMuiCfgConfHeaders()).thenReturn(cfgConfHeaders);
        when(promozioneTestataService.get()).thenReturn(promoTestataService);
        when(pianificazioneService.getPromoPianificazoneById(24L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(testata, userDTO)).thenReturn(true);
        when(securityUtil.isWriteable(eq(pianificazione), anyList())).thenReturn(true);
        when(cfgPianificazioneService.findPianificazioneByCanaleMeccanicaTipoRigaField(canale, meccanica, tipoRiga, "FOOBAR", testata))
                .thenReturn(cfgPianificazione);
        when(pianificazionePromoUtil.validateUpdatedPlanningData(cfgPianificazione, "HELLO", null))
                .thenReturn(true);
        when(promoPianificazioneEntityHelper.invokeSetterEntity(anyList(), eq("FOOBAR"), eq("HELLO"), eq(cfgPianificazione)))
                .thenReturn(pianificazioniUpdated);
        when(promoConfigurationHelper.getConfigurazioniSorted(testata)).thenReturn(new ArrayList<>(cfgPianificazioni));
        when(dynamicColumnDefFactory.getDynamicColumnDef(anyString())).thenReturn(planningColumnDef);
        when(planningColumnDef.mapPianificazioneToObjectNode(
                any(PromozioneTestataEntity.class), any(PromozionePianificazioneEntity.class),
                anyBoolean(), eq(1), anyBoolean(), anyList(), eq(null), anyBoolean(), anyList(), any(), anyBoolean(), anyBoolean()
        )).thenReturn(objectNode);
        try {
            final Response response = serviceResource.updatePlanning(asPayload(payload), contesto, mockedRequest);
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        } catch (Exception ex) {
            fail(String.format("An unexpected exception was thrown: %s", ex.getMessage()));
        }
    }

    @Test
    public void whenUpdatePlanningCalledWithEmptyPayloadThenReturnPreconditionFail() {
        final String payload = "{}";
        try {
            Response response = serviceResource.updatePlanning(payload, null, mock(HttpServletRequest.class));
            assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        } catch (Exception e) {
            fail(String.format("An unexpected exception was thrown: %s", e.getMessage()));
        }
    }

    @Test
    public void whenUpdatePlanningCalledWithMissingPayloadPartsThenReturnPreconditionFail() {
        Map<String, String> payload = new HashMap<>();
        payload.put("columnToBeUpdated", "somecolumn");
        try {
            Response response = serviceResource.updatePlanning(asPayload(payload), null, mock(HttpServletRequest.class));
            assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
            payload.clear();
            payload.put("valueToBeUpdated", "somevalue");
            response = serviceResource.updatePlanning(asPayload(payload), null, mock(HttpServletRequest.class));
            assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        } catch (Exception e) {
            fail(String.format("An unexpected exception was thrown: %s", e.getMessage()));
        }
    }

    @Test
    public void whenUpdatePlanningCalledNoPromoAccessibleThenReturnsPreconditionFailed() {
        Map<String, Object> payload = createPayload();
        when(serviceResource.getApplicationUser(contesto)).thenReturn(userDTO);
        try {
            Response response = serviceResource.updatePlanning(asPayload(payload), null, mock(HttpServletRequest.class));
            assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
            assertEquals("{\"message\":\"Promozione non accessibile\"}", response.getEntity().toString());
        } catch (Exception e) {
            fail(String.format("An unexpected exception was thrown: %s", e.getMessage()));
        }
    }

    @Test
    public void whenUpdatePlanningCalledValidationFailedThenReturnsPreconditionFailed() {
        //TODO: questo dovrebbe ritornare un 412 e non un 200
        Map<String, Object> payload = createPayload();
        PromozioneTestataEntity testata = mockPromozione();
        PromozionePianificazioneEntity pianificazione = mockPianificazione(testata);
        when(serviceResource.getApplicationUser(contesto)).thenReturn(userDTO);
        when(pianificazioneService.getPromoPianificazoneById(anyLong())).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(nullable(PromozioneTestataEntity.class), nullable(UserDTO.class))).thenReturn(true);
        when(session.getAttribute(UsersEntity.USER_ATTRIBUTE)).thenReturn(usersEntity);
        when(usersEntity.isAdmin()).thenReturn(true);
        CfgPianificazTipoRigaEntity tipoRigaEntity = mock(CfgPianificazTipoRigaEntity.class);
        when(pianificazione.getTipoRiga()).thenReturn(tipoRigaEntity);
        when(tipoRigaEntity.getCodiceTipo()).thenReturn("E");
        try {
            Response response = serviceResource.updatePlanning(asPayload(payload), contesto, mock(HttpServletRequest.class));
            assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
            assertThat(response.getEntity().toString(), CoreMatchers.containsString("Caricamento fallito: validazione campo errata"));
        } catch (Exception e) {
            fail(String.format("An unexpected exception was thrown: %s", e.getMessage()));
        }
    }

    @Test
    public void calcolaWebservicesPerPianificazioneReturnsEmptyWhenEmptySetPianificazione() {
        PromozioneTestataEntity promo = mockPromozione();
        PromozionePianificazioneEntity plano = mockPianificazione(promo);
        CfgSetPianificazioneEntity set = mock(CfgSetPianificazioneEntity.class);
        when(promo.getMuiCfgSetPianificazione()).thenReturn(set);
        when(set.getMuiCfgConfHeaders()).thenReturn(Collections.emptySet());
        Map<String, String> result = serviceResource.calcolaWebservicesPerPianificazione(promo, plano);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void calcolaWebservicesPerPianificazioneReturnsEmptyWhenNoConfigurationMatches() {
        PromozioneTestataEntity promo = mockPromozione();
        PromozionePianificazioneEntity plano = mockPianificazione(promo);
        MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        when(plano.getMeccanicaEntity()).thenReturn(meccanica);
        when(meccanica.getCodiceMeccanica()).thenReturn("meccanica1");
        CfgSetPianificazioneEntity set = mock(CfgSetPianificazioneEntity.class);
        when(promo.getMuiCfgSetPianificazione()).thenReturn(set);
        CfgConfHeaderEntity header = mock(CfgConfHeaderEntity.class);
        when(set.getMuiCfgConfHeaders()).thenReturn(Collections.singleton(header));
        when(header.getMeccanicaEntity()).thenReturn(meccanica);
        when(header.getMuiCfgPianificaziones()).thenReturn(Collections.emptySet());
        when(pianificazionePromoUtil.getConfigurazioniWithChiave(anyList())).thenReturn(Collections.emptyList());

        Map<String, String> result = serviceResource.calcolaWebservicesPerPianificazione(promo, plano);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test

    public void calcolaWebservicesPerCampoChiaveReturnsEmptyWhenNoConfigurationFound() {
        when(pianificazionePromoUtil.getConfigurazioniWithChiave(any(), nullable(String.class))).thenReturn(Collections.emptyList());
        CfgPianificazioneEntity configurazione = mock(CfgPianificazioneEntity.class);
        CfgConfHeaderEntity header = mock(CfgConfHeaderEntity.class);
        when(configurazione.getMuiCfgConfHeader()).thenReturn(header);
        when(header.getMuiCfgPianificaziones()).thenReturn(Collections.emptySet());
        Map<String, String> result = serviceResource.calcolaWebservicesPerCampoChiave(null, configurazione, null, null); // i valori non mi servono a niente
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getBuonoScontoProgressivoCondizionale_emptyId() {
        final Response response = serviceResource.getBuonoScontoProgressivoCondizionale("", "key", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, never()).getPromoPianificazoneById(anyLong());
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getBuonoScontoProgressivoCondizionale_pianificazioneNotFound() {
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(null);
        final Response response = serviceResource.getBuonoScontoProgressivoCondizionale("1", "key", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getBuonoScontoProgressivoCondizionale_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(eq(promozione), any(UserDTO.class))).thenReturn(false);
        final Response response = serviceResource.getBuonoScontoProgressivoCondizionale("1", "key", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getBuonoScontoProgressivoCondizionale_fieldNotConfigured() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN))).thenReturn(Collections.emptyList());
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getBuonoScontoProgressivoCondizionale("1", "key", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getBuonoScontoProgressivoCondizionale_fieldNotConfiguredAsListaCondizionale() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(cfg.getListaCondizionale()).thenReturn(null);
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN)))
                .thenReturn(Collections.singletonList(cfg));
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getBuonoScontoProgressivoCondizionale("1", "key", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getBuonoScontoProgressivoCondizionale_fieldConfiguredAsWrongListaCondizionale() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(cfg.getListaCondizionale()).thenReturn("[1..10]");
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN)))
                .thenReturn(Collections.singletonList(cfg));
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getBuonoScontoProgressivoCondizionale("1", "key", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getBuonoScontoProgressivoCondizionale_ok() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        final List<String> picklistValues = IntStream.rangeClosed(1, 10).boxed().map(String::valueOf).collect(Collectors.toList());
        final List<Integer> values = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(pianificazione.getBuonoScontoProgressivo()).thenReturn(42);
        when(cfg.getListaCondizionale()).thenReturn("{\"23\": \"[1..10]\", \"24\": \"[20..30]\"}");
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN)))
                .thenReturn(Collections.singletonList(cfg));
        when(pianificazionePromoUtil.getCorrectFormatPickListValues("[1..10]")).thenReturn(picklistValues);
        when(pianificazionePromoUtil.getAvailableProgressiveDiscountCodesBuoniPotenziamento(promozione, 23,
                picklistValues, values)).thenReturn(values);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getBuonoScontoProgressivoCondizionale("1", "23", contesto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getBuonoScontoProgressivo_emptyId() {
        final Response response = serviceResource.getBuonoScontoProgressivo("", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, never()).getPromoPianificazoneById(anyLong());
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getBuonoScontoProgressivo_pianificazioneNotFound() {
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(null);
        final Response response = serviceResource.getBuonoScontoProgressivo("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getBuonoScontoProgressivo_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getBuonoScontoProgressivo("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getBuonoScontoProgressivo_fieldNotConfigured() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN)))
                .thenReturn(Collections.emptyList());
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getBuonoScontoProgressivo("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getBuonoScontoProgressivo_fieldNotConfiguredAsLista() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(cfg.getLista()).thenReturn(null);
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN)))
                .thenReturn(Collections.singletonList(cfg));
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getBuonoScontoProgressivo("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getBuonoScontoProgressivo_ok() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        final List<String> picklistValues = IntStream.rangeClosed(1, 10).boxed().map(String::valueOf).collect(Collectors.toList());
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(pianificazione.getBuonoScontoRadice()).thenReturn(42);
        when(cfg.getLista()).thenReturn("[1..10]");
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN)))
                .thenReturn(Collections.singletonList(cfg));
        when(pianificazionePromoUtil.getCorrectFormatPickListValues("[1..10]")).thenReturn(picklistValues);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getBuonoScontoProgressivo("1", contesto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_PROGRESSIVO_CAMPAGNA_COLUMN));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    @Ignore("Not yet")
    public void calcolaListaConSovrapposizioni() {
    }

    @Test
    @Ignore("Not yet")
    public void getSelezioneArticoloFilteredRowData() {
    }

    @Test
    public void getPlanningRowDataFiltered_badPromoId() throws Exception {
        final Response response = serviceResource.getPlanningRowDataFiltered("bad", "bad",
                "bad", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(planningColumnDef, never()).generateRowDataByPromoConfiguration(
                anyString(), anyBoolean(), anyList(), nullable(String.class));
    }

    @Test
    public void getPlanningRowDataFiltered_promoNotFound() throws Exception {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getPlanningRowDataFiltered("bad", "1",
                "bad", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(planningColumnDef, never()).generateRowDataByPromoConfiguration(
                anyString(), anyBoolean(), anyList(), nullable(String.class));
    }

    @Test
    public void getPlanningRowDataFiltered_promoNotAccessible() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getPlanningRowDataFiltered("bad", "1",
                "bad", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(planningColumnDef, never()).generateRowDataByPromoConfiguration(
                anyString(), anyBoolean(), anyList(), nullable(String.class));
    }

    @Test
    public void getPlanningRowDataFiltered_ok() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(dynamicColumnDefFactory.getDynamicColumnDef("pianificazione")).thenReturn(planningColumnDef);
        when(planningColumnDef.generateRowDataByPromoConfiguration(eq("1"), anyBoolean(), anyList(), nullable(String.class)))
                .thenReturn("ok");
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getPlanningRowDataFiltered("pianificazione", "1",
                "bad", contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(planningColumnDef, times(1))
                .generateRowDataByPromoConfiguration(eq("1"), anyBoolean(), anyList(), nullable(String.class));
    }

    @Test
    public void getClusterCliente_emptyId(){
        final Response response = serviceResource.getClusterCliente("", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, never()).getPromoPianificazoneById(anyLong());
        verify(clusterClienteService, never()).findByDataInizioAndDataFine(any(Date.class),any(Date.class));
    }

    @Test
    public void getNumeroSet_emptyId() {
        final Response response = serviceResource.getNumeroSet("", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, never()).getPromoPianificazoneById(anyLong());
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(numSetUtils, never()).getAvailable(any(), any(), any());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getNumeroSet_pianificazioneNotFound() {
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(null);
        final Response response = serviceResource.getNumeroSet("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(numSetUtils, never()).getAvailable(any(), any(), any());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getNumeroSet_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getNumeroSet("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(numSetUtils, never()).getAvailable(any(), any(), any());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getNumeroSet_fieldNotConfigured() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_NUM_SET_COLUMN)))
                .thenReturn(Collections.emptyList());
        final Response response = serviceResource.getNumeroSet("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_NUM_SET_COLUMN));
        verify(numSetUtils, never()).getAvailable(any(), any(), any());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getNumeroSet_fieldNotConfiguredAsLista() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(cfg.getLista()).thenReturn(null);
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_NUM_SET_COLUMN)))
                .thenReturn(Collections.singletonList(cfg));
        final Response response = serviceResource.getNumeroSet("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_NUM_SET_COLUMN));
        verify(numSetUtils, never()).getAvailable(any(), any(), any());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getNumeroSet_ok() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        final List<Integer> values = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(mock(CfgSetPianificazioneEntity.class));
        when(pianificazione.getMeccanicaEntity()).thenReturn(mock(MeccanicheEntity.class));
        when(cfg.getLista()).thenReturn("[1..10]");
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(configurationService.findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_NUM_SET_COLUMN)))
                .thenReturn(Collections.singletonList(cfg));
        when(numSetUtils.getAvailable(eq(promozione), eq("[1..10]"), any())).thenReturn(values);
        final Response response = serviceResource.getNumeroSet("1", contesto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(configurationService, times(1)).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), eq(PianificazioneConstants.REFERENCE_NUM_SET_COLUMN));
        verify(numSetUtils, times(1)).getAvailable(eq(promozione), eq("[1..10]"), any());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getMeccanicheDisponibili() {
        // TODO
    }

    @Test
    public void getNumeroRaggruppamento_emptyId() {
        final Response response = serviceResource.getNumeroRaggruppamento("", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, never()).getPromoPianificazoneById(anyLong());
        verify(cfgPianificazioneService, never()).findPianificazioneByCanaleMeccanicaField(
                any(CanalePromozioneEntity.class), any(MeccanicheEntity.class), anyString());
        verify(configurationService, never()).findBySetAndMeccanicaAndCampo(any(CfgSetPianificazioneEntity.class),
                any(MeccanicheEntity.class), anyString());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getNumeroRaggruppamento_pianificazioneNotFound() {
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(null);
        final Response response = serviceResource.getNumeroRaggruppamento("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(cfgPianificazioneService, never()).findPianificazioneByCanaleMeccanicaField(
                any(CanalePromozioneEntity.class), any(MeccanicheEntity.class), anyString());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getNumeroRaggruppamento_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(eq(promozione), any(UserDTO.class))).thenReturn(false);
        final Response response = serviceResource.getNumeroRaggruppamento("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(cfgPianificazioneService, never()).findPianificazioneByCanaleMeccanicaField(
                any(CanalePromozioneEntity.class), any(MeccanicheEntity.class), anyString());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getNumeroRaggruppamento_fieldNotConfigured() {
        final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(promozione.getCanalePromozioneEntity()).thenReturn(canale);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(cfgPianificazioneService.findPianificazioneByCanaleMeccanicaField(canale, meccanica,
                PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN))
                .thenReturn(Collections.emptyList());
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getNumeroRaggruppamento("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(cfgPianificazioneService, times(1)).findPianificazioneByCanaleMeccanicaField(canale, meccanica,
                PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN);
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getNumeroRaggruppamento_fieldNotConfiguredAsLista() {
        final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        when(promozione.getCanalePromozioneEntity()).thenReturn(canale);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(cfgPianificazioneService.findPianificazioneByCanaleMeccanicaField(canale, meccanica,
                PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN))
                .thenReturn(Collections.singletonList(cfg));
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getNumeroRaggruppamento("1", contesto);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(cfgPianificazioneService, times(1)).findPianificazioneByCanaleMeccanicaField(canale, meccanica,
                PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN);
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getNumeroRaggruppamento_ok() {
        final MeccanicheEntity meccanica = mock(MeccanicheEntity.class);
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final CfgPianificazioneEntity cfg = mock(CfgPianificazioneEntity.class);
        when(promozione.getCanalePromozioneEntity()).thenReturn(canale);
        when(pianificazione.getMeccanicaEntity()).thenReturn(meccanica);
        when(cfg.getLista()).thenReturn("[1..10]");
        when(pianificazioneService.getPromoPianificazoneById(1L)).thenReturn(pianificazione);
        when(cfgPianificazioneService.findPianificazioneByCanaleMeccanicaField(canale, meccanica,
                PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN))
                .thenReturn(Collections.singletonList(cfg));
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.getNumeroRaggruppamento("1", contesto);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(pianificazioneService, times(1)).getPromoPianificazoneById(1L);
        verify(cfgPianificazioneService, times(1)).findPianificazioneByCanaleMeccanicaField(canale, meccanica,
                PianificazioneConstants.REFERENCE_NUMERO_RAGGRUPPAMENTO_COLUMN);
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void downlodScarti_emptyIdUpload() {
        final Response response = serviceResource.downloadScarti("", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(uploadFidatyService, never()).findById(anyLong());
        verify(complementariUtils, never()).getScarto(anyLong());
    }

    @Test
    public void downlodScarti_badIdUpload() {
        final Response response = serviceResource.downloadScarti("bad", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(uploadFidatyService, never()).findById(anyLong());
        verify(complementariUtils, never()).getScarto(anyLong());
    }

    @Test
    public void downlodScarti_uploadNotFound() {
        when(uploadFidatyService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.downloadScarti("1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(uploadFidatyService, times(1)).findById(1L);
        verify(complementariUtils, never()).getScarto(anyLong());
    }

    @Test
    public void downlodScarti_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final UploadFidayEntity uploadFiday = mock(UploadFidayEntity.class);
        when(uploadFiday.getPromozionePianificazioneEntity()).thenReturn(pianificazione);
        when(uploadFidatyService.findById(1L)).thenReturn(uploadFiday);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.downloadScarti("1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(uploadFidatyService, times(1)).findById(1L);
        verify(complementariUtils, never()).getScarto(anyLong());
    }

    @Test
    public void downlodScarti_cannotPrepareFile() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final UploadFidayEntity uploadFiday = mock(UploadFidayEntity.class);
        when(uploadFiday.getPromozionePianificazioneEntity()).thenReturn(pianificazione);
        when(uploadFidatyService.findById(1L)).thenReturn(uploadFiday);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(complementariUtils.getScarto(1L)).thenReturn(null);
        final Response response = serviceResource.downloadScarti("1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(uploadFidatyService, times(1)).findById(1L);
        verify(complementariUtils, times(1)).getScarto(1L);
    }

    @Test
    public void downlodScarti_ok() {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final UploadFidayEntity uploadFiday = mock(UploadFidayEntity.class);
        final File file = mock(File.class);
        when(file.canRead()).thenReturn(true);
        when(uploadFiday.getPromozionePianificazioneEntity()).thenReturn(pianificazione);
        when(uploadFidatyService.findById(1L)).thenReturn(uploadFiday);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(complementariUtils.getScarto(1L)).thenReturn(file);
        final Response response = serviceResource.downloadScarti("1", contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(uploadFidatyService, times(1)).findById(1L);
        verify(complementariUtils, times(1)).getScarto(1L);
    }

    @Test
    public void whenLoadColumnDefFromFileDoesNotFindColumnDefThenReturnsInternalServerError() {
        Response actual = serviceResource.loadColumnDefFromFile("afile", "agrid", "acontext", true);
        assertEquals(actual.getStatus(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }

    @Test
    public void whenLoadColumnDefFromFileFindsColumnDefThenReturnsOk() {
        Response actual = serviceResource.loadColumnDefFromFile("sampleColumnDef.json", "", "contesto", true);
        assertEquals(actual.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void getRowDataConfermaPianificazioneReturnsPreconditionFailedWhenPromoNotAccessible() {
        PromozioneTestataEntity testata = mockPromozione();
        when(promoService.findById(1L)).thenReturn(testata);
        Response response = serviceResource.getRowDataConfermaPianificazione("1", mockedRequest);
        assertEquals(response.getStatus(), Response.Status.PRECONDITION_FAILED.getStatusCode());
        assertThat(response.getEntity().toString(), CoreMatchers.containsString("Promozione con id 1 non accessibile"));
    }

    @Test
    public void getRowDataConfermaPianificazioneReturnsOkWhenPromoFoundAndAdminUser() {
        PromozioneTestataEntity testata = mockPromozione();
        when(promoService.findById(1L)).thenReturn(testata);
        when(promoSecurity.isAccessible(testata, userDTO)).thenReturn(true);
        when(userDTO.getGruppi()).thenReturn(Collections.singletonList("gruppo"));
        when(serviceResource.isUserAdmin()).thenReturn(true);
        when(pianificazionePromoRowDataUtil.createCompratoriRowData(anyList())).thenReturn("");
        Response response = serviceResource.getRowDataConfermaPianificazione("1", mockedRequest);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        assertThat(response.getEntity().toString(), CoreMatchers.containsString(""));
    }

    @Test
    @Ignore("Not yet")
    public void getPromoRiferimentoArticoliColumnDef() {
    }

    @Test
    @Ignore("Not yet")
    public void getPromoRiferimentoArticoliRowData() {
    }

    @Test
    @Ignore("Not yet")
    public void getPlanoRiferimentoArticoliColumnDef() {
    }

    @Test
    @Ignore("Not yet")
    public void getPlanoRiferimentoArticoliRowData() {
    }

    @Test
    @Ignore("Not yet")
    public void getExtendedArticoliPlano() {
    }

    @Test
    @Ignore("Not yet")
    public void getSovrapposizioniNegoziColumnDef() {
    }

    @Test
    @Ignore("Not yet")
    public void getSovrapposizioniNegoziRowData() {
    }

    private PromozioneTestataEntity mockPromozione() {
//        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
//        final PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        // Maybe not necessary
//        when(promozione.getCanalePromozioneEntity()).thenReturn(canale);
//        return promozione;
        return mock(PromozioneTestataEntity.class);
    }

    private PromozionePianificazioneEntity mockPianificazione(PromozioneTestataEntity promozione) {
        final PromozionePianificazioneEntity mock = mock(PromozionePianificazioneEntity.class);
        when(mock.getPromozioneTestataEntity()).thenReturn(promozione);
        return mock;
    }

    private String asPayload(Map map) throws Exception {
        return JsonUtils.writeValueAsString(map);
    }

    private Map<String, Object> createPayload(){
        Map<String, Object> payload = new HashMap<>();
        DBPromoAgCell cell = new DBPromoAgCell();
        cell.setValue("somecolumn");
        payload.put("columnToBeUpdated", cell);
        cell = new DBPromoAgCell();
        cell.setValue("somevalue");
        payload.put("valueToBeUpdated", cell);
        cell = new DBPromoAgCell();
        cell.setValue("1");
        payload.put("idPromoPianificazione", cell);
        cell = new DBPromoAgCell();
        cell.setValue("someHeader");
        payload.put("columnHeaderToBeUpdated", cell);
        return  payload;
    }
}