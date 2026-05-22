package com.axiante.mui.webapp.webservice;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.persistence.entities.PianificazionePianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.entities.PianoMediaEntity;
import com.axiante.mui.dbpromo.persistence.service.PianificazionePianoMediaService;
import com.axiante.mui.dbpromo.persistence.service.PianoMediaService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.PianoMediaSecurityUtils;
import com.axiante.mui.webapp.business.service.PianoMediaHelperService;
import com.axiante.mui.webapp.webservice.dto.CreatePianificazioneDto;
import com.axiante.mui.webapp.webservice.dto.PianificazioniPianoDto;
import com.axiante.mui.webapp.webservice.util.PianificazionePianoFactory;
import javax.enterprise.inject.Instance;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PianificazionePianoMediaResourceTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Mock
    HttpSession session;

    @Mock
    private UsersEntity usersEntity;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Instance<PianoMediaService> pianoMediaServiceInstance;

    @Mock
    private PianoMediaService pianoMediaService;

    @Mock
    private Instance<PianificazionePianoMediaService> pianificazionePianoMediaServiceInstance;

    @Mock
    private PianificazionePianoMediaService pianificazionePianoMediaService;

    @Mock
    private Instance<PianificazionePianoFactory> pianificazionePianoFactoryInstance;

    @Mock
    private PianificazionePianoFactory pianificazionePianoFactory;

    @Mock
    private Instance<PianoMediaHelperService> pianoMediaHelperServiceInstance;

    @Mock
    private PianoMediaHelperService pianoMediaHelperService;

    @Mock
    private Instance<PianoMediaSecurityUtils> securityUtilsInstance;

    @Mock
    private PianoMediaSecurityUtils securityUtils;

    @Mock
    private PianoMediaEntity pianoMedia;

    @Mock
    private CreatePianificazioneDto createPianificazioneDto;

    @Mock
    private PianificazioniPianoDto pianificazioniPianoDto;

    @Spy
    @InjectMocks
    private PianificazionePianoMediaResource ws;

    @Before
    public void setUp() throws Exception {
        when(ws.getCurrentUser()).thenReturn(usersEntity);
        when(usersEntity.getName()).thenReturn("junit");
        when(pianoMediaServiceInstance.get()).thenReturn(pianoMediaService);
        when(pianificazionePianoMediaServiceInstance.get()).thenReturn(pianificazionePianoMediaService);
        when(pianificazionePianoFactoryInstance.get()).thenReturn(pianificazionePianoFactory);
        when(pianoMediaHelperServiceInstance.get()).thenReturn(pianoMediaHelperService);
        when(pianoMediaHelperService.executeChecks(any(PianificazionePianoMediaEntity.class), anyString()))
                .thenReturn(0);
        when(securityUtilsInstance.get()).thenReturn(securityUtils);
        when(securityUtils.canWriteScheda(usersEntity)).thenReturn(true);
    }

    @Test
    public void getPianificazioni_givenWrongIdPianoMedia_shouldReturnPreconditionFailed() {
        final Response response = ws.getPianificazioni("foo", request);
        assertEquals(412, response.getStatus());
        verify(pianoMediaServiceInstance, never()).get();
        verify(pianoMediaService, never()).findById(any());
        verify(pianificazionePianoFactoryInstance, never()).get();
        verify(pianificazionePianoFactory, never()).build(any(), eq(true));
    }

    @Test
    public void getPianificazioni_whenPianoMediaNotExist_shouldReturnPreconditionFailed() {
        when(pianoMediaService.findById(1L)).thenReturn(null);
        final Response response = ws.getPianificazioni("1", request);
        assertEquals(412, response.getStatus());
        verify(pianoMediaServiceInstance, times(1)).get();
        verify(pianoMediaService, times(1)).findById(1L);
        verify(pianificazionePianoFactoryInstance, never()).get();
        verify(pianificazionePianoFactory, never()).build(any(), eq(true));
    }

    @Test
    public void getPianificazioni() {
        when(pianoMediaService.findById(1L)).thenReturn(pianoMedia);
        when(pianificazionePianoFactory.build(pianoMedia, true)).thenReturn(pianificazioniPianoDto);
        when(pianificazioniPianoDto.asJson()).thenReturn("ok");
        when(securityUtils.isGanntEditable(pianoMedia)).thenReturn(true);
        final Response response = ws.getPianificazioni("1", request);
        assertEquals(200, response.getStatus());
        assertEquals("ok", response.getEntity());
        verify(pianoMediaServiceInstance, times(1)).get();
        verify(pianoMediaService, times(1)).findById(1L);
        verify(pianificazionePianoFactoryInstance, times(1)).get();
        verify(pianificazionePianoFactory, times(1)).build(pianoMedia, true);
    }

    @Test
    public void createPianificazione_givenWrongIdPianoMedia_shouldReturnPreconditionFailed() {
        final Response response = ws.createPianificazione("foo", createPianificazioneDto, request);
        assertEquals(412, response.getStatus());
        verify(pianoMediaServiceInstance, never()).get();
        verify(pianoMediaService, never()).findById(any());
        verify(pianificazionePianoMediaServiceInstance, never()).get();
        verify(pianificazionePianoMediaService, never()).persist(any(PianificazionePianoMediaEntity.class));
        verify(pianificazionePianoFactoryInstance, never()).get();
        verify(pianificazionePianoFactory, never())
                .buildPianificazionePianoMedia(any(PianoMediaEntity.class), any(CreatePianificazioneDto.class), anyString());
    }

    @Test
    public void createPianificazione_givenNullPayload_shouldReturnPreconditionFailed() {
        final Response response = ws.createPianificazione("1", null, request);
        assertEquals(412, response.getStatus());
        verify(pianoMediaServiceInstance, never()).get();
        verify(pianoMediaService, never()).findById(any());
        verify(pianificazionePianoMediaServiceInstance, never()).get();
        verify(pianificazionePianoMediaService, never()).persist(any(PianificazionePianoMediaEntity.class));
        verify(pianificazionePianoFactoryInstance, never()).get();
        verify(pianificazionePianoFactory, never())
                .buildPianificazionePianoMedia(any(PianoMediaEntity.class), any(CreatePianificazioneDto.class), anyString());
    }

    @Test
    public void createPianificazione_whenPianoMediaNotExist_shouldReturnPreconditionFailed() {
        when(pianoMediaService.findById(1L)).thenReturn(null);
        final Response response = ws.createPianificazione("1", createPianificazioneDto, request);
        assertEquals(412, response.getStatus());
        verify(pianoMediaServiceInstance, times(1)).get();
        verify(pianoMediaService, times(1)).findById(1L);
        verify(pianificazionePianoMediaServiceInstance, never()).get();
        verify(pianificazionePianoMediaService, never()).persist(any(PianificazionePianoMediaEntity.class));
        verify(pianificazionePianoFactoryInstance, never()).get();
        verify(pianificazionePianoFactory, never())
                .buildPianificazionePianoMedia(any(PianoMediaEntity.class), any(CreatePianificazioneDto.class), anyString());
    }

    @Test
    public void createPianificazione_whenCannotBuildEntity_shouldReturnPreconditionFailed() {
        when(pianoMediaService.findById(1L)).thenReturn(pianoMedia);
        when(pianificazionePianoFactory.buildPianificazionePianoMedia(pianoMedia, createPianificazioneDto, "junit"))
                .thenReturn(null);
        final Response response = ws.createPianificazione("1", createPianificazioneDto, request);
        assertEquals(412, response.getStatus());
        verify(pianoMediaServiceInstance, times(1)).get();
        verify(pianoMediaService, times(1)).findById(1L);
        verify(pianificazionePianoMediaServiceInstance, never()).get();
        verify(pianificazionePianoMediaService, never()).persist(any(PianificazionePianoMediaEntity.class));
        verify(pianificazionePianoFactoryInstance, times(1)).get();
        verify(pianificazionePianoFactory, times(1))
                .buildPianificazionePianoMedia(pianoMedia, createPianificazioneDto, "junit");
    }

    @Test
    public void createPianificazione() {
        when(pianoMediaService.findById(1L)).thenReturn(pianoMedia);
        final PianificazionePianoMediaEntity ppm = mock(PianificazionePianoMediaEntity.class);
        when(pianificazionePianoFactory.buildPianificazionePianoMedia(pianoMedia, createPianificazioneDto, "junit"))
                .thenReturn(ppm);
        when(pianificazionePianoFactory.build(pianoMedia, true)).thenReturn(pianificazioniPianoDto);
        when(pianificazionePianoMediaService.persist(ppm)).thenReturn(ppm);
        final Response response = ws.createPianificazione("1", createPianificazioneDto, request);
        assertEquals(200, response.getStatus());
        verify(pianoMediaServiceInstance, times(1)).get();
        verify(pianoMediaService, times(1)).findById(1L);
        verify(pianificazionePianoMediaServiceInstance, times(1)).get();
        verify(pianificazionePianoMediaService, times(1)).persist(ppm);
        verify(pianificazionePianoFactoryInstance, times(2)).get();
        verify(pianificazionePianoFactory, times(1))
                .buildPianificazionePianoMedia(pianoMedia, createPianificazioneDto, "junit");
        verify(pianificazionePianoFactory, times(1)).build(pianoMedia, true);
    }
}