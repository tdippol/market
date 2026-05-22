package com.axiante.mui.webapp.webservice;

import com.axiante.mui.common.utility.DateTimeUtils;
import com.axiante.mui.dbpromo.business.enumeration.PromoStatusEnum;
import com.axiante.mui.dbpromo.persistence.entities.CanalePromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.CfgSetPianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.GruppoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromoPubblicazioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneMeccanicheEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneNegozioEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneStatoEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTipoTerminaleEntity;
import com.axiante.mui.dbpromo.persistence.entities.RepartoEntity;
import com.axiante.mui.dbpromo.persistence.entities.StatoPromozioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.TipoTerminaleCassaEntity;
import com.axiante.mui.dbpromo.persistence.service.CreatePromotionService;
import com.axiante.mui.dbpromo.persistence.service.NegoziPromoService;
import com.axiante.mui.dbpromo.persistence.service.PromoPubblicazioneTestataService;
import com.axiante.mui.dbpromo.persistence.service.PromoService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.validator.model.Promotion;
import com.axiante.mui.validator.service.PromotionValidatorService;
import com.axiante.mui.webapp.business.OwnershipService;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.security.PromoSecurity;
import com.axiante.mui.webapp.webservice.util.CreaPromozioneFactory;
import com.axiante.mui.webapp.webservice.util.PromoShopsUtil;
import com.axiante.mui.webapp.webservice.util.PromozioneTestataHelper;
import com.axiante.mui.webapp.webservice.util.SchedaPromoUtil;
import com.axiante.mui.webapp.webservice.util.ViewPromoUtil;
import com.axiante.mui.webapp.webservice.validator.PromozioneTestataValidator;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anySet;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.nullable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GridServiceResourceTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    private String contesto = "testContext";

    @Mock
    HttpSession session;

    @Mock
    UserService userService;

    @Mock
    private HttpServletRequest mockedRequest;

    @Mock
    private PromoSecurity promoSecurity;

    @Mock
    private PromoService promoService;

    @Mock
    private UserDTO userDTO;

    @Mock
    private UsersEntity usersEntity;

    @Mock
    private OwnershipService ownershipService;

    @Mock
    private PromotionValidatorService promotionValidatorService;

    @Mock
    private PromozioneTestataValidator promozioneTestataValidator;

    @Mock
    private ViewPromoUtil viewPromoUtil;

    @Mock
    private PromoShopsUtil promoShopsUtil;

    @Mock
    private SchedaPromoUtil schedaPromoUtil;

    @Mock
    private PromozioneTestataHelper promozioneTestataHelper;

    @Mock
    private CreaPromozioneFactory creaPromozioneFactory;

    @Mock
    private CreatePromotionService createPromotionService;
    @Mock
    private NegoziPromoService negoziPromoService;

    @Mock
    private PromoPubblicazioneTestataService promoPubblicazioneTestataService;

    @Spy
    @InjectMocks
    private GridServiceResource serviceResource;

    @Before
    public void setUp() throws Exception {
        when(usersEntity.getName()).thenReturn("junit");
        when(serviceResource.getCurrentUser()).thenReturn(usersEntity);
        when(serviceResource.getApplicationUser(contesto)).thenReturn(userDTO);
        when(serviceResource.getApplicationUser(nullable(String.class))).thenReturn(userDTO);
        when(userDTO.getCanali()).thenReturn(Collections.emptyList());
        when(userDTO.getGruppi()).thenReturn(Collections.emptyList());
        when(userDTO.getUser()).thenReturn(usersEntity);
        when(usersEntity.getName()).thenReturn("Mockito");
    }

    @Test
    public void getColumnDefRiepilogo() {
        serviceResource.getColumnDefRiepilogo(contesto, mockedRequest);
        verify(serviceResource, times(1))
                .loadColumnDefFromFile("columnDefVisualizza.json", "db_grid_visualizzaPromo", contesto);
    }

    @Test
    public void getRowDataRiepilogo() {
        when(viewPromoUtil.createRowData(anyList(), anyList(), anyBoolean())).thenReturn("ok");
        final Response response = serviceResource.getRowDataRiepilogo(contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(viewPromoUtil, times(1)).createRowData(anyList(), anyList(), anyBoolean());
    }

    @Test
    public void getColumnDefCreaPromozione() {
        serviceResource.getColumnDefCreaPromozione(contesto, mockedRequest);
        verify(serviceResource, times(1))
                .loadColumnDefFromFile("columnDefCreaPromo.json", "db_grid_creaPromo", contesto);
    }

    @Test
    public void getRowDataCreaPromozione() {
        assertNotNull(serviceResource.getRowDataRiepilogo(contesto, mockedRequest));
    }

    @Test
    public void getRowData() {
        when(serviceResource.getApplicationUser(null)).thenReturn(userDTO);
        serviceResource.getRowData(mockedRequest);
        verify(promoShopsUtil, times(1))
                .populateRowData(nullable(String.class), nullable(String.class), anyList(), anyList(), anyBoolean());
    }


    @Test
    public void changeView_badRadioCheck() {
        Response response = serviceResource.changeView("foo", "bar", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        response = serviceResource.changeView(null, "bar", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void changeView_noPromo_allShop() {
        final Response response = serviceResource.changeView("ALL", null, contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void changeView_noPromo_modifiedShop() {
        final Response response = serviceResource.changeView("ALL", null, contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void changeView_badPromoId() {
        final Response response = serviceResource.changeView("ALL", "bad", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void changeView_promoNotFound() {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.changeView("ALL", "1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void changeView_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.changeView("ALL", "1", contesto, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void changeView_promoAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.changeView("ALL", "1", contesto, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }


    @Test
    public void getRowDataSchedaPromoStati_badPromoId() {
        Response response = serviceResource.getRowDataSchedaPromoStati("foo", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createStatiRowData(anySet());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoStati_promoNotFound() {
        when(promoService.findById(1L)).thenReturn(null);
        Response response = serviceResource.getRowDataSchedaPromoStati("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createStatiRowData(anySet());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoStati_promoNotAccessible() {
        PromozioneTestataEntity promozione = mockPromozione();
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        when(promoService.findById(1L)).thenReturn(promozione);
        Response response = serviceResource.getRowDataSchedaPromoStati("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createStatiRowData(anySet());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoStati_ok() {
        PromozioneTestataEntity promozione = mockPromozione();
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(promoService.findById(1L)).thenReturn(promozione);
        when(schedaPromoUtil.createStatiRowData(anySet())).thenReturn("ok");
        Response response = serviceResource.getRowDataSchedaPromoStati("1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(schedaPromoUtil, times(1)).createStatiRowData(anySet());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }


    @Test
    public void getRowDataSchedaPromoPubblicazioni_badPromoId() throws Exception {
        final Response response = serviceResource.getRowDataSchedaPromoPubblicazioni("bad", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoPubblicazioneTestataService, never()).findByPromoID(any(Long.class));
        verify(schedaPromoUtil, never()).createPubblicazioniRowData(anyList());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getRowDataSchedaPromoPubblicazioni_promoNotFound() throws Exception {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getRowDataSchedaPromoPubblicazioni("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoPubblicazioneTestataService, never()).findByPromoID(any(Long.class));
        verify(schedaPromoUtil, never()).createPubblicazioniRowData(anyList());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getRowDataSchedaPromoPubblicazioni_promoNotAccessible() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getRowDataSchedaPromoPubblicazioni("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoPubblicazioneTestataService, never()).findByPromoID(any(Long.class));
        verify(schedaPromoUtil, never()).createPubblicazioniRowData(anyList());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoPubblicazioni_ok() throws Exception {
        final List<PromoPubblicazioneTestataEntity> pubblicazioni = Collections.singletonList(mock(PromoPubblicazioneTestataEntity.class));
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(promoPubblicazioneTestataService.findByPromoID(1L)).thenReturn(pubblicazioni);
        when(schedaPromoUtil.createPubblicazioniRowData(pubblicazioni)).thenReturn("ok");
        final Response response = serviceResource.getRowDataSchedaPromoPubblicazioni("1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(promoPubblicazioneTestataService, times(1)).findByPromoID(any(Long.class));
        verify(schedaPromoUtil, times(1)).createPubblicazioniRowData(pubblicazioni);
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getColumnDefSchedaPromoPubblicazioni() {
    }


    @Test
    public void getRowDataSchedaPromoControlli_badPromoId() {
        final Response response = serviceResource.getRowDataSchedaPromoControlli("bad", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createControlliRowData(any(PromozioneTestataEntity.class));
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getRowDataSchedaPromoControlli_promoNotFound() {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getRowDataSchedaPromoControlli("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createControlliRowData(any(PromozioneTestataEntity.class));
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getRowDataSchedaPromoControlli_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getRowDataSchedaPromoControlli("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createControlliRowData(any(PromozioneTestataEntity.class));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoControlli_ok() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(schedaPromoUtil.createControlliRowData(promozione)).thenReturn("ok");
        final Response response = serviceResource.getRowDataSchedaPromoControlli("1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(schedaPromoUtil, times(1)).createControlliRowData(promozione);
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }


    @Test
    public void getRowDataSchedaPromoOwnership_badPromoId() {
        final Response response = serviceResource.getRowDataSchedaPromoOwnership("bad", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createOwnerRowData(any(PromozioneTestataEntity.class));
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getRowDataSchedaPromoOwnership_promoNotFound() {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getRowDataSchedaPromoOwnership("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createOwnerRowData(any(PromozioneTestataEntity.class));
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
    }

    @Test
    public void getRowDataSchedaPromoOwnership_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getRowDataSchedaPromoOwnership("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createOwnerRowData(any(PromozioneTestataEntity.class));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoOwnership_ok() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(schedaPromoUtil.createOwnerRowData(promozione)).thenReturn("ok");
        final Response response = serviceResource.getRowDataSchedaPromoOwnership("1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(schedaPromoUtil, times(1)).createOwnerRowData(promozione);
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void updatePromoTestata_badPayload() {
        final Response response = serviceResource.updatePromoTestata("bad-payload", null, mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void updatePromoTestata_badPromoId() {
        String payload = "{\"id\":{\"value\":\"foo\"}}";
        final Response response = serviceResource.updatePromoTestata(payload, null, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void updatePromoTestata_promoNotFound() {
        String payload = "{\"id\":{\"value\":\"1\"}}";
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.updatePromoTestata(payload, null, mockedRequest);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void updatePromoTestata_promoNotAccessible() {
        String payload = "{\"id\":{\"value\":\"1\"}}";
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.updatePromoTestata(payload, null, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void updatePromoTestata_promoNonEditabile() {
        final Date date1 = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        final Date date2 = new GregorianCalendar(2022, Calendar.AUGUST, 10).getTime();
        String payload = mockCreaTestataJson(date1, date2);
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(ownershipService.hasOwnership(eq(promozione), anyList())).thenReturn(true);
        final Response response = serviceResource.updatePromoTestata(payload, null, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void updatePromoTestata_ok() {
        final Date date1 = new GregorianCalendar(2022, Calendar.AUGUST, 1).getTime();
        final Date date2 = new GregorianCalendar(2022, Calendar.AUGUST, 10).getTime();
        String payload = mockCreaTestataJson(date1, date2);
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneStatoEntity promoStato = mockPromozioneStato(null);
        when(promozione.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
        when(promozione.getDataInizio()).thenReturn(date1);
        when(promozione.getDataFine()).thenReturn(date2);
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(ownershipService.hasOwnership(eq(promozione), anyList())).thenReturn(true);
        when(promotionValidatorService.validateEditPromotion(any(Promotion.class), eq(promozione)))
                .thenReturn(null);
        lenient().when(promozioneTestataValidator.validateDates(eq(promozione), any(Date.class), any(Date.class)))
                .thenReturn(null);
        final Response response = serviceResource.updatePromoTestata(payload, null, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoTestata_badPayload() {
        final Response response = serviceResource.deletePromoTestata("bad-payload", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoTestata_badPromoId() {
        String payload = "{\"id\":{\"value\":\"foo\"}}";
        final Response response = serviceResource.deletePromoTestata(payload, mockedRequest);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoTestata_promoNotFound() {
        String payload = "{\"id\":{\"value\":\"1\"}}";
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.deletePromoTestata(payload, mockedRequest);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoTestata_promoNotAccessible() {
        String payload = "{\"id\":{\"value\":\"1\"}}";
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.deletePromoTestata(payload, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoTestata_promoNotDeletable() {
        String payload = "{\"id\":{\"value\":\"1\"}}";
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(ownershipService.hasOwnership(eq(promozione), anyList())).thenReturn(true);
        when(promozioneTestataHelper.delete(eq(promozione), anyString())).thenReturn(false);
        final Response response = serviceResource.deletePromoTestata(payload, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoTestata_ok() {
        String payload = "{\"id\":{\"value\":\"1\"}}";
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(ownershipService.hasOwnership(eq(promozione), anyList())).thenReturn(true);
        when(promozioneTestataHelper.delete(eq(promozione), anyString())).thenReturn(true);
        final Response response = serviceResource.deletePromoTestata(payload, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void savePromoHeaders_badRequest() {
        Response response = serviceResource.savePromoHeaders(contesto, "bad-payload", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        String payload = "{\"id\":{\"value\":\"1\"}}";
        response = serviceResource.savePromoHeaders(contesto, payload, mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void savePromoHeaders_promoNotAccessible() {
        String payload = mockCreaTestataJson(null, null);
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        when(creaPromozioneFactory.isAllCellsPopulated(any(JsonNode.class))).thenReturn(true);
        when(creaPromozioneFactory.isRowNodeEmpty(any(JsonNode.class))).thenReturn(false);
        when(creaPromozioneFactory.build(any(PromozioneTestataEntity.class), any(JsonNode.class), eq(userDTO)))
                .thenReturn(promozione);
        final Response response = serviceResource.savePromoHeaders(contesto, "[" + payload + "]", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void savePromoHeaders_ok() throws Exception {
        String payload = mockCreaTestataJson(null, null);
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promozione.getCodicePromozione()).thenReturn("FOO");
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(promozione.getMuiCanalePromozione()).thenReturn(canale);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(creaPromozioneFactory.isAllCellsPopulated(any(JsonNode.class))).thenReturn(true);
        when(creaPromozioneFactory.isRowNodeEmpty(any(JsonNode.class))).thenReturn(false);
        when(creaPromozioneFactory.build(any(PromozioneTestataEntity.class), any(JsonNode.class), eq(userDTO)))
                .thenReturn(promozione);
        lenient().when(promotionValidatorService.validateNewPromotion(eq(promozione), anyList(), any(GruppoPromozioneEntity.class)))
                .thenReturn(null);
        when(promoService.persist(eq(promozione), anyString())).thenReturn(promozione);
        doNothing().when(creaPromozioneFactory).deleteRow(any(JsonNode.class), anyString());
        when(createPromotionService.runFunctionCountTestate(any(Long.class), any(Long.class), nullable(Date.class), nullable(Date.class))).thenReturn(true);
        when(createPromotionService.runFunctionCountTestate(any(Long.class), nullable(Date.class), nullable(Date.class), any(Long.class))).thenReturn(true);
        final Response response = serviceResource.savePromoHeaders(contesto, "[" + payload + "]", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoService, times(1)).persist(eq(promozione), anyString());
        verify(creaPromozioneFactory, times(1)).deleteRow(any(JsonNode.class), anyString());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void savePromoHeader_badRequest() {
        final Response response = serviceResource.savePromoHeader(contesto, "bad-payload", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void savePromoHeader_promoNotAccessible() {
        String payload = mockCreaTestataJson(null, null);
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        when(creaPromozioneFactory.build(any(PromozioneTestataEntity.class), any(JsonNode.class), eq(userDTO)))
                .thenReturn(promozione);
        final Response response = serviceResource.savePromoHeader(contesto, payload, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void savePromoHeader_ok() throws Exception {
        String payload = mockCreaTestataJson(null, null);
        final CfgSetPianificazioneEntity cfgSetPianificazione = mock(CfgSetPianificazioneEntity.class);
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promozione.getCodicePromozione()).thenReturn("FOO");
        when(promozione.getMuiCfgSetPianificazione()).thenReturn(cfgSetPianificazione);
        when(promozione.getMuiCanalePromozione()).thenReturn(canale);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(creaPromozioneFactory.build(any(PromozioneTestataEntity.class), any(JsonNode.class), eq(userDTO)))
                .thenReturn(promozione);
        lenient().when(promotionValidatorService.validateNewPromotion(eq(promozione), anyList(), any(GruppoPromozioneEntity.class)))
                .thenReturn(null);
        when(promoService.persist(eq(promozione), anyString())).thenReturn(promozione);
        doNothing().when(creaPromozioneFactory).deleteRow(any(JsonNode.class), anyString());
        when(createPromotionService.runFunctionCountTestate(any(Long.class), any(Long.class), nullable(Date.class), nullable(Date.class))).thenReturn(true);
        when(createPromotionService.runFunctionCountTestate(any(Long.class), nullable(Date.class), nullable(Date.class), any(Long.class))).thenReturn(true);
        final Response response = serviceResource.savePromoHeader(contesto, payload, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoService, times(1)).persist(eq(promozione), anyString());
        verify(creaPromozioneFactory, times(1)).deleteRow(any(JsonNode.class), anyString());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }


    @Test
    public void updatePromoShop_badPayload() throws Exception {
        ex.expect(Exception.class);
        serviceResource.updatePromoShop("ALL", "bad-payload", mockedRequest);
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void updatePromoShop_badParam() throws Exception {
        final String payload = "{\"foo\":\"bar\"}";
        final Response response = serviceResource.updatePromoShop(null, payload, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void updatePromoShop_shopIdNotPresent() throws Exception {
        final String payload = "{\"promoShopId\":\"\"}";
        final Response response = serviceResource.updatePromoShop("ALL", payload, mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void updatePromoShop_shopWithoutPromozione() throws Exception {
        final String payload = "{\"promoShopId\": {\"value\": \"1\"} }";
        final PromozioneNegozioEntity promoNegozio = mock(PromozioneNegozioEntity.class);
        when(promoNegozio.getPromozioneTestataEntity()).thenReturn(null);
        when(negoziPromoService.findById(1L)).thenReturn(promoNegozio);
        final Response response = serviceResource.updatePromoShop("ALL", payload, mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void updatePromoShop_shopWithPromozioneNotAccessible() throws Exception {
        final String payload = "{\"promoShopId\": {\"value\": \"1\"} }";
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneNegozioEntity promoNegozio = mock(PromozioneNegozioEntity.class);
        when(promoNegozio.getPromozioneTestataEntity()).thenReturn(promozione);
        when(negoziPromoService.findById(1L)).thenReturn(promoNegozio);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.updatePromoShop("ALL", payload, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void updatePromoShop_shopWithPromozioneNotEditable() throws Exception {
        final String payload = "{\"promoShopId\": {\"value\": \"1\"} }";
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneNegozioEntity promoNegozio = mock(PromozioneNegozioEntity.class);
        when(promoNegozio.getPromozioneTestataEntity()).thenReturn(promozione);
        when(negoziPromoService.findById(1L)).thenReturn(promoNegozio);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(ownershipService.hasOwnership(eq(promozione), anyList())).thenReturn(false);
        final Response response = serviceResource.updatePromoShop("ALL", payload, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void updatePromoShop_updateFailed() throws Exception {
        final String payload = "{\"promoShopId\": {\"value\": \"1\"} }";
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneNegozioEntity promoNegozio = mock(PromozioneNegozioEntity.class);
        when(promoNegozio.getPromozioneTestataEntity()).thenReturn(promozione);
        final PromozioneStatoEntity promoStato = mockPromozioneStato(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey());
        when(promozione.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
        when(negoziPromoService.findById(1L)).thenReturn(promoNegozio);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(ownershipService.hasOwnership(eq(promozione), anyList())).thenReturn(true);
        when(promoShopsUtil.validateUpdatedRow(any(JsonNode.class))).thenReturn(false);
        final Response response = serviceResource.updatePromoShop("ALL", payload, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void updatePromoShop_ok() throws Exception {
        final String payload = mockUpdatePromoShopJson();
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneNegozioEntity promoNegozio = mock(PromozioneNegozioEntity.class);
        when(promoNegozio.getPromozioneTestataEntity()).thenReturn(promozione);
        final PromozioneStatoEntity promoStato = mockPromozioneStato(PromoStatusEnum.TESTATA_PROMOZIONE_CREATA.getKey());
        when(promozione.getPromozioneStatoEntities()).thenReturn(Collections.singleton(promoStato));
        when(negoziPromoService.findById(1L)).thenReturn(promoNegozio);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(ownershipService.hasOwnership(eq(promozione), anyList())).thenReturn(true);
        when(promoShopsUtil.validateUpdatedRow(any(JsonNode.class))).thenReturn(true);
        when(negoziPromoService.savePromozioneNegozioEntity(promoNegozio)).thenReturn(promoNegozio);
        final Response response = serviceResource.updatePromoShop("ALL", payload, mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(negoziPromoService, times(1)).savePromozioneNegozioEntity(promoNegozio);
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoMeccaniche_noPromoId() {
        assertNull(serviceResource.getRowDataSchedaPromoMeccaniche(null, mockedRequest));
        assertNull(serviceResource.getRowDataSchedaPromoMeccaniche("", mockedRequest));
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoMeccaniche_badPromoId() {
        ex.expect(Exception.class);
        serviceResource.getRowDataSchedaPromoMeccaniche("bad", mockedRequest);
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoMeccaniche_promoNotFound() {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getRowDataSchedaPromoMeccaniche("1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoMeccaniche_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getRowDataSchedaPromoMeccaniche("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoMeccaniche_ok() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(schedaPromoUtil.createMeccanicheRowData(eq(promozione), anyList(), eq(false))).thenReturn("ok");
        final Response response = serviceResource.getRowDataSchedaPromoMeccaniche("1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(schedaPromoUtil, times(1))
                .createMeccanicheRowData(eq(promozione), anyList(), eq(false));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoReparti_noPromoId() {
        assertNull(serviceResource.getRowDataSchedaPromoReparti(null, mockedRequest));
        assertNull(serviceResource.getRowDataSchedaPromoReparti("", mockedRequest));
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoReparti_badPromoId() {
        ex.expect(Exception.class);
        serviceResource.getRowDataSchedaPromoReparti("bad", mockedRequest);
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoReparti_PromoNotFound() {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getRowDataSchedaPromoReparti("1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoReparti_PromoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getRowDataSchedaPromoReparti("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoReparti_ok() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(promoService.findById(1L)).thenReturn(promozione);
        when(schedaPromoUtil.createRepartiRowData(eq(promozione), anyList(), eq(false))).thenReturn("ok");
        final Response response = serviceResource.getRowDataSchedaPromoReparti("1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(schedaPromoUtil, times(1))
                .createRepartiRowData(eq(promozione), anyList(), eq(false));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoTipoCassa_noPromoId() {
        assertNull(serviceResource.getRowDataSchedaPromoTipoCassa(null, mockedRequest));
        assertNull(serviceResource.getRowDataSchedaPromoTipoCassa("", mockedRequest));
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoTipoCassa_badPromoId() {
        ex.expect(Exception.class);
        serviceResource.getRowDataSchedaPromoTipoCassa("bad", mockedRequest);
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoTipoCassa_promoNotFound() {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.getRowDataSchedaPromoTipoCassa("1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void getRowDataSchedaPromoTipoCassa_promoNotAccessible() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.getRowDataSchedaPromoTipoCassa("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void getRowDataSchedaPromoTipoCassa_ok() {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(schedaPromoUtil.createTipoCassaRowData(eq(promozione), anyList(), eq(false))).thenReturn("ok");
        final Response response = serviceResource.getRowDataSchedaPromoTipoCassa("1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(schedaPromoUtil, times(1)).createTipoCassaRowData(eq(promozione), anyList(), eq(false));
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoReparto_badPayload() throws Exception {
        final Response response = serviceResource.deletePromoReparto("1", "bad-payload", mockedRequest);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoReparto_badPromoId() throws Exception {
        final Response response = serviceResource.deletePromoReparto("bad", "foo=bar", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoReparto_badRepartoId() throws Exception {
        final Response response = serviceResource.deletePromoReparto("1", "foo=bar", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoReparto_promoNotFound() throws Exception {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.deletePromoReparto("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoReparto_promoNotAccessible() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.deletePromoReparto("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoReparto_repartoNotFound() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        final RepartoEntity reparto = mockReparto(2L);
        when(promozione.getReparti()).thenReturn(Collections.singleton(reparto));
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.deletePromoReparto("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoReparto_cannotPersist() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        final RepartoEntity reparto = mockReparto(1L);
        when(promozione.getReparti()).thenReturn(Collections.singleton(reparto));
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(promoService.persist(eq(promozione), anyString())).thenThrow(Exception.class);
        final Response response = serviceResource.deletePromoReparto("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, times(1)).persist(eq(promozione), anyString());
        verify(schedaPromoUtil, never()).createRepartiRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoReparto_ok() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        final RepartoEntity reparto = mockReparto(1L);
        when(promozione.getReparti()).thenReturn(Collections.singleton(reparto));
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(promoService.persist(eq(promozione), anyString())).thenReturn(promozione);
        when(schedaPromoUtil.createRepartiRowData(eq(promozione), anyList(), anyBoolean())).thenReturn("ok");
        final Response response = serviceResource.deletePromoReparto("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(promoService, times(1)).persist(eq(promozione), anyString());
        verify(schedaPromoUtil, times(1)).createRepartiRowData(eq(promozione), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoMeccanica_badPayload() throws Exception {
        final Response response = serviceResource.deletePromoMeccanica("1", "bad-payload", mockedRequest);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoMeccanica_badPromoId() throws Exception {
        final Response response = serviceResource.deletePromoMeccanica("bad", "foo=bar", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoMeccanica_badMeccanicaId() throws Exception {
        final Response response = serviceResource.deletePromoMeccanica("1", "foo=bar", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoMeccanica_promoNotFound() throws Exception {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.deletePromoMeccanica("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoMeccanica_promoNotAccessible() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.deletePromoMeccanica("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoMeccanica_meccanicaNotFound() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneMeccanicheEntity promoMeccanica = mockPromoMeccanica(2L);
        when(promozione.getPromozioneMeccanicheEntities()).thenReturn(Collections.singleton(promoMeccanica));
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.deletePromoMeccanica("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoMeccanica_cannotPersist() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneMeccanicheEntity promoMeccanica = mockPromoMeccanica(1L);
        when(promozione.getPromozioneMeccanicheEntities()).thenReturn(Collections.singleton(promoMeccanica));
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(promoService.persist(eq(promozione), anyString())).thenThrow(Exception.class);
        final Response response = serviceResource.deletePromoMeccanica("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, times(1)).persist(eq(promozione), anyString());
        verify(schedaPromoUtil, never()).createMeccanicheRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoMeccanica_ok() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneMeccanicheEntity promoMeccanica = mockPromoMeccanica(1L);
        when(promozione.getPromozioneMeccanicheEntities()).thenReturn(Collections.singleton(promoMeccanica));
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(promoService.persist(eq(promozione), anyString())).thenReturn(promozione);
        when(schedaPromoUtil.createMeccanicheRowData(eq(promozione), anyList(), anyBoolean())).thenReturn("ok");
        final Response response = serviceResource.deletePromoMeccanica("1", "foo=1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(promoService, times(1)).persist(eq(promozione), anyString());
        verify(schedaPromoUtil, times(1)).createMeccanicheRowData(eq(promozione), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoTipoCassa_badPayload() throws Exception {
        final Response response = serviceResource.deletePromoTipoCassa(1L, "bad-payload", mockedRequest);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoTipoCassa_badTipoCassa() throws Exception {
        final Response response = serviceResource.deletePromoTipoCassa(1L, "foo=bar", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoTipoCassa_promoNotFound() throws Exception {
        when(promoService.findById(1L)).thenReturn(null);
        final Response response = serviceResource.deletePromoTipoCassa(1L, "foo=1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), eq(userDTO));
    }

    @Test
    public void deletePromoTipoCassa_promoNotAccessible() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.deletePromoTipoCassa(1L, "foo=1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoTipoCassa_tipoCassaNotFound() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneTipoTerminaleEntity promoTipoCassa = mockPromoTipoCassa(2L);
        when(promozione.getPromozioneTipiTerminaleCassa()).thenReturn(Collections.singleton(promoTipoCassa));
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        final Response response = serviceResource.deletePromoTipoCassa(1L, "foo=1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, never()).persist(any(PromozioneTestataEntity.class), anyString());
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoTipoCassa_cannotPersist() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneTipoTerminaleEntity promoTipoCassa = mockPromoTipoCassa(1L);
        when(promozione.getPromozioneTipiTerminaleCassa()).thenReturn(Collections.singleton(promoTipoCassa));
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(promoService.persist(eq(promozione), anyString())).thenThrow(Exception.class);
        final Response response = serviceResource.deletePromoTipoCassa(1L, "foo=1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoService, times(1)).persist(eq(promozione), anyString());
        verify(schedaPromoUtil, never()).createTipoCassaRowData(any(PromozioneTestataEntity.class), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }

    @Test
    public void deletePromoTipoCassa_ok() throws Exception {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozioneTipoTerminaleEntity promoTipoCassa = mockPromoTipoCassa(1L);
        when(promozione.getPromozioneTipiTerminaleCassa()).thenReturn(Collections.singleton(promoTipoCassa));
        when(promoService.findById(1L)).thenReturn(promozione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(promoService.persist(eq(promozione), anyString())).thenReturn(promozione);
        when(schedaPromoUtil.createTipoCassaRowData(eq(promozione), anyList(), anyBoolean())).thenReturn("ok");
        final Response response = serviceResource.deletePromoTipoCassa(1L, "foo=1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("ok", response.getEntity().toString());
        verify(promoService, times(1)).persist(eq(promozione), anyString());
        verify(schedaPromoUtil, times(1)).createTipoCassaRowData(eq(promozione), anyList(), anyBoolean());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
    }



    private PromozioneTestataEntity mockPromozione() {
        final CanalePromozioneEntity canale = mock(CanalePromozioneEntity.class);
        final PromozioneTestataEntity promozione = mock(PromozioneTestataEntity.class);
        when(promozione.getCanalePromozioneEntity()).thenReturn(canale);
        return promozione;
    }

    private PromozioneStatoEntity mockPromozioneStato(String codiceStato) {
        final StatoPromozioneEntity statoPromo = mock(StatoPromozioneEntity.class);
        if (codiceStato != null) {
            when(statoPromo.getCodiceStato()).thenReturn(codiceStato);
        }
        final PromozioneStatoEntity mock = mock(PromozioneStatoEntity.class);
        when(mock.getStatoPromozioneEntity()).thenReturn(statoPromo);
        return mock;
    }

    private RepartoEntity mockReparto(Long id) {
        final RepartoEntity mock = mock(RepartoEntity.class);
        when(mock.getId()).thenReturn(id);
        return mock;
    }

    private PromozioneMeccanicheEntity mockPromoMeccanica(Long id) {
        final PromozioneMeccanicheEntity mock = mock(PromozioneMeccanicheEntity.class);
        when(mock.getId()).thenReturn(id);
        return mock;
    }

    private PromozioneTipoTerminaleEntity mockPromoTipoCassa(Long id) {
        final TipoTerminaleCassaEntity tipoTerminaleCassa = mock(TipoTerminaleCassaEntity.class);
        when(tipoTerminaleCassa.getId()).thenReturn(id);
        final PromozioneTipoTerminaleEntity mock = mock(PromozioneTipoTerminaleEntity.class);
        when(mock.getTipoTerminaleCassaEntity()).thenReturn(tipoTerminaleCassa);
        return mock;
    }

    private String mockCreaTestataJson(Date dataInizio, Date dataFine) {
        final DateTimeUtils dtUtils = new DateTimeUtils();
        String s = String.format("{"
                        + "'id': { 'value': '1' },"
                        + "'dataInizio': { 'value': '%s' },"
                        + "'newDataInizio': { 'value': '' },"
                        + "'dataFine': { 'value': '%s' },"
                        + "'newDataFine': { 'value': '' },"
                        + "'anno': { 'value': '2022' },"
                        + "'newDescrizione': { 'value': '' },"
                        + "'newNoteMarketing': { 'value': '' },"
                        + "'oraInizio': { 'value': '' },"
                        + "'newOraInizio': { 'value': '' },"
                        + "'oraFine': { 'value': '' },"
                        + "'newOraFine': { 'value': '' },"
                        + "'messaggio': { 'value': '' }"
                        + "}",
                dataInizio != null ? dtUtils.toExcelDate(dataInizio) : "",
                dataFine != null ? dtUtils.toExcelDate(dataFine) : "");
        return s.replaceAll("'", "\"");
    }

    private String mockUpdatePromoShopJson() {
        String s = "{"
                + "'promoShopId': { 'value': '1' },"
                + "'dataInizio': { 'value': '' },"
                + "'dataFine': { 'value': '' },"
                + "'flag': { 'value': '' },"
                + "'codiceMeccanica': { 'value': 'M800' }"
                + "}";
        return s.replaceAll("'", "\"");
    }

//TODO: questo e' un lavoro per superman
//    @Test
//    @Ignore("Not yet")
//    public void getSelezionaPromozioniRowData() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getSelezionaPlanoRowData() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getSelezionaPromozioniColumnDef() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getSelezionaPlanogrammiColumnDef() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getPlanogrammiColumnDef() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getPlanogrammiRowData() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getRowDataEdit() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDefEdit() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDef() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDefSchedaPromoStati() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDefSchedaAddPromoBuyers() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDefSchedaPromoBuyers() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDefSchedaPromoControlli() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDefSchedaPromoOwnership() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void savePromotion() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void findChannelsByGroup() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void bulkUpdateFlag() {
//    }
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDefSchedaPromoMeccaniche() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDefSchedaPromoReparti() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getColumnDefSchedaPromoTipoCassa() {
//    }
//    @Test
//    @Ignore("Not yet")
//    public void saveGridSorting() {
//    }
//
//    @Test
//    @Ignore("Not yet")
//    public void getGridSorting() {
//    }
//    @Test
//    @Ignore("Not yet")
//    public void getApplicationProperties() {
//    }

}