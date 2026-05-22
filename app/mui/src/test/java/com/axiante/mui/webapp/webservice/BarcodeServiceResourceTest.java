package com.axiante.mui.webapp.webservice;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.axiante.mui.dbpromo.business.utils.BarcodeUtils;
import com.axiante.mui.dbpromo.persistence.entities.PromozionePianificazioneEntity;
import com.axiante.mui.dbpromo.persistence.entities.PromozioneTestataEntity;
import com.axiante.mui.dbpromo.persistence.service.PromozionePianificazioneService;
import com.axiante.mui.persistence.entity.UsersEntity;
import com.axiante.mui.webapp.business.UserService;
import com.axiante.mui.webapp.business.data.UserDTO;
import com.axiante.mui.webapp.utils.security.PromoSecurity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.zxing.WriterException;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BarcodeServiceResourceTest {

    private String contesto = "testContext";

    @Mock
    HttpSession session;

    @Mock
    UserService userService;

    @Mock
    private HttpServletRequest mockedRequest;

    @Mock
    private UserDTO userDTO;

    @Mock
    private UsersEntity usersEntity;

    @Mock
    private PromozionePianificazioneService service;

    @Mock
    private BarcodeUtils utils;

    @Mock
    private PromoSecurity promoSecurity;

    @Spy
    @InjectMocks
    private BarcodeServiceResource serviceResource;

    @Before
    public void setUp() throws Exception {
        when(serviceResource.getCurrentUser()).thenReturn(usersEntity);
        when(serviceResource.getApplicationUser(contesto)).thenReturn(userDTO);
        when(serviceResource.getApplicationUser(nullable(String.class))).thenReturn(userDTO);
    }

    @Test
    public void generateEan13_nullId() throws JsonProcessingException {
        final Response response = serviceResource.generateEan13(null, mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(utils, never()).generateEan13FromEntity(any(PromozionePianificazioneEntity.class));
    }

    @Test
    public void generateEan13_pianificazioneNotFound() throws JsonProcessingException {
        when(service.findById(1L)).thenReturn(null);
        final Response response = serviceResource.generateEan13("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, never()).isAccessible(any(PromozioneTestataEntity.class), any(UserDTO.class));
        verify(utils, never()).generateEan13FromEntity(any(PromozionePianificazioneEntity.class));
    }

    @Test
    public void generateEan13_promoNotAccessible() throws JsonProcessingException {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(service.findById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(false);
        final Response response = serviceResource.generateEan13("1", mockedRequest);
        assertEquals(Response.Status.PRECONDITION_FAILED.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(utils, never()).generateEan13FromEntity(any(PromozionePianificazioneEntity.class));
    }

    @Test
    public void generateEan13_cannotCreateImage() throws JsonProcessingException, WriterException {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        when(service.findById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(utils.generateEan13ImageFromEntity(pianificazione)).thenThrow(WriterException.class);
        final Response response = serviceResource.generateEan13("1", mockedRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(utils, times(1)).generateEan13ImageFromEntity(pianificazione);
    }
    @Test
    public void generateEan13_ok() throws JsonProcessingException, WriterException {
        final PromozioneTestataEntity promozione = mockPromozione();
        final PromozionePianificazioneEntity pianificazione = mockPianificazione(promozione);
        final BufferedImage image = new BufferedImage(10, 10, TYPE_INT_RGB);
        image.getGraphics().setColor(Color.BLUE);
        image.getGraphics().fillRect(0, 0, 10, 10);
        when(service.findById(1L)).thenReturn(pianificazione);
        when(promoSecurity.isAccessible(promozione, userDTO)).thenReturn(true);
        when(utils.generateEan13ImageFromEntity(pianificazione)).thenReturn(image);
        final Response response = serviceResource.generateEan13("1", mockedRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(promoSecurity, times(1)).isAccessible(promozione, userDTO);
        verify(utils, times(1)).generateEan13ImageFromEntity(pianificazione);
    }

    private PromozionePianificazioneEntity mockPianificazione(PromozioneTestataEntity promozione) {
        final PromozionePianificazioneEntity pianificazione = mock(PromozionePianificazioneEntity.class);
        when(pianificazione.getPromozioneTestataEntity()).thenReturn(promozione);
        return pianificazione;
    }

    private PromozioneTestataEntity mockPromozione() {
        return mock(PromozioneTestataEntity.class);
    }
}